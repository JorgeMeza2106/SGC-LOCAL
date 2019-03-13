package pe.com.fisi.cenpro.sigeco.mgc.services.transformer;

import static pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoMovimiento.ENTRADA;
import static pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoMovimiento.SALIDA;

import java.sql.Time;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsistenciaPersonalFormBO;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Asistencia;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HorarioClinica;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Persona;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;

public class AsistenciaTransformer {
	
	public static Asistencia transformToAsistencia(AsistenciaPersonalFormBO asistenciaPersonalBO){
		 Asistencia.AsistenciaBuilder asistenciaBuilder = Asistencia.builder()
			.fecha(DateUtil.toDate(asistenciaPersonalBO.getFechaHoraMovimiento()))
			.persona(Persona.builder()
						.dniPersona(asistenciaPersonalBO.getDniPersonaAsistente())
						.build())
		    .horarioClinica(HorarioClinica.builder()
		    			.idHorarioClinica(asistenciaPersonalBO.getIdHorarioClinica())
		    			.build());
		if (asistenciaPersonalBO.getTipoMovimiento().equals(ENTRADA)) {
			asistenciaBuilder
					.horaEntrada(new Time(DateUtil.toMilliSeconds(asistenciaPersonalBO.getFechaHoraMovimiento())));
		} else if (asistenciaPersonalBO.getTipoMovimiento().equals(SALIDA)) {
			asistenciaBuilder
					.horaSalida(new Time(DateUtil.toMilliSeconds(asistenciaPersonalBO.getFechaHoraMovimiento())));
		}
		return asistenciaBuilder.build();	
	}
	
	
		
}
