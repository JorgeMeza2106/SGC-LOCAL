package pe.com.fisi.cenpro.sigeco.mgc.utils;

import static pe.com.fisi.cenpro.sigeco.mgc.utils.ServiceConstants.*;

public class Mensajes {

	public static final String M_FECHA_RESERVA_NO_VALIDA = "No se puede programar una cita con menos de "
			+ MIN_DIAS_ANTI_PERMITIDA + " (L-V) / " + MIN_DIAS_ANTI_PERMITIDA_SABADO + "(S) " + "o más de "
			+ MAX_DIAS_ANTI_PERMITIDA + "días de anticipación.";

	public static final String M_CITA_CORRECTA = "Se registró la cita con éxito.";

	public static final String M_MAXIMO_CITAS_ALCANZADO = "Máximo número de citas alcanzadas por día (" + MAXIMO_CITAS_DIA
			+ ").";

	public static final String M_EXISTE_CITA_PACIENTE_DIA = " Ya se programó una cita con el paciente en la fecha seleccionada.";

	public static final String M_FECHA_ATENCION_DOMINGO = "No se puede programar citas para el domingo.";

	public static final String M_FECHA_ATENCION_FERIADO = "El día seleccionado es feriado.";

	public static final String M_FECHA_ATENCION_NO_VALIDA = "La fecha de atención no es valida. ";

	public static final String ERROR_NO_IDENTIFICADO = "Error no identificado. Contacte con el administrador de sistema.";

	public static final String M_HORA_RESERVA_NO_PERMITIDA = 
			"Solo se pueden sacar citas para el día seleccionado, antes de las " + MAXIMA_HORA_PERMITIDA + " pm.";

	public static final String M_CITA_ADICIONAL_NO_VALIDA = "La cita programada no es una cita adicional.";

	public static final String M_ADVERTENCIA_CITAS_NO_VALIDADA = 
			"La cita no ha sido validada con las reglas del sistema."
			+ "Use esta opción solo para casos excepcionales.";

	public static final String M_CITA_ADICIONAL_CORRECTA = "La cita adicional fue registrada con éxito.";
	
	public static final String M_CITA_ADICIONAL_NOTIFICACION = "Se ha registrado una cita adicional.";
	
	public static final String M_ASISTENCIA_VALIDA = "La asistencia fue registrada con éxito.";
	
	public static final String M_ASISTENCIA_NO_VALIDA = "El registro de la asistencia no es valido"
														+" en la clinica, el turno o la fecha. "	;
	public static final String M_ASISTENCIA_NO_HORARIO = "No existe el horario";

	public static final String M_CITA_TURNO_NO_VALIDO = "El turno seleccionado no le corresponde.";
	
	public static final String M_CONTRATO_GENERADO = "Se generó un nuevo contrato";
	
	public static final String M_CONTRATO_NO_GENERADO = "No se pudo generar nuevo contrato";
	
	public static final String M_CONTRATO_ELIMINADO = "Se eliminó el contrato";
	
	public static final String M_CONTRATO_DESHABILITADO = "El contrato ha sido deshabilitado";
	
	public static final String M_CONTRATO_NO_ELIMINADO = "No se pudo eliminar el contrato";
	
	public static final String M_CONTRATO_NO_DESHABILITADO = "No se puedo sido deshabilitar el contrato";
	
	public static final String evaluarResultado(int resultado) {
		// TODO Auto-generated method stub
		switch (resultado) {
		case FECHA_ATENCION_DOMINGO:
			return M_FECHA_ATENCION_DOMINGO;
		case FECHA_ATENCION_FERIADO:
			return M_FECHA_ATENCION_FERIADO;
		case FECHA_ATENCION_NO_VALIDA:
			return M_FECHA_ATENCION_NO_VALIDA;
		default:
			return ERROR_NO_IDENTIFICADO;
		}
	}

}
