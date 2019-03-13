package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.CitaForm;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Asignacion;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Cita;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HorarioClinica;
import pe.com.fisi.cenpro.sigeco.mgc.repository.AsignacionRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.CitaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.HorarioClinicaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.CitaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.ParamConfigService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaAsistenciaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.PapeletaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.CitaTransformer;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes;
import pe.com.fisi.cenpro.sigeco.mgc.utils.ServiceConstants;

@Service
public class CitaServiceImpl implements CitaService {

	private final CitaRepository citaRepository;
	private final AsignacionRepository asignacionRepository;
	private final HorarioClinicaRepository horarioClinicaRepository;

	@Autowired
	public CitaServiceImpl(CitaRepository citaRepository, AsignacionRepository asignacionRepository,
			HorarioClinicaRepository horarioClinicaRepository) {
		this.citaRepository = citaRepository;
		this.asignacionRepository = asignacionRepository;
		this.horarioClinicaRepository = horarioClinicaRepository;
	}

	@Override
	public List<CitaBO> listarCitasPorIdAsignacion(String idAsig) {
		List<Object[]> list = null;

		if (!idAsig.trim().isEmpty()) {
			int idAsignacion = AppUtil.ObjectToInteger(idAsig);
			list = citaRepository.obtenerCitasPorIdAsignacion(idAsignacion);
		} else {
			list = citaRepository.obtenerCitas();
		}

		return CitaTransformer.transformObjectsToListBo(list);
	}

	@Override
	public List<CitaBO> listarCitasPorBo(CitaBO citaBo) {
		char turno = 'O';
		int nroHc = -1;
		int nroContrato = -1;
		String codigoAlumno = "X";
		String dniPaciente = "X";
		int idCurso = -1;
		int anioCurso = -1;
		int idClinica = -1;
		String fechaAtencion = "X";
		String esRecita = "N";

		if (!String.valueOf(citaBo.getTurno()).trim().isEmpty()) {
			turno = citaBo.getTurno();
		}
		if (citaBo.getNumeroHC() != null) {
			nroHc = citaBo.getNumeroHC();
		}
		if (citaBo.getNumeroContrato() != null) {
			nroContrato = citaBo.getNumeroContrato();
		}
		if (citaBo.getCodigoOperador() != null && !citaBo.getCodigoOperador().trim().isEmpty()) {
			codigoAlumno = citaBo.getCodigoOperador();
		}
		if (citaBo.getDniPaciente() != null && !citaBo.getDniPaciente().trim().isEmpty()) {
			dniPaciente = citaBo.getDniPaciente();
		}
		if (citaBo.getIdCurso() != null && citaBo.getIdCurso() != -1) {
			idCurso = citaBo.getIdCurso();
		}
		if (citaBo.getAnio() != null && citaBo.getAnio() != -1) {
			anioCurso = Integer.parseInt(String.valueOf(citaBo.getAnio()));
		}
		if (citaBo.getIdClinica() != null && citaBo.getIdClinica() != -1) {
			idClinica = citaBo.getIdClinica();
		}
		if (citaBo.getFechaAtencion() != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			fechaAtencion = formatter.format(citaBo.getFechaAtencion());
		}
		if (citaBo.getIsRecita()) {
			esRecita = "Y";
		}
		return CitaTransformer.transformObjectsToListBo(citaRepository.obtenerCitasPorCampos(String.valueOf(turno),
				nroHc, nroContrato, codigoAlumno, dniPaciente, idCurso, anioCurso, idClinica, fechaAtencion, esRecita));
		// citaRepository.obtenerCitasPorCampos(nroHc, nroContrato,
		// codigoAlumno,
		// dniPaciente, idCurso, anioCurso, idClinica, fechaAtencion,
		// esRecita));
	}
	
