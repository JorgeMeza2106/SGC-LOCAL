package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.time.LocalTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class HorarioClinicaAdministrativoBO {
	Short idHorarioClinica;
	String diaHorarioClinica;
	String nombreClinica;
	String descripcionTurno;
	LocalTime inicioTurno;
	LocalTime finTurno;
}
