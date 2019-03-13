package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.HistoriaClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Alumno;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Asignacion;
import pe.com.fisi.cenpro.sigeco.mgc.domain.ContratoPre;
import pe.com.fisi.cenpro.sigeco.mgc.domain.DatosPersona;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HistoriaClinica;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Paciente;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Persona;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Ubigeo;
import pe.com.fisi.cenpro.sigeco.mgc.repository.AlumnoRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.AsignacionRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.ContratoPreRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.DatosPersonaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.HistoriaClinicaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.PacienteRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.PersonaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.UbigeoRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.HistoriaClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsignacionDetalleBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.AsignacionTransformer;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.HistoriaClinicaTransformer;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Constantes;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;

@Service
//@Transactional
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

	private final HistoriaClinicaRepository historiaClinicaRepository;
	private final PacienteRepository pacienteRepository;
	private final ContratoPreRepository contratoPreRepository;
	private final PersonaRepository personaRepository;
	private final DatosPersonaRepository datosPersonaRepository;
	private final AsignacionRepository asignacionRepository;
	private final AlumnoRepository alumnoRepository;
	private final UbigeoRepository ubigeoRepository;

	@Autowired
	public HistoriaClinicaServiceImpl(HistoriaClinicaRepository historiaClinicaRepository,
			PacienteRepository pacienteRepository, ContratoPreRepository contratoPreRepository,
			PersonaRepository personaRepository, DatosPersonaRepository datosPersonaRepository,
			AsignacionRepository asignacionRepository, AlumnoRepository alumnoRepository,
			UbigeoRepository ubigeoRepository) {
		this.historiaClinicaRepository = historiaClinicaRepository;
		this.pacienteRepository = pacienteRepository;
		this.contratoPreRepository = contratoPreRepository;
		this.personaRepository = personaRepository;
		this.datosPersonaRepository = datosPersonaRepository;
		this.asignacionRepository = asignacionRepository;
		this.alumnoRepository = alumnoRepository;
		this.ubigeoRepository = ubigeoRepository;
	}

	@Override
	public List<HistoriaClinicaGeneralBO> obtenerTodasHistoriasclinicas() {
		List<Object[]> listObjectHistoriaClinica = historiaClinicaRepository.obtenerTodasHistoriasClinicas();
		return HistoriaClinicaTransformer.transformToListHistoriaGeneralBo(listObjectHistoriaClinica);
	}

	@Override
	public HistoriaClinicaBO obtnerHistoriaClinica(int numHc) {

		List<Object[]> list = historiaClinicaRepository.obtenerHistoriaClinica(numHc);
		HistoriaClinicaBO historiaClinicaBO = HistoriaClinicaTransformer.transformToHistoriaClinicaBo(list.get(0));
		List<AsignacionDetalleBO> listDetallesBo = AsignacionTransformer
				.transformListObjectToAsignacionDetalleBo(asignacionRepository.obtenerAsignacionesPorNroHc(numHc));

		historiaClinicaBO.setListaAsignacionDetalle(listDetallesBo);
		return historiaClinicaBO;
	}

	@Override
	@Transactional
	public List<String> registrarHistoriaClinica(HistoriaClinicaForm historiaForm) {
		List<String> lstValidacion = null;
		HistoriaClinica historiaClinica = HistoriaClinicaTransformer
				.transformHistoriaFormToHistoriaClinica(historiaForm);
		
		historiaClinica=agregarUbigeoPaciente(historiaClinica,historiaForm);
		
		ContratoPre contrato = new ContratoPre();

		if (historiaForm.getHistoriaClinicaGeneralBO().getNroContrato() != null) {

			contrato.setNroContrato(historiaForm.getHistoriaClinicaGeneralBO().getNroContrato());
			lstValidacion = validarHCenBD(historiaClinica, contrato.getNroContrato());

			if (lstValidacion.isEmpty()) {

				contrato.setHistoriaClinica(historiaClinica);
				contrato.setFecha(historiaForm.getHistoriaClinicaGeneralBO().getFechaContrato());

				registrarHistoriaClinica(historiaClinica, contrato);
			}
		} else {

			lstValidacion = validarHCenBD(historiaClinica, -1);
			if (lstValidacion.isEmpty()) {

				registrarHistoriaClinica(historiaClinica);
			}
		}

		return lstValidacion;
	}
	
	private HistoriaClinica agregarUbigeoPaciente(HistoriaClinica historiaClinica,HistoriaClinicaForm  historiaClinicaForm){
		String codigoLugarProcedencia = historiaClinicaForm.getDatosPersonaBO().getUbigeoProcedencia();
		String codigoLugarNacimiento = historiaClinicaForm.getDatosPersonaBO().getUbigeoNacimiento();
		
		historiaClinica.getPaciente().getPersona().getDatosPersona().setUbigeo2(getUbigeo(codigoLugarNacimiento));
		historiaClinica.getPaciente().getPersona().getDatosPersona().setUbigeo1(getUbigeo(codigoLugarProcedencia));
		return historiaClinica;
	}

	public Ubigeo getUbigeo(String codigo) {
		Ubigeo ubigeo=null;
		try {
			String [] ubigeoChain = codigo.split(Constantes.SPLIT);
			List<Ubigeo> lista = ubigeoRepository.findByDepartamentoIdAndProvinciaIdAndDistritoId(ubigeoChain[0], ubigeoChain[1], ubigeoChain[2]);
			if(lista !=null && lista.size()>0){
				ubigeo = lista.get(0);
			}
		} catch (Exception e) {
			System.out.println("Error en la cadena enviada");
		}
		return ubigeo;
	}
	
	@Transactional
	public void registrarHistoriaClinica(HistoriaClinica historiaClinica) {
		Persona persona = historiaClinica.getPaciente().getPersona();
		DatosPersona datosPersona = historiaClinica.getPaciente().getPersona().getDatosPersona();
		Paciente paciente = historiaClinica.getPaciente();

		persona.setDatosPersona(null);// el mapeo no soporta la relacion, existe
										// conflictos por el uso de un unico key
										// compartido entre las entidades.
		personaRepository.save(persona);
		datosPersonaRepository.save(datosPersona);
		pacienteRepository.save(paciente);
		historiaClinicaRepository.save(historiaClinica);
	}

	public void registrarHistoriaClinica(HistoriaClinica historiaClinica, ContratoPre contrato) {
		registrarHistoriaClinica(historiaClinica);
		contratoPreRepository.saveAndFlush(contrato);
	}

	public List<String> validarHCenBD(HistoriaClinica HC, int numContrato) {
		List<String> lstNulos = new ArrayList<String>();
		if (historiaClinicaRepository.exists(HC.getNroHC())) {
			lstNulos.add("La Historia Clinica ya esta Registrada");
		}
		if (pacienteRepository.exists(HC.getPaciente().getPersona().getDniPersona())) {
			lstNulos.add("El paciente ya esta Registrado");
		}
		if (contratoPreRepository.exists(numContrato)) {
			lstNulos.add("El Contrato ya esta Registrado");
		}
		return lstNulos;
	}

	@Override
	public void modificarHistoriaClinica(HistoriaClinicaForm historiaForm) {
		HistoriaClinica historiaClinica = HistoriaClinicaTransformer
				.transformHistoriaFormToHistoriaClinica(historiaForm);
		ContratoPre contrato = new ContratoPre();
		
		historiaClinica=agregarUbigeoPaciente(historiaClinica,historiaForm);

		historiaClinica.getPaciente().getPersona().getDatosPersona()
				.setDniPersona(historiaForm.getHistoriaClinicaGeneralBO().getDniPaciente());
		historiaClinica.getPaciente().setDniPaciente(historiaForm.getHistoriaClinicaGeneralBO().getDniPaciente());

		if (historiaForm.getHistoriaClinicaGeneralBO().getNroContrato() != null) {
			contrato.setNroContrato(historiaForm.getHistoriaClinicaGeneralBO().getNroContrato());
			contrato.setHistoriaClinica(historiaClinica);
			contrato.setFecha(historiaForm.getHistoriaClinicaGeneralBO().getFechaContrato());
			modificarHistoriaClinica(historiaClinica, contrato);

		} else {
			modificarHistoriaClinica(historiaClinica);
		}
	}
	
	public void modificarHistoriaClinica(HistoriaClinica historiaClinica) {
		Persona persona = historiaClinica.getPaciente().getPersona();
		DatosPersona datosPersona = historiaClinica.getPaciente().getPersona().getDatosPersona();
		Paciente paciente = historiaClinica.getPaciente();

		persona.setDatosPersona(null);// el mapeo no soporta la relacion, existe
										// conflictos por el uso de un unico key
										// compartido entre las entidades.
		personaRepository.saveAndFlush(persona);
		datosPersonaRepository.saveAndFlush(datosPersona);
		pacienteRepository.saveAndFlush(paciente);
	}

	public void modificarHistoriaClinica(HistoriaClinica historiaClinica, ContratoPre contrato) {
		modificarHistoriaClinica(historiaClinica);
		contratoPreRepository.saveAndFlush(contrato);
	}

	@Override
	public boolean validarDatos(List<HistoriaClinicaForm> listaForms, List<Mensaje> mensajes) {
		boolean correcto = true;

		int i = 0;
		while (i < listaForms.size()) {

			String apellidoParternoPaciente = listaForms.get(i).getHistoriaClinicaGeneralBO().getApellidoPatPaciente();

			if (apellidoParternoPaciente != null && !apellidoParternoPaciente.trim().isEmpty()) {

				if (noSeRepite(i, listaForms, mensajes) == false) {
					correcto = false;
				}

				String codigoAlumno = listaForms.get(i).getAsignacionForm().getCodigoAlumno();

				if (codigoAlumno != null && !codigoAlumno.trim().isEmpty()) {

					if (!existeAlumno(codigoAlumno)) {
						correcto = false;
						Mensaje mensaje = new Mensaje();
						mensaje.setTipo(3);
						mensaje.setDescripcion("NO EXISTE ALUMNO CON CODIGO: " + codigoAlumno);
						mensaje.setResultado(listaForms.get(i));
						mensajes.add(mensaje);
					}
				}

				String nombreDistrito = listaForms.get(i).getDatosPersonaBO().getDistrito();

				if (nombreDistrito != null && !nombreDistrito.trim().isEmpty()) {

					if (!existeDistrito(nombreDistrito)) {

						correcto = false;
						Mensaje mensaje = new Mensaje();
						mensaje.setTipo(3);
						mensaje.setDescripcion("NO EXISTE DISTRITO: " + nombreDistrito);
						mensaje.setResultado(listaForms.get(i));
						mensajes.add(mensaje);
					}
				}
			}
			i++;
		}

		return correcto;
	}

	private boolean existeAlumno(String codigo) {
		if (alumnoRepository.findByCodigoAlumno(codigo) != null) {
			return true;
		}
		return false;
	}

	private boolean existeDistrito(String nombreDistrito) {
		if (ubigeoRepository.obtenerUbigeosPorNombre(nombreDistrito) != null) {
			return true;
		}
		return false;
	}

	// con esto verficare q la persona no este en el excel, ni en la bd
	private boolean noSeRepite(int posicion, List<HistoriaClinicaForm> listaForm, List<Mensaje> mensajes) {
		boolean correcto = true;
		HistoriaClinicaForm hcForm = listaForm.get(posicion);

		if (hcForm.getHistoriaClinicaGeneralBO().getDniPaciente().equals("")) {
			correcto = false;
			Mensaje mensaje = new Mensaje();
			mensaje.setTipo(3);
			mensaje.setDescripcion("FALTA DNI PACIENTE: " + hcForm.getHistoriaClinicaGeneralBO().getNombrePaciente() + " "
					+ hcForm.getHistoriaClinicaGeneralBO().getApellidoPatPaciente());
			mensaje.setResultado(hcForm);
			mensajes.add(mensaje);
		}
		int i = 0;
		while (i < listaForm.size()) {
			// para que no se compare con el elemento que ha llegado
			if (i != posicion && !hcForm.getHistoriaClinicaGeneralBO().getDniPaciente().equals("")) {
				if (hcForm.getHistoriaClinicaGeneralBO().getDniPaciente()
						.equals(listaForm.get(i).getHistoriaClinicaGeneralBO().getDniPaciente())) {
					correcto = false;
					Mensaje mensaje = new Mensaje();
					mensaje.setTipo(3);
					mensaje.setDescripcion(
							"SE REPITE DNI EN EXCEL: " + hcForm.getHistoriaClinicaGeneralBO().getDniPaciente());
					mensaje.setResultado(hcForm);
					mensajes.add(mensaje);
				} else if (hcForm.getHistoriaClinicaGeneralBO().getNroHC()
						.equals(listaForm.get(i).getHistoriaClinicaGeneralBO().getNroHC())) {
					correcto = false;
					Mensaje mensaje = new Mensaje();
					mensaje.setTipo(3);
					mensaje.setDescripcion("SE REPITE HC EN EXCEL: " + hcForm.getHistoriaClinicaGeneralBO().getNroHC());
					mensaje.setResultado(hcForm);
					mensajes.add(mensaje);
				} else if (hcForm.getHistoriaClinicaGeneralBO().getNroContrato() != 0) {
					if (hcForm.getHistoriaClinicaGeneralBO().getNroContrato()
							.equals(listaForm.get(i).getHistoriaClinicaGeneralBO().getNroContrato())) {
						correcto = false;
						Mensaje mensaje = new Mensaje();
						mensaje.setTipo(3);
						mensaje.setDescripcion("SE REPITE CONTRATO EN EXCEL: "
								+ hcForm.getHistoriaClinicaGeneralBO().getNroContrato());
						mensaje.setResultado(hcForm);
						mensajes.add(mensaje);
					}
				}
			}
			i++;
		}
		return correcto;
	}

	@Override
	public List<HistoriaClinicaForm> agregarHistoriasImportadas(List<HistoriaClinicaForm> listaForms, Mensaje mensaje) {

		List<HistoriaClinicaForm> agregados = new ArrayList<>();
		int cantidadAgregados = 0;
		int i = 0;

		for (HistoriaClinicaForm historiaForm : listaForms) {

			try {
				// para comprobar que la fila del excel tiene datos
				if (!historiaForm.getHistoriaClinicaGeneralBO().getApellidoPatPaciente().trim().isEmpty()) {

					i++;
					if (!existeEnBD(historiaForm)) {

						cantidadAgregados++;
						HistoriaClinica historiaClinica = HistoriaClinicaTransformer
								.transformHistoriaFormToHistoriaClinica(historiaForm);

						Ubigeo ubigeo = null;
						String nombreDistrito = historiaForm.getDatosPersonaBO().getDistrito();

						if (nombreDistrito != null && !nombreDistrito.trim().isEmpty()) {

							List<Object[]> listResult = ubigeoRepository.obtenerUbigeosPorNombre(nombreDistrito);
							if (listResult != null) {

								Object[] o = listResult.get(0);
								ubigeo = new Ubigeo();

								ubigeo.setIdUbigeo(AppUtil.ObjectToShort(o[0]));
								historiaClinica.getPaciente().getPersona().getDatosPersona()
										.setUbigeo1(ubigeo);
							}
						}

						registrarHistoriaClinica(historiaClinica);

						///////////////////////////////////////////////////////////

						String codigoAlumno = historiaForm.getAsignacionForm().getCodigoAlumno();
						Alumno alumno = alumnoRepository.findByCodigoAlumno(codigoAlumno);

						if (alumno != null) {

							Asignacion asignacion = new Asignacion();
							asignacion.setFechaInicio(null);
							asignacion.setAlumno(alumno);

							Integer nroContrato = historiaForm.getHistoriaClinicaGeneralBO().getNroContrato();

							if (nroContrato != null && nroContrato != 0) {

								ContratoPre contrato = new ContratoPre();
								contrato.setNroContrato(nroContrato);
								contrato.setFecha(historiaForm.getHistoriaClinicaGeneralBO().getFechaContrato());
								contrato.setHistoriaClinica(historiaClinica);

								contratoPreRepository.saveAndFlush(contrato);

								asignacion.setEstado(historiaForm.getAsignacionForm().getEstado());
								asignacion.setHistoriaClinica(historiaClinica);

								asignacionRepository.saveAndFlush(asignacion);
							}
						}

						agregados.add(historiaForm);
					}
				}

			} catch (Exception e) {
				System.out.println("Error en agregar importados");
				System.out.println(e.getMessage());
			}
		}
		mensaje.setDescripcion("Se agregaron " + cantidadAgregados + "/" + i + " Historias Clinicas");
		return null;
	}

	private boolean existeEnBD(HistoriaClinicaForm hcForm) {

		boolean existe = false;
		String dni = hcForm.getHistoriaClinicaGeneralBO().getDniPaciente();
		int nroHC = hcForm.getHistoriaClinicaGeneralBO().getNroHC();

		if (personaRepository.exists(dni)) {
			Mensaje mensaje = new Mensaje();
			mensaje.setTipo(4);
			mensaje.setDescripcion("YA EXISTE PACIENTE CON DNI: " + dni);
			mensaje.setResultado(hcForm);
			existe = true;
		}
		if (historiaClinicaRepository.exists(nroHC)) {
			Mensaje mensaje = new Mensaje();
			mensaje.setTipo(4);
			mensaje.setDescripcion("YA EXISTE HISTORIA CLINICA CON N°: " + nroHC);
			mensaje.setResultado(hcForm);
			existe = true;
		}

		return existe;
	}

}
