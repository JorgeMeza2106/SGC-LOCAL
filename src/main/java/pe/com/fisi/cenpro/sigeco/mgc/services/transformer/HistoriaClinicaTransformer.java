package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import static pe.com.fisi.cenpro.sigeco.mgc.utils.Constantes.SPLIT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.HistoriaClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Asignacion;
import pe.com.fisi.cenpro.sigeco.mgc.domain.ContratoPre;
import pe.com.fisi.cenpro.sigeco.mgc.domain.DatosPersona;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HistoriaClinica;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Paciente;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Persona;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Ubigeo;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsignacionDetalleBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DatosPersonaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;;

/**
 * @author CENTRO PRODUCCIÓN
 *
 */
@Service
public class HistoriaClinicaTransformer
		implements Transformer<HistoriaClinica, HistoriaClinicaBO, HistoriaClinicaForm> {

	@Override
	public HistoriaClinica transformToE(HistoriaClinicaForm f) {

		HistoriaClinica eHc = new HistoriaClinica();
		Paciente paciente = new Paciente();
		Persona persona = new Persona();
		DatosPersona dPersona = new DatosPersona();

		persona.setDniPersona(f.getHistoriaClinicaGeneralBO().getDniPaciente());
		persona.setNombre(f.getHistoriaClinicaGeneralBO().getNombrePaciente());
		persona.setApellidoPaterno(f.getHistoriaClinicaGeneralBO().getApellidoPatPaciente());
		persona.setApellidoMaterno(f.getHistoriaClinicaGeneralBO().getApellidoMatPaciente());

		// dPersona.setDniPersona(f.getHistoriaClinicaGeneralBO().getDniPaciente());

		dPersona.setDomicilio(f.getDatosPersonaBO().getDomicilio());
		dPersona.setOcupacion(f.getDatosPersonaBO().getOcupacion());
		dPersona.setTelefonoFijo(f.getDatosPersonaBO().getTelefonoFijo());
		dPersona.setTelefonoCelular(f.getDatosPersonaBO().getTelefonoCelular());
		dPersona.setFechaNacimiento(f.getDatosPersonaBO().getFechaNacimiento());
		dPersona.setEstadoCivil(f.getDatosPersonaBO().getEstadoCivil());
		dPersona.setSexo(f.getDatosPersonaBO().getSexo());
		dPersona.setPersona(persona);// vinculamos a dPersona con Persona.

		System.out.println("aquiiiiiiiiiiiii1");
		persona.setDatosPersona(dPersona);// Vinculamos a persona con dPersona.
		System.out.println(persona.getDniPersona());

		// paciente.setDniPersona(f.getHistoriaClinicaGeneralBO().getDniPaciente());
		paciente.setPersona(persona);

		// Creando la HC
		eHc.setNroHC(f.getHistoriaClinicaGeneralBO().getNroHC());
		eHc.setFecha(f.getHistoriaClinicaGeneralBO().getFechaHC());
		System.out.println("fecha transformer" + ": " + eHc.getFecha());
		eHc.setPaciente(paciente);
		System.out.println("Aquiiiiiiiiiiiiiiii2");
		return eHc;
	}

	@Override
	public HistoriaClinicaBO transformToBO(HistoriaClinica hc) {

		HistoriaClinicaBO hcBO = new HistoriaClinicaBO();
		HistoriaClinicaGeneralBO hcgBO = transformToSimpleBO(hc);
		DatosPersonaBO dpBO = new DatosPersonaBO();

		DatosPersona dp = hc.getPaciente().getPersona().getDatosPersona();
		dpBO.setSexo(dp.getSexo());
		dpBO.setEmail(dp.getEmail());
		dpBO.setDomicilio(dp.getDomicilio());
		dpBO.setEstadoCivil(dp.getEstadoCivil());
		dpBO.setFechaNacimiento(dp.getFechaNacimiento());
		dpBO.setOcupacion(dp.getOcupacion());
		dpBO.setTelefonoCelular(dp.getTelefonoCelular());
		dpBO.setTelefonoFijo(dp.getTelefonoFijo());
		/* Construyendo los ubigeos con formato "01/12/15" */
		StringBuilder ubigeoNacimiento = new StringBuilder();
		ubigeoNacimiento.append(dp.getUbigeo2().getDepartamentoId()).append(SPLIT)
				.append(dp.getUbigeo2().getProvinciaId()).append(SPLIT)
				.append(dp.getUbigeo2().getDistritoId());
		dpBO.setUbigeoNacimiento(ubigeoNacimiento.toString());

		StringBuilder ubigeoProcedencia = new StringBuilder();
		ubigeoProcedencia.append(dp.getUbigeo1().getDepartamentoId()).append(SPLIT)
				.append(dp.getUbigeo1().getProvinciaId()).append(SPLIT)
				.append(dp.getUbigeo1().getDistritoId());
		dpBO.setUbigeoProcedencia(ubigeoProcedencia.toString());

		List<AsignacionDetalleBO> lad = new ArrayList<>();
		for (Asignacion asig : hc.getAsignacions()) {
			AsignacionDetalleBO adBO = new AsignacionDetalleBO();
			// Operador
			adBO.setApellidoPatOperador(asig.getAlumno().getPersona().getApellidoPaterno());
			adBO.setApellidoMatOperador(asig.getAlumno().getPersona().getApellidoMaterno());
			adBO.setNombreOperador(asig.getAlumno().getPersona().getNombre());
			// Doctor
			if (asig.getDoctor() != null) {
				adBO.setApellidoPatDoctor(asig.getDoctor().getPersona().getApellidoPaterno());
				adBO.setNombreDoctor(asig.getDoctor().getPersona().getNombre());
			}
			// Asignacion
			adBO.setIdAsignacion(asig.getIdAsignacion());
			adBO.setFechaInicio(asig.getFechaInicio());
			adBO.setFechaFin(asig.getFechaFin());
			adBO.setEstado(asig.getEstado());
			lad.add(adBO);
		}

		hcBO.setDatosAdicionalesPaciente(dpBO);
		hcBO.setDatosGeneralesPaciente(hcgBO);
		hcBO.setListaAsignacionDetalle(lad);

		return hcBO;
	}

	public List<HistoriaClinicaGeneralBO> transformToListSimpleBO(List<HistoriaClinica> lhc) {
		List<HistoriaClinicaGeneralBO> lhcBO = new ArrayList<>();
		for (HistoriaClinica hc : lhc) {
			lhcBO.add(transformToSimpleBO(hc));
			System.out.println("aqui hara tanta consulta? diomio :O");

		}
		return lhcBO;
	}

	public HistoriaClinicaGeneralBO transformToSimpleBO(HistoriaClinica hc) {
		HistoriaClinicaGeneralBO hcBO = new HistoriaClinicaGeneralBO();

		hcBO.setNroHC(hc.getNroHC());
		Iterator<ContratoPre> iter = hc.getContratoPres().iterator();

		while (iter.hasNext()) {
			ContratoPre cp = iter.next();
			int anioContrato = DateUtil.getYear(cp.getFecha());
			int anioActual = DateUtil.getActualYear();
			if (anioContrato == anioActual) {
				hcBO.setNroContrato(cp.getNroContrato());
				hcBO.setFechaContrato(cp.getFecha());
			}
		}

		Persona pac = hc.getPaciente().getPersona();

		hcBO.setDniPaciente(pac.getDniPersona());
		hcBO.setNombrePaciente(pac.getNombre());
		hcBO.setApellidoMatPaciente(pac.getApellidoMaterno());
		hcBO.setApellidoPatPaciente(pac.getApellidoPaterno());

		return hcBO;
	}

	@Override
	public List<HistoriaClinica> transformToE(List<HistoriaClinicaBO> lbos) {

		return null;
	}

	@Override
	public List<HistoriaClinicaBO> transformToBO(List<HistoriaClinica> lens) {
		// TODO Auto-generated method stub
		return null;
	}

	// public static HistoriaClinicaGeneralBO transformToBo2(HistoriaClinica
	// historiaClinica) {
	// HistoriaClinicaGeneralBO historiaClinicaBo = null;
	// if (historiaClinica != null) {
	// historiaClinicaBo=new HistoriaClinicaGeneralBO();
	// historiaClinicaBo.setNroHC(historiaClinica.getNroHc());
	// historiaClinicaBo.setNroContrato(getMaxContratoPre(historiaClinica.getContratoPres()));
	// if (historiaClinica.getPaciente() != null &&
	// historiaClinica.getPaciente().getDniPersona() != null) {
	// historiaClinicaBo.setDniPaciente(historiaClinica.getPaciente().getPersona().getDniPersona());
	// historiaClinicaBo
	// .setApellidoPatPaciente(historiaClinica.getPaciente().getPersona().getApellidoPaterno());
	// historiaClinicaBo
	// .setApellidoMatPaciente(historiaClinica.getPaciente().getPersona().getApellidoMaterno());
	// historiaClinicaBo.setNombrePaciente(historiaClinica.getPaciente().getPersona().getNombre());
	// }
	// }
	// return historiaClinicaBo;
	// }

	// private static Integer getMaxContratoPre(Set<ContratoPre> contratoPre) {
	// int nroContratoMax = 0;
	// if (contratoPre.size() != 0) {
	// for (ContratoPre contrato : contratoPre) {
	// if (nroContratoMax < contrato.getNroContrato()) {
	// nroContratoMax = contrato.getNroContrato();
	// }
	// }
	// return nroContratoMax;
	// } else {
	// return null;
	// }
	// }

	public static HistoriaClinicaBO transformToHistoriaClinicaBo(Object[] o) {
		HistoriaClinicaBO historiaClinicaBO = null;
		if (o != null) {

			historiaClinicaBO = new HistoriaClinicaBO();

			HistoriaClinicaGeneralBO historiaClinicaGeneralBO = new HistoriaClinicaGeneralBO();
			historiaClinicaGeneralBO.setNroHC(AppUtil.ObjectToInteger(o[0]));
			historiaClinicaGeneralBO.setFechaHC(AppUtil.ObjectToDate(o[1]));

			historiaClinicaGeneralBO.setNroContrato(AppUtil.ObjectToInteger(o[2]));
			historiaClinicaGeneralBO.setFechaContrato(AppUtil.ObjectToDate(o[3]));

			historiaClinicaGeneralBO.setDniPaciente(AppUtil.ObjectToString(o[4]));
			historiaClinicaGeneralBO.setNombrePaciente(AppUtil.ObjectToString(o[5]));
			historiaClinicaGeneralBO.setApellidoPatPaciente(AppUtil.ObjectToString(o[6]));
			historiaClinicaGeneralBO.setApellidoMatPaciente(AppUtil.ObjectToString(o[7]));

			DatosPersonaBO datosPersonaBo = new DatosPersonaBO();
			datosPersonaBo.setSexo(AppUtil.ObjectToString(o[8]));
			datosPersonaBo.setEmail(AppUtil.ObjectToString(o[9]));
			datosPersonaBo.setTelefonoFijo(AppUtil.ObjectToString(o[10]));
			datosPersonaBo.setTelefonoCelular(AppUtil.ObjectToString(o[11]));
			datosPersonaBo.setOcupacion(AppUtil.ObjectToString(o[12]));
			datosPersonaBo.setDomicilio(AppUtil.ObjectToString(o[13]));
			datosPersonaBo.setEstadoCivil(AppUtil.ObjectToString(o[14]));
			datosPersonaBo.setFechaNacimiento(AppUtil.ObjectToDate(o[15]));

			if (o[16] != null) {
				Ubigeo ubiNac = (Ubigeo) o[16];				

				StringBuilder ubigeoNacimiento = new StringBuilder();
				ubigeoNacimiento.append(ubiNac.getDepartamentoId()).append(SPLIT).append(ubiNac.getProvinciaId())
						.append(SPLIT).append(ubiNac.getDistritoId());
				datosPersonaBo.setUbigeoNacimiento(ubigeoNacimiento.toString());
			}
			if (o[17] != null) {
				Ubigeo ubiPro = (Ubigeo) o[17];
				
				StringBuilder ubigeoProcedencia = new StringBuilder();
				ubigeoProcedencia.append(ubiPro.getDepartamentoId()).append(SPLIT).append(ubiPro.getProvinciaId())
						.append(SPLIT).append(ubiPro.getDistritoId());
				datosPersonaBo.setUbigeoProcedencia(ubigeoProcedencia.toString());
			}

			historiaClinicaBO.setDatosGeneralesPaciente(historiaClinicaGeneralBO);
			historiaClinicaBO.setDatosAdicionalesPaciente(datosPersonaBo);
		}
		return historiaClinicaBO;
	}

	public static List<HistoriaClinicaGeneralBO> transformToListHistoriaGeneralBo(List<Object[]> listObjects) {
		List<HistoriaClinicaGeneralBO> listHistoriClinicaGeneralBo = null;

		if (listObjects != null) {
			listHistoriClinicaGeneralBo = new ArrayList<HistoriaClinicaGeneralBO>();

			for (Object[] o : listObjects) {
				HistoriaClinicaGeneralBO historia = new HistoriaClinicaGeneralBO();

				historia.setNroHC(AppUtil.ObjectToInteger(o[0]));
				historia.setFechaHC(AppUtil.ObjectToDate(o[1]));
				historia.setDniPaciente(AppUtil.ObjectToString(o[2]));
				historia.setNombrePaciente(AppUtil.ObjectToString(o[3]));
				historia.setApellidoPatPaciente(AppUtil.ObjectToString(o[4]));
				historia.setApellidoMatPaciente(AppUtil.ObjectToString(o[5]));
				historia.setNroContrato(AppUtil.ObjectToInteger(o[6]));
				historia.setFechaContrato(AppUtil.ObjectToDate(o[7]));

				listHistoriClinicaGeneralBo.add(historia);
			}
		}

		return listHistoriClinicaGeneralBo;
	}

	public static HistoriaClinica transformHistoriaFormToHistoriaClinica(HistoriaClinicaForm historiaForm) {

		HistoriaClinica historiaClinica = new HistoriaClinica();
		Paciente paciente = new Paciente();
		Persona persona = new Persona();
		DatosPersona datosPersona = new DatosPersona();

		persona.setDniPersona(historiaForm.getHistoriaClinicaGeneralBO().getDniPaciente());
		persona.setNombre(historiaForm.getHistoriaClinicaGeneralBO().getNombrePaciente());
		persona.setApellidoPaterno(historiaForm.getHistoriaClinicaGeneralBO().getApellidoPatPaciente());
		persona.setApellidoMaterno(historiaForm.getHistoriaClinicaGeneralBO().getApellidoMatPaciente());

		datosPersona.setDomicilio(historiaForm.getDatosPersonaBO().getDomicilio());
		datosPersona.setOcupacion(historiaForm.getDatosPersonaBO().getOcupacion());
		datosPersona.setTelefonoFijo(historiaForm.getDatosPersonaBO().getTelefonoFijo());
		datosPersona.setTelefonoCelular(historiaForm.getDatosPersonaBO().getTelefonoCelular());
		datosPersona.setFechaNacimiento(historiaForm.getDatosPersonaBO().getFechaNacimiento());
		datosPersona.setEstadoCivil(historiaForm.getDatosPersonaBO().getEstadoCivil());
		datosPersona.setSexo(historiaForm.getDatosPersonaBO().getSexo());
		datosPersona.setEmail(historiaForm.getDatosPersonaBO().getEmail());

		datosPersona.setPersona(persona);
		datosPersona.setDniPersona(historiaForm.getHistoriaClinicaGeneralBO().getDniPaciente());
		persona.setDatosPersona(datosPersona);

		paciente.setPersona(persona);
		paciente.setDniPaciente(historiaForm.getHistoriaClinicaGeneralBO().getDniPaciente());

		historiaClinica.setNroHC(historiaForm.getHistoriaClinicaGeneralBO().getNroHC());
		historiaClinica.setFecha(historiaForm.getHistoriaClinicaGeneralBO().getFechaHC());

		historiaClinica.setPaciente(paciente);

		return historiaClinica;
	}
}
