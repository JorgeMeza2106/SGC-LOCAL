package pe.com.fisi.cenpro.sigeco.mgc.controller.form;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoMovimiento;
import pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoPersonal;
import pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoPrograma;

@Builder
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AsistenciaPersonalFormBO {
	 String dniPersonaAsistente;
	 String dniAdministrativoClinica;
	 TipoPersonal tipoPersonal;
	 TipoPrograma tipoPrograma;
	 TipoMovimiento tipoMovimiento;
	 LocalDateTime fechaHoraMovimiento;
	 Short idHorarioClinica;
	
}