	@Override
	public List<CitaAsistenciaBO> listarCitasAsistenciaPorBo(CitaBO citaBo) {
		char turno = 'O';
		int nroHc = -1;
		int nroContrato = -1;
		String codigoAlumno = "X";
		String dniPaciente = "X";
		int idCurso = -1;
		int idClinica = -1;
		String fechaAtencion = "X";
		
		if (!String.valueOf(citaBo.getTurno()).trim().isEmpty()) {
			turno = citaBo.getTurno();
		}
		if (citaBo.getNumeroHC() != null) {
			nroHc = citaBo.getNumeroHC();
		}
		if (citaBo.getNumeroContrato() != null) {
			nroContrato = citaBo.getNumeroContrato();
		}
		if (citaBo.getCodigoOperador() != null && !citaBo.getCodigoOperador().trim().isEmpty()) {
			codigoAlumno = citaBo.getCodigoOperador();
		}
		if (citaBo.getDniPaciente() != null && !citaBo.getDniPaciente().trim().isEmpty()) {
			dniPaciente = citaBo.getDniPaciente();
		}
		if (citaBo.getIdCurso() != null && citaBo.getIdCurso() != -1) {
			idCurso = citaBo.getIdCurso();
		}
		if (citaBo.getIdClinica() != null && citaBo.getIdClinica() != -1) {
			idClinica = citaBo.getIdClinica();
		}
		if (citaBo.getFechaAtencion() != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			fechaAtencion = formatter.format(citaBo.getFechaAtencion());
		}		
		return CitaTransformer.transformObjectsToListAsistenciaBo(citaRepository.obtenerCitasAsistenciaPorCampos(String.valueOf(turno),
				nroHc, nroContrato, codigoAlumno, dniPaciente, idCurso, idClinica, fechaAtencion));
	}
	

	@Override
	@Transactional
	public String registrarCita(CitaForm citaForm, String dniPersona) {
		return registrarCita(citaForm, dniPersona, true);
	}

