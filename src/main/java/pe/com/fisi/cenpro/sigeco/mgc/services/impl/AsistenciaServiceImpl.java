package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import static pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoMovimiento.ENTRADA;
import static pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoMovimiento.SALIDA;
import static pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoOperacion.ACTUALIZACION;
import static pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoOperacion.NINGUNA;
import static pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoOperacion.REGISTRO;
import static pe.com.fisi.cenpro.sigeco.mgc.services.transformer.AsistenciaTransformer.transformToAsistencia;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsistenciaPersonalFormBO;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Asistencia;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Cita;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HorarioClinica;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Persona;
import pe.com.fisi.cenpro.sigeco.mgc.repository.AsistenciaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.CitaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.HorarioClinicaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.PersonaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsistenciaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HorarioClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsistenciaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaAdministrativoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.exceptions.AsistenciaException;
import pe.com.fisi.cenpro.sigeco.mgc.services.exceptions.HorarioClinicaException;
import pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoMovimiento;
import pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoOperacion;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.ListUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes;
import pe.com.fisi.cenpro.sigeco.mgc.utils.ServiceConstants;
import pe.com.fisi.cenpro.sigeco.mgc.utils.ServiceUtil;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

	private final AsistenciaRepository asistenciaRepository;
	private final CitaRepository citaRepository;
	private final HorarioClinicaRepository horarioClinicaRepository;
	private final PersonaRepository personaRepository;

	@Autowired
	private HorarioClinicaService horarioClinicaService;

	@Autowired
	public AsistenciaServiceImpl(CitaRepository citaRepository, HorarioClinicaRepository horarioClinicaRepository,
			AsistenciaRepository asistenciaRepository, PersonaRepository personaRepository) {
		this.citaRepository = citaRepository;
		this.horarioClinicaRepository = horarioClinicaRepository;
		this.asistenciaRepository = asistenciaRepository;
		this.personaRepository = personaRepository;
	}

	@Override
	@Transactional 
	public String registrarAsistencia(AsistenciaBO asistenciaBO) {
		// TODO Auto-generated method stub
		System.out.println("Id: " + asistenciaBO.getIdClinicaAsistencia());
		int idCita = asistenciaBO.getIdCitaAsistencia();
		Cita cita = citaRepository.findOneCustom(idCita);
		Date hoy = new Date();
		String msj = "";
		short turno = DateUtil.getShiftOfDate(hoy);
		// List<?> obj = Arrays.asList(1,2);
		// List<Object[]> ao = (List<Object[]>) obj;
		if (turno != 0) {

			System.out.println("Parametros: " + asistenciaBO.getIdClinicaAsistencia() + " " + turno + " "
					+ DateUtil.getNameDayOfTheWeek(hoy));
			List<Object> list = horarioClinicaRepository.obtenerHorariosPorClinicaTurnoDia(
					asistenciaBO.getIdClinicaAsistencia(), turno, DateUtil.getNameDayOfTheWeek(hoy));
			if (!list.isEmpty()) {
				if (cita.getHorarioClinica().getIdHorarioClinica() == (short) list.get(0)) {
					Asistencia asistencia = new Asistencia();
					Persona persona = new Persona(); // para saber que persona
														// va a marcar
														// asistencia
					HorarioClinica horarioClinica = new HorarioClinica(); // para
																			// en
																			// que
																			// horario
																			// entro
					Time time = new Time(hoy.getTime()); // para saber la hora
															// en que entro

					persona.setDniPersona(asistenciaBO.getDniPersonaAsistencia());
					horarioClinica.setIdHorarioClinica(cita.getHorarioClinica().getIdHorarioClinica());
					System.out.println("dniPersona " + asistenciaBO.getDniPersonaAsistencia());
					asistencia.setHoraEntrada(time);// hoy.minutos en Time
					asistencia.setFecha(hoy);
					asistencia.setPersona(persona);
					asistencia.setHorarioClinica(horarioClinica);
					asistencia = asistenciaRepository.saveAndFlush(asistencia);
					cita.setAsistencia(asistencia);// no hizo falta de un update
					msj = Mensajes.M_ASISTENCIA_VALIDA;
				} else {
					msj = Mensajes.M_ASISTENCIA_NO_VALIDA;
				}
			} else {
				msj = Mensajes.M_ASISTENCIA_NO_HORARIO;
			}
		} else {
			msj = Mensajes.M_ASISTENCIA_NO_VALIDA;
		}
		return msj;
	}

	/**
	 * Registra la asistencia del personal (administrativo, docente, operador)
	 * 
	 * 
	 **/

	@Override
	public String buscarNombreCompletoPersonal(String dniPersona) {
		Map<String, Object> personaBuscada = personaRepository.getNombreCompletoPorDni(dniPersona);
		System.out.println(personaBuscada);
		if (personaBuscada != null && !personaBuscada.isEmpty()) {
			return ServiceUtil.concatenarNombreCompleto(personaBuscada);
		}
		return null;
	}

	/**
	 * Valida si la ip de la máquina coincide con la asignada para la clínica
	 * Valida si el administrativo tiene un turno habilitado
	 */
	@Override
	@Transactional
	public void registrarAsistencia(AsistenciaPersonalFormBO asistenciaPersonalFormBo) {
		Optional<Asistencia> asistencia;
		// Validación del turno del administrativo
		HorarioClinicaAdministrativoBO horarioClinicaAdministrativoBO = horarioClinicaService
				.buscarHorarioClinicoActualPorAdministrativo(asistenciaPersonalFormBo.getDniAdministrativoClinica(),
						asistenciaPersonalFormBo.getFechaHoraMovimiento())
				.orElseThrow(() -> new HorarioClinicaException(
						"El administrativo no está en horario clínico válido para ingreso de asistencia."));
		asistenciaPersonalFormBo.setIdHorarioClinica(horarioClinicaAdministrativoBO.getIdHorarioClinica());

		// Validación del número máximo de entrada-salida permitida por día.
		List<Asistencia> lsAsistencia = asistenciaRepository.buscarLsAsistenciaPorHorarioFecha(
				horarioClinicaAdministrativoBO.getIdHorarioClinica(),
				DateUtil.toDate(asistenciaPersonalFormBo.getFechaHoraMovimiento()),
				asistenciaPersonalFormBo.getDniPersonaAsistente());
		System.out.println("mira aca-------------> " +lsAsistencia);
		
		if (lsAsistencia.size() > ServiceConstants.MAXIMO_ENTRADA_SALIDA_POR_TURNO) {
			throw new AsistenciaException("El personal ha alcanzado el máximo de entradas y salidas por turno.");
		}

		// Validación de lógica de orden de Entrada y Salida
		TipoMovimiento nuevoMovimiento = asistenciaPersonalFormBo.getTipoMovimiento();
		TipoMovimiento ultimoMovimiento;
		TipoOperacion tipoOperacion = NINGUNA;
		Asistencia ultimaAsistencia = ListUtil.getLast(lsAsistencia);
		System.out.println("seraaa"  + ultimaAsistencia);
		ultimoMovimiento = ultimaAsistencia != null ? (ultimaAsistencia.getHoraSalida() == null ? ENTRADA : SALIDA)
				: SALIDA;
		if (!nuevoMovimiento.equals(ultimoMovimiento)) {
			tipoOperacion = nuevoMovimiento.equals(ENTRADA) ? REGISTRO : ACTUALIZACION;
		} else {
			throw new AsistenciaException("El personal ya ha registrado su " + nuevoMovimiento.name());
		}
		

		// Validación del intervalo entre entrada y salida
		if (ultimoMovimiento.equals(ENTRADA)) {
			System.out.println(DateUtil.minutesBetween(ultimaAsistencia.getHoraEntrada(), asistenciaPersonalFormBo
					.getFechaHoraMovimiento()) );
			if (DateUtil.minutesBetween(ultimaAsistencia.getHoraEntrada(), asistenciaPersonalFormBo
					.getFechaHoraMovimiento()) < ServiceConstants.TIEMPO_MINIMO_MINUTOS_ENTRE_MOVIMIENTO) {
				throw new AsistenciaException("El personal debe permanecer un mínimo de "
						+ ServiceConstants.TIEMPO_MINIMO_MINUTOS_ENTRE_MOVIMIENTO);
			}
		}
		
		
		// Registro o actualización
		switch (tipoOperacion) {
			case REGISTRO:
				Asistencia nuevaAsistencia = transformToAsistencia(asistenciaPersonalFormBo);
				nuevaAsistencia.setHoraEntrada(DateUtil.toTime(asistenciaPersonalFormBo.getFechaHoraMovimiento()));
				asistencia = Optional.ofNullable(asistenciaRepository.saveAndFlush(nuevaAsistencia));
				asistencia.orElseThrow(
						() -> new AsistenciaException("El sistema ha fracasado al intentar guardar la asistencia."));
				break;
			case ACTUALIZACION:
				ultimaAsistencia.setHoraSalida(DateUtil.toTime(asistenciaPersonalFormBo.getFechaHoraMovimiento()));
				break;
			case NINGUNA:
				break;
		}

	}

}
