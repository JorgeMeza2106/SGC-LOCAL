package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.repository.HorarioClinicaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.HorarioClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaAdministrativoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.HorarioClinicaTransformer;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;

@Service
public class HorarioClinicaServiceImpl implements HorarioClinicaService {

	@Autowired
	private HorarioClinicaRepository horarioClinicaRepository;

	@Override
	public List<HorarioClinicaBO> obtenerTodosHorarios() {
		return HorarioClinicaTransformer.transformObjectsToListBo(horarioClinicaRepository.obtenerHorarios());
	}

	@Override
	public List<HorarioClinicaAdministrativoBO> buscarHorariosClinicosPorAdministrativo(String dniAdministrativo) {
		return HorarioClinicaTransformer.transformToHorarioClinicaAdministrativoBO(
				horarioClinicaRepository.buscarHorariosPorAdministrativo(dniAdministrativo));
	}

	@Override
	public Optional<HorarioClinicaAdministrativoBO> buscarHorarioClinicoActualPorAdministrativo(
			String dniAdministrativo, LocalDateTime fechaHoraRegistro) {
		LocalTime horaActual = fechaHoraRegistro.toLocalTime();
		LocalDate fechaActual = fechaHoraRegistro.toLocalDate();
		List<HorarioClinicaAdministrativoBO> lsHorarioClinicaAdministrativoBO = buscarHorariosClinicosPorAdministrativo(
				dniAdministrativo);
		System.out.println("esto sera como yo diga: " + lsHorarioClinicaAdministrativoBO.stream().filter(hca ->
				DateUtil.isBetween(horaActual, hca.getInicioTurno(), hca.getFinTurno().plusMinutes(45))
				&& DateUtil.isSameDay(hca.getDiaHorarioClinica(), fechaActual))
				.findFirst());
		return lsHorarioClinicaAdministrativoBO.stream().filter(hca ->
				DateUtil.isBetween(horaActual, hca.getInicioTurno(), hca.getFinTurno().plusMinutes(45))
				&& DateUtil.isSameDay(hca.getDiaHorarioClinica(), fechaActual))
				.findFirst();
	}

}