	/**
	 * Método que registra la cita, realiza todas las validaciones necesarias
	 * para mantener la integridad de la cita y respetar las reglas de negocio.
	 * Valida los siguientes reglas:
	 * <ul type="1">
	 * <li>Cronologia de Fechas</li>
	 * <li>Fecha de Atencion</li>
	 * <li>Fecha de Reserva</li>
	 * <li>Cantidad de citas</li>
	 * </ul>
	 * 
	 * @param citaForm
	 *            es el dto que contiene los datos a registrar provenientes del
	 *            controlador, se asume que su formato ha sido previamente
	 *            validado.
	 */
	@Transactional
	@Override
	public String registrarCita(CitaForm citaForm, String dniPersona, boolean isValidada) {
		
		boolean isHoraRestringida = false, isAdicional = false;
		LocalDateTime fechaHoraReserva = DateUtil.getCurrentDateTimeWithTimeZone();
		LocalDate fechaReserva = fechaHoraReserva.toLocalDate();
		LocalDate fechaAtencion = DateUtil.toLocalDate(citaForm.getFechaAtencion());

		// Validar cronologia de fechas
		if (!validarCronologiaDeFechas(fechaReserva, fechaAtencion)) {
			return Mensajes.M_FECHA_ATENCION_NO_VALIDA;
		} else {
			// Verificar cita adicional
			isAdicional = verificarCitaAdicional(fechaReserva, fechaAtencion);
			citaForm.setAdicional(isAdicional);
		}

		if (isValidada) {
			// Validar la fecha de atención
			int resValidaFecAte = validarFechaAtencion(fechaAtencion);
			if (resValidaFecAte != ServiceConstants.FECHA_ATENCION_VALIDA) {
				return Mensajes.evaluarResultado(resValidaFecAte);
			}

			// Validar la fecha de reserva
			int resValidaFecRes = validarFechaReserva(fechaReserva, fechaAtencion);
			if (resValidaFecRes != ServiceConstants.FECHA_RESERVA_ANTICIPADA) {
				if (resValidaFecRes == ServiceConstants.HORA_RESTRINGIDA) {
					isHoraRestringida = true;
				} else if (resValidaFecRes == ServiceConstants.FECHA_RESERVA_NO_VALIDA) {
					return Mensajes.M_FECHA_RESERVA_NO_VALIDA;
				}
			}

			// Validar la hora de reserva
			if (isHoraRestringida) {
				// No Esta antes de la hora maxima
				if (!validarHoraReserva(fechaHoraReserva.toLocalTime())) {
					return Mensajes.M_HORA_RESERVA_NO_PERMITIDA;
				}
			}

			// Validar la cantidad de citas
			int numCitasPorDia = citaRepository.getNumCitasPorDia(dniPersona, citaForm.getFechaAtencion());
			if (numCitasPorDia < ServiceConstants.MAXIMO_CITAS_DIA) {
				int numCitasPorDiaPaciente = citaRepository.getNumCitasPorPaciente(citaForm.getIdAsignacion(),
						citaForm.getFechaAtencion());
				if (numCitasPorDiaPaciente == 0) {
					
					Cita cita = obtenerCitaDeCitaForm(citaForm);
					if( cita != null ){
						cita = citaRepository.saveAndFlush(cita);
						if(isAdicional) {
							return Mensajes.M_CITA_ADICIONAL_CORRECTA;
						}
						return Mensajes.M_CITA_CORRECTA;
						
					} else {
						return Mensajes.M_CITA_TURNO_NO_VALIDO;
					}

				} else {
					return Mensajes.M_EXISTE_CITA_PACIENTE_DIA;
				}
			} else {
				return Mensajes.M_MAXIMO_CITAS_ALCANZADO;
			}
			
		} else {
			Cita cita = obtenerCitaDeCitaForm(citaForm);
			if( cita != null ){
				citaRepository.saveAndFlush(cita);
				if(isAdicional) {
					return Mensajes.M_CITA_ADICIONAL_CORRECTA + " \n" + Mensajes.M_ADVERTENCIA_CITAS_NO_VALIDADA;
				}
				return Mensajes.M_CITA_CORRECTA + " " + Mensajes.M_ADVERTENCIA_CITAS_NO_VALIDADA;
			} else {
				return Mensajes.M_CITA_TURNO_NO_VALIDO + " " + Mensajes.M_ADVERTENCIA_CITAS_NO_VALIDADA;
			}
		}
	}

	/**
	 * Metodo que valida la fecha de atención
	 * 
	 * @param fechaReserva
	 * @param fechaAtencion
	 * @return una constante de ServiceConstants que representa el resultado de
	 *         validar las fechas (Valida, No valida: Domingo, Feriado)
	 * @see ServiceConstants
	 */
	private int validarFechaAtencion(LocalDate fechaAtencion) {
		boolean isFecAteFeriado = ParamConfigService.getLsFeriados().stream()
				.anyMatch(f -> f.compareTo(fechaAtencion) == 0);
		if (!isFecAteFeriado) {
			boolean isFecAteDomingo = fechaAtencion.getDayOfWeek().equals(DayOfWeek.SUNDAY);
			if (!isFecAteDomingo) {
				return ServiceConstants.FECHA_ATENCION_VALIDA;
			} else {
				return ServiceConstants.FECHA_ATENCION_DOMINGO;
			}
		} else {
			return ServiceConstants.FECHA_ATENCION_FERIADO;
		}
	}

	/**
	 * Método que valida si la fecha de reserva se encuentra en el rango
	 * permitido de la fecha de atención, en base al MAX y MIN de días de
	 * anticipación permitidos
	 * 
	 * @param fechaReserva
	 * @param fechaAtencion
	 * @return una constante de ServiceConstants dependendiendo si la fecha de
	 *         reserva es valida o no y si se encuentra en el límite la cte.
	 *         indica que hay que restringir la hora.
	 */
	private int validarFechaReserva(LocalDate fechaReserva, LocalDate fechaAtencion) {
		boolean isFecResSabado = fechaReserva.getDayOfWeek().equals(DayOfWeek.SATURDAY);
		int maxAntPer = isFecResSabado ? ServiceConstants.MIN_DIAS_ANTI_PERMITIDA
				: ServiceConstants.MIN_DIAS_ANTI_PERMITIDA_SABADO;
		long numDiasEntreFecResFecAte = DateUtil.daysBetween(fechaReserva, fechaAtencion);
		if (numDiasEntreFecResFecAte > maxAntPer) {
			return ServiceConstants.FECHA_RESERVA_ANTICIPADA;
		} else if (numDiasEntreFecResFecAte == maxAntPer) {
			return ServiceConstants.HORA_RESTRINGIDA;
		} else {
			return ServiceConstants.FECHA_RESERVA_NO_VALIDA;
		}
	}

