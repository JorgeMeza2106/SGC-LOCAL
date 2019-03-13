package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Clinica;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HorarioClinica;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsignacionBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaAdministrativoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.TransformerUtil;

@Service
public class HorarioClinicaTransformer implements Transformer<HorarioClinica, HorarioClinicaBO, HorarioClinicaBO> {

	@Autowired
	@Qualifier("clinicaTransformer")
	Transformer<Clinica, ClinicaBO, ClinicaBO> transformerClinica;

	@Override
	public HorarioClinica transformToE(HorarioClinicaBO f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HorarioClinicaBO transformToBO(HorarioClinica e) {

		return null;
	}

	@Override
	public List<HorarioClinica> transformToE(List<HorarioClinicaBO> lbos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HorarioClinicaBO> transformToBO(List<HorarioClinica> lens) {
		List<HorarioClinicaBO> listahorarioClinicaBO = new ArrayList<HorarioClinicaBO>();

		for (HorarioClinica hc : lens) {
			HorarioClinicaBO hcbo = new HorarioClinicaBO();
			ClinicaBO clinicaBO = transformerClinica.transformToBO(hc.getClinica());
			hcbo.setClinicaBO(clinicaBO);
			hcbo.setDia(DateUtil.getNumberDayOfWeek(hc.getDia()));
			hcbo.setIdCurso(hc.getCurso().getIdCurso());
			;
			listahorarioClinicaBO.add(hcbo);
		}

		return listahorarioClinicaBO;
	}

	public static List<HorarioClinicaBO> transformObjectsToListBo(List<Object[]> listObjects) {
		List<HorarioClinicaBO> listHorarioClinicaBO = new ArrayList<HorarioClinicaBO>();
		for (Object[] object : listObjects) {
			listHorarioClinicaBO.add(transformObjectToBo(object));
		}
		return listHorarioClinicaBO;
	}

	public static HorarioClinicaBO transformObjectToBo(Object[] object) {
		HorarioClinicaBO horarioClinicaBO = null;
		if (object != null) {

			horarioClinicaBO = new HorarioClinicaBO();
			horarioClinicaBO.setDia(DateUtil.getNumberDayOfWeek(AppUtil.ObjectToString(object[0])));

			ClinicaBO clinicaBO = new ClinicaBO();
			clinicaBO.setIdClinica(AppUtil.ObjectToInteger(object[1]));
			clinicaBO.setNombre(AppUtil.ObjectToString(object[2]));
			horarioClinicaBO.setClinicaBO(clinicaBO);

			horarioClinicaBO.setIdCurso(AppUtil.ObjectToInteger(object[3]));
		}
		return horarioClinicaBO;
	}

	public static List<HorarioClinicaAdministrativoBO> transformToHorarioClinicaAdministrativoBO(
			List<Map<String, Object>> lsHorarioClinicaAdministrativo) {
		return lsHorarioClinicaAdministrativo.stream()
				.map(HorarioClinicaTransformer::transformToHorarioClinicaAdministrativoBO).collect(Collectors.toList());
	}

	public static HorarioClinicaAdministrativoBO transformToHorarioClinicaAdministrativoBO(
			Map<String, Object> horarioClinicaAdministrativo) {
		return HorarioClinicaAdministrativoBO.builder()
				.idHorarioClinica(TransformerUtil.toPositiveShort(horarioClinicaAdministrativo.get("idHorarioClinica")))
				.diaHorarioClinica(TransformerUtil.toString(horarioClinicaAdministrativo.get("diaHorarioClinica")))
				.nombreClinica(TransformerUtil.toString(horarioClinicaAdministrativo.get("nombreClinica")))
				.inicioTurno(TransformerUtil.toTime(horarioClinicaAdministrativo.get("inicioTurno")).toLocalTime())
				.finTurno(TransformerUtil.toTime(horarioClinicaAdministrativo.get("finTurno")).toLocalTime())
				.descripcionTurno(TransformerUtil.toString(horarioClinicaAdministrativo.get("descripcionTurno")))
				.build();
	}

}
