package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Doctor;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DoctorBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;

public class DoctorTransformer implements Transformer<Doctor, DoctorBO, DoctorBO> {

	@Override
	public Doctor transformToE(DoctorBO f) {
		
		return null;
	}

	@Override
	public DoctorBO transformToBO(Doctor doctor) {
		DoctorBO doc = new DoctorBO();
		doc.setApellidoMaterno(doctor.getPersona().getApellidoMaterno());
		doc.setApellidoPaterno(doctor.getPersona().getApellidoPaterno());
		doc.setDni(doctor.getDniDoctor());
		doc.setNombre(doctor.getPersona().getNombre());
		return doc;
	}

	@Override
	public List<Doctor> transformToE(List<DoctorBO> lbos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DoctorBO> transformToBO(List<Doctor> lens) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<DoctorBO> transformListObjectToListBo(List<Object[]> listObject){
		List<DoctorBO> listBo = null;
		if(listObject!=null){
			listBo = new ArrayList<DoctorBO>();
			for(Object[] o : listObject){
				listBo.add(transformObjectToBo(o));
			}
		}
		return listBo;
	}

	public static DoctorBO transformObjectToBo(Object[] o){
		DoctorBO doctorBO = null;
		if(o!=null){
			doctorBO = new DoctorBO();
			doctorBO.setDni(AppUtil.ObjectToString(o[0]));
			doctorBO.setNombre(AppUtil.ObjectToString(o[1]));
			doctorBO.setApellidoPaterno(AppUtil.ObjectToString(o[2]));
			doctorBO.setApellidoMaterno(AppUtil.ObjectToString(o[3]));
		}
		
		return doctorBO;
	}
}
