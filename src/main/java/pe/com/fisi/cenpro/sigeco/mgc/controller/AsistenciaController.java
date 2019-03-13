package pe.com.fisi.cenpro.sigeco.mgc.controller;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsistenciaPersonalFormBO;
import pe.com.fisi.cenpro.sigeco.mgc.controller.validation.Dni;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsistenciaService;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.SecurityUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.ServiceUtil;

@RestController
@Validated
@RequestMapping("/admin_cl/asistencia")
public class AsistenciaController {

	@Autowired
	AsistenciaService asistenciaService;

	@GetMapping(value="/{tipoMovimiento}")
	public String goRegistrarAsistencia(Model model, @PathVariable @NotNull @NotBlank String tipoMovimiento, @Dni  @RequestParam  String dniPersona) {
		LocalDateTime fechaHoraRegistro = DateUtil.getCurrentDateTimeWithTimeZone();
		AsistenciaPersonalFormBO asistenciaPersonalFormBO = 
				AsistenciaPersonalFormBO.builder()
							.dniPersonaAsistente(dniPersona)
							.fechaHoraMovimiento(fechaHoraRegistro)
							.tipoMovimiento(ServiceUtil.convertirTipoMovimiento(tipoMovimiento))
							.dniAdministrativoClinica(SecurityUtil.getDniUsuario())
							.build();
		asistenciaService.registrarAsistencia(asistenciaPersonalFormBO);
		return	DateUtil.getFormattedDate(fechaHoraRegistro);
	}

	@GetMapping(params = "accion=buscarPersona")
	public ResponseEntity<?> goBusquedaPersonal(@Dni @RequestParam  String dniPersona) {
		System.out.println("asi toy llegando ah :p " + dniPersona);
		String nombreCompleto = asistenciaService.buscarNombreCompletoPersonal(dniPersona);
		return nombreCompleto != null ? ResponseEntity.ok(nombreCompleto) : ResponseEntity.notFound().build();
	}
	

}
