package pe.com.fisi.cenpro.sigeco.mgc.services;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsistenciaPersonalFormBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsistenciaBO;

public interface AsistenciaService {

	public String registrarAsistencia(AsistenciaBO asistenciaBO);

	public void registrarAsistencia(AsistenciaPersonalFormBO asistenciaPersonalFormBo);

	public String buscarNombreCompletoPersonal(String dni);
}
