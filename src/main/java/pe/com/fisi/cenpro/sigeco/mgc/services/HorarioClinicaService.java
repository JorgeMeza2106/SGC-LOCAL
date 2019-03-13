package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaAdministrativoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaBO;

public interface HorarioClinicaService {

	public List<HorarioClinicaBO> obtenerTodosHorarios();
	public List<HorarioClinicaAdministrativoBO> buscarHorariosClinicosPorAdministrativo(String dniAdministrativo);
	public Optional<HorarioClinicaAdministrativoBO> buscarHorarioClinicoActualPorAdministrativo(String dniAdministrativo, LocalDateTime fechaHoraRegistro);

	
	
}
