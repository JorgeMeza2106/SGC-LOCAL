package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.services.AsignacionService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaAsistenciaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaEventoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.PapeletaBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;

@Service
@Qualifier("citaTransformer")
public class CitaTransformer {

	@Autowired
	AsignacionService asignacionService;

	public static List<CitaBO> transformObjectsToListBo(List<Object[]> listObjects) {

		List<CitaBO> listaCitas = new ArrayList<CitaBO>();
		for (Object[] object : listObjects) {
			listaCitas.add(transformObjectToBo(object));
		}
		return listaCitas;
	}
	
	public static List<CitaEventoBO> transformObjectsToListCitaEventoBo(List<Object[]> listObjects) {

		List<CitaEventoBO> listaCitas = new ArrayList<CitaEventoBO>();
		for (Object[] object : listObjects) {
			listaCitas.add(transformObjectToCitaEventoBo(object));
		}
		return listaCitas;
	}

	public static List<CitaAsistenciaBO> transformObjectsToListAsistenciaBo(List<Object[]> listObjects) {

		List<CitaAsistenciaBO> listaCitas = new ArrayList<CitaAsistenciaBO>();
		for (Object[] object : listObjects) {
			listaCitas.add(transformObjectAsistenciaToBo(object));
		}
		return listaCitas;
	}

	public static CitaBO transformObjectToBo(Object[] objects) {
		CitaBO citaBO = null;
		if (objects != null) {
			citaBO = new CitaBO();
			citaBO.setIdCita(AppUtil.ObjectToInteger(objects[0]));
			citaBO.setNumeroHC(AppUtil.ObjectToInteger(objects[1]));
			citaBO.setContrato(AppUtil.ObjectToInteger(objects[2]));
			citaBO.setOperador(AppUtil.ObjectToString(objects[5]) + " " + AppUtil.ObjectToString(objects[6]));
			citaBO.setPaciente(AppUtil.ObjectToString(objects[3]) + " " + AppUtil.ObjectToString(objects[4]));
			citaBO.setCurso(AppUtil.ObjectToString(objects[7]));
			if (objects[8] != null) {
				citaBO.setAnio(AppUtil.ObjectToByte(objects[8]));
			}
			// citaBO.setTurno(AppUtil.ObjectToChar(objects[9]));
			citaBO.setFechaAtencion(AppUtil.ObjectToDate(objects[11]));
			citaBO.setClinica(AppUtil.ObjectToString(objects[12]));
			citaBO.setEstado(AppUtil.ObjectToString(objects[15]));
			citaBO.setDniPaciente(AppUtil.ObjectToString(objects[16]));
		}
		return citaBO;
	}

	public static CitaAsistenciaBO transformObjectAsistenciaToBo(Object[] objects) {
		CitaAsistenciaBO citaBO = null;
		if (objects != null) {
			citaBO = new CitaAsistenciaBO();
			citaBO.setIdCita(AppUtil.ObjectToInteger(objects[0]));
			citaBO.setNumeroHC(AppUtil.ObjectToInteger(objects[1]));
			citaBO.setContrato(AppUtil.ObjectToInteger(objects[2]));
			citaBO.setOperador(AppUtil.ObjectToString(objects[5]) + " " + AppUtil.ObjectToString(objects[6]));
			citaBO.setPaciente(AppUtil.ObjectToString(objects[3]) + " " + AppUtil.ObjectToString(objects[4]));
			citaBO.setCurso(AppUtil.ObjectToString(objects[7]));
			citaBO.setFechaAtencion(AppUtil.ObjectToDate(objects[10]));
			citaBO.setClinica(AppUtil.ObjectToString(objects[11]));
			citaBO.setDniPaciente(AppUtil.ObjectToString(objects[15]));
			citaBO.setFechaAsistencia(AppUtil.ObjectToTime(objects[16]));
		}
		return citaBO;
	}
	
	public static CitaEventoBO transformObjectToCitaEventoBo(Object[] objects) {
		CitaEventoBO citaevento_BO = null;
		if (objects != null) {
			citaevento_BO = new CitaEventoBO();
			
			citaevento_BO.setId(AppUtil.ObjectToInteger(objects[0]));
			citaevento_BO.setTitle("Cita con paciente: "+ AppUtil.ObjectToInteger(objects[1]));
			citaevento_BO.setStart((AppUtil.ObjectToDate(objects[11])));
			/*
			citaevento_BO.setEnd(id);
			*/
			citaevento_BO.setDescripcion("Pruebita con fe");
		}
		return citaevento_BO;
	}

	public static PapeletaBO transformObjectToPapeletaBo(Object[] o) {
		PapeletaBO papeletaBo = null;
		if (o != null) {
			papeletaBo = new PapeletaBO();

			papeletaBo.setTurno(AppUtil.getTurno(o[0]));
			papeletaBo.setClinica(AppUtil.ObjectToString(o[1]));
			papeletaBo.setHistoriaClinica(AppUtil.ObjectToString(o[2]));

			papeletaBo.setDni(AppUtil.ObjectToString(o[3]));
			papeletaBo.setPaciente(AppUtil.getNombreCompleto(o[4], o[5], o[6]));
			papeletaBo.setEdad(AppUtil.getEdad(o[7]));
			papeletaBo.setDistrito(AppUtil.ObjectToString(o[8]));

			papeletaBo.setEspecialidad(AppUtil.ObjectToString(o[9]));

			papeletaBo.setCie(" ");
			papeletaBo.setFecha(AppUtil.getFechaActual());
			papeletaBo.setHora(AppUtil.getHoraActual());
			papeletaBo.setNumeroPapeleta(" ");

		}

		return papeletaBo;
	}
}