	/**
	 * Método que evalua si la hora esta antes del parametro definidio. No es
	 * inclusivo con el minuto/segundo final.
	 * 
	 * @param horaReserva
	 */
	private boolean validarHoraReserva(LocalTime horaReserva) {
		return horaReserva.compareTo(ServiceConstants.MAXIMA_HORA_PERMITIDA) > 0;
	}

	/**
	 * Método que solo verifica si una cita es adicional o no, es decir, si la
	 * fecha de reserva es la misma que la fecha de atención.
	 * 
	 * @param fechaReserva
	 * @param fechaAtencion
	 * @return true si coinciden las fechas, false en otro caso.
	 */
	private boolean verificarCitaAdicional(LocalDate fechaReserva, LocalDate fechaAtencion) {
		return fechaAtencion.compareTo(fechaReserva) == 0;
	}

	private boolean validarCronologiaDeFechas(LocalDate fechaReserva, LocalDate fechaAtencion) {
		return fechaAtencion.compareTo(fechaReserva) >= 0;
	}

    /**
     * Cancela una cita, verificando las condiciones de validación
     * */
	@Transactional
	@Override
	public void cancelarCita(int idCita) {
		Cita cita = citaRepository.findOneCustom(idCita);		
		if (cita != null) {
			cita.setEstado("CANCELADA");
			citaRepository.saveAndFlush(cita);
		}
	}

	public Cita obtenerCitaDeCitaForm(CitaForm citaForm) {
		Cita cita = new Cita();
		cita.setFechaAtencion(citaForm.getFechaAtencion());
		cita.setFechaReserva(DateUtil.getCurrentDateWithTimeZone());

		Asignacion asignacion = new Asignacion();
		asignacion.setIdAsignacion(citaForm.getIdAsignacion());
		cita.setAsignacion(asignacion);
		if(citaForm.isAdicional()){
			cita.setEstado("ADICIONAL");
		} else {
			cita.setEstado("RESERVADA");
		}
		System.out.println(citaForm.getTurno() + "--->oliwi");
		// cita.setTurno(citaForm.getTurno());
 
		List<Object[]> list = horarioClinicaRepository.obtenerHorariosPorCursoClinicaDia(citaForm.getIdClinica(),
				 DateUtil.getNameDayOfTheWeek(citaForm.getFechaAtencion()), citaForm.getTurno()+"");

		
		if (!list.isEmpty()) {
			HorarioClinica horarioClinica = new HorarioClinica();
			Object o[] = list.get(0);
			horarioClinica.setIdHorarioClinica(AppUtil.ObjectToShort(o[0]));
			cita.setHorarioClinica(horarioClinica);
		} else {
			return null;
		}

		if (citaForm.getIdHorarioClinica() != null) {
			HorarioClinica hc = new HorarioClinica();
			hc.setIdHorarioClinica(citaForm.getIdHorarioClinica().shortValue());
			
			cita.setHorarioClinica(hc);
		}
		return cita;
	}

	@Override
	public PapeletaBO obtenerPapeletaBoPorIdCita(int idCita) {
		List<Object[]> lista = citaRepository.obtenerCitasPorId(idCita);
		if (lista != null && lista.size() > 0) {
			return CitaTransformer.transformObjectToPapeletaBo(lista.get(0));
		}
		return null;
	}
}
