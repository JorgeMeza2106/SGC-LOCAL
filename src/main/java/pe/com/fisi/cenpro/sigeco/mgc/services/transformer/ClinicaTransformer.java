package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Clinica;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;

@Service
public class ClinicaTransformer implements Transformer<Clinica, ClinicaBO, ClinicaBO> {

	@Override
	public Clinica transformToE(ClinicaBO f) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public ClinicaBO transformToBO(Clinica e) {
		// TODO Auto-generated method stub
		ClinicaBO clinicaBO = new ClinicaBO(e.getIdClinica(), e.getNombre());
		return clinicaBO;
	}

	@Override
	public List<Clinica> transformToE(List<ClinicaBO> lbos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClinicaBO> transformToBO(List<Clinica> lens) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ClinicaBO transformToBo(Clinica clinica) {
		ClinicaBO clinicaBO = null;
		if (clinica != null) {
			clinicaBO = new ClinicaBO();
			clinicaBO.setIdClinica(clinica.getIdClinica());
			clinicaBO.setNombre(clinica.getNombre());
		}
		return clinicaBO;
	}

	
	public static List<ClinicaBO> transformToListBo(List<Clinica> listClinica){
		List<ClinicaBO> listClinicaBo=null;
		if(listClinica!=null){
			listClinicaBo  = new ArrayList<ClinicaBO>();
			for(Clinica clinica : listClinica){
				listClinicaBo.add(transformToBo(clinica));
			}
		}
		return listClinicaBo;
	}
}
