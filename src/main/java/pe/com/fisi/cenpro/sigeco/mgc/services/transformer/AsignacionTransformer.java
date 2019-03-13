package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsignacionBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsignacionDetalleBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;

@Service
@Qualifier("asignacionTransformer")
public class AsignacionTransformer{

//	@Autowired
//	@Qualifier("alumnoServiceImplDeprecated")
//	AlumnoServiceDeprecated alumnoService;
//
//	@Autowired
//	@Qualifier("doctorServiceImplDeprecated")
//	DoctorServiceDeprecated doctorService;
//
//	@Autowired
//	@Qualifier("historiaClinicaServiceImplDeprecated")
//	HistoriaClinicaServiceDeprecated historiaClinicaService;

//	@Override
//	public Asignacion transformToE(AsignacionForm f) {
//		Asignacion asig = new Asignacion();
//		asig.setAlumno(alumnoService.read(alumnoService.obtenerPorCodigo(f.getCodigoAlumno())));
//		asig.setDoctor(doctorService.read(f.getDniDoctor()));
//		asig.setEstado(f.getEstado());
//		asig.setFechaInicio(f.getFechaInicio());
//		asig.setHistoriaClinica(historiaClinicaService.read(f.getNroHC()));
//		return asig;
//	}

//	@Override
//	public AsignacionBO transformToBO(Asignacion e) {
//		AsignacionBO asigBO = new AsignacionBO();
//
//		HistoriaClinicaTransformer transformerHC = new HistoriaClinicaTransformer();
//
//		HistoriaClinicaGeneralBO hcgBO = transformerHC.transformToSimpleBO(e.getHistoriaClinica());
//
//		asigBO.setDatosHistoria(hcgBO);
//		asigBO.setEstado(e.getEstado());
//		asigBO.setIdAsignacion(e.getIdAsignacion());
//		return null;
//	}

//	@Override
//	public List<Asignacion> transformToE(List<AsignacionBO> lbos) {
//
//		return null;
//	}

//	@Override
//	public List<AsignacionBO> transformToBO(List<Asignacion> lens) {
//		// cuando se trata de una lista debe ser a traves de una consulta join
//		List<AsignacionBO> listaAsignacionesBO = new ArrayList<>();
//		for (Asignacion asignacion : lens) {
//			listaAsignacionesBO.add(transformToBO(asignacion));
//		}
//		return listaAsignacionesBO;
//	}

	// public static AsignacionBO transformToBo(Asignacion asignacion) {
	// AsignacionBO asignacionBO = null;
	// if (asignacion != null) {
	// asignacionBO = new AsignacionBO();
	// asignacionBO.setIdAsignacion(asignacion.getIdAsignacion());
	// asignacionBO.setEstado(asignacion.getEstado());
	// asignacionBO.setDatosHistoria(HistoriaClinicaTransformer.transformToBo2(asignacion.getHistoriaClinica()));
	// }
	// return asignacionBO;
	// }

	// public static List<AsignacionBO> transformToListBo(List<Asignacion>
	// listAsignacion) {
	// List<AsignacionBO> listAsignacionBO = new ArrayList<AsignacionBO>();
	// for (Asignacion asignacion : listAsignacion) {
	// listAsignacionBO.add(transformToBo(asignacion));
	// }
	// return listAsignacionBO;
	// }

	public static List<AsignacionBO> transformObjectsToListBo(List<Object[]> listObjects) {
		List<AsignacionBO> listAsignacionBO = new ArrayList<AsignacionBO>();
		for (Object[] object : listObjects) {
			listAsignacionBO.add(transformObjectToBo(object));
		}
		return listAsignacionBO;
	}

	public static AsignacionBO transformObjectToBo(Object[] object) {
		AsignacionBO asignacionBO = null;
		if (object != null) {
			asignacionBO = new AsignacionBO();
			asignacionBO.setIdAsignacion(AppUtil.ObjectToInteger(object[0]));
			asignacionBO.setEstado(AppUtil.ObjectToString(object[1]));

			HistoriaClinicaGeneralBO historiaClinicaBo = new HistoriaClinicaGeneralBO();
			historiaClinicaBo.setNroHC(AppUtil.ObjectToInteger(object[2]));
			historiaClinicaBo.setNroContrato(AppUtil.ObjectToInteger(object[3]));
			historiaClinicaBo.setDniPaciente(AppUtil.ObjectToString(object[4]));
			historiaClinicaBo.setApellidoPatPaciente(AppUtil.ObjectToString(object[5]));
			historiaClinicaBo.setApellidoMatPaciente(AppUtil.ObjectToString(object[6]));
			historiaClinicaBo.setNombrePaciente(AppUtil.ObjectToString(object[7]));

			asignacionBO.setDatosHistoria(historiaClinicaBo);
		}
		return asignacionBO;
	}
	
	public static List<AsignacionDetalleBO> transformListObjectToAsignacionDetalleBo(List<Object[]> listObject){
		List<AsignacionDetalleBO> listAsignacionDetalleBo = null;
		if(listObject!=null){
			
			listAsignacionDetalleBo  = new ArrayList<AsignacionDetalleBO>();
			for(Object[] o : listObject){
				listAsignacionDetalleBo.add(transformToAsignacionDetalleBo(o));
			}
		}
		return listAsignacionDetalleBo;
	}
	
	public static AsignacionDetalleBO transformToAsignacionDetalleBo(Object[] o){
		AsignacionDetalleBO asignacionDetalleBo = null;
		if(o!=null){
			
			asignacionDetalleBo = new AsignacionDetalleBO();
			asignacionDetalleBo.setIdAsignacion(AppUtil.ObjectToInteger(o[0]));
			asignacionDetalleBo.setFechaInicio(AppUtil.ObjectToDate(o[1]));
			asignacionDetalleBo.setFechaFin(AppUtil.ObjectToDate(o[2]));
			asignacionDetalleBo.setEstado(AppUtil.ObjectToString(o[3]));
			asignacionDetalleBo.setNombreOperador(AppUtil.ObjectToString(o[4]));
			asignacionDetalleBo.setApellidoPatOperador(AppUtil.ObjectToString(o[5]));
			asignacionDetalleBo.setApellidoMatOperador(AppUtil.ObjectToString(o[6]));
			asignacionDetalleBo.setNombreDoctor(AppUtil.ObjectToString(o[7]));
			asignacionDetalleBo.setApellidoPatDoctor(AppUtil.ObjectToString(o[8]));
		}
		return asignacionDetalleBo;
	}
}
