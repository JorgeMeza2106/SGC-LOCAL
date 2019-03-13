package pe.com.fisi.cenpro.sigeco.mgc.utils;

import java.time.LocalTime;

public final class ServiceConstants {

	
	public static final int CITA_CORRECTA = 0;
	public static final int EXISTE_CITA_PACIENTE_DIA = -1;
	
	//Validacion de números de citas
	public static final int MAXIMO_CITAS_ALCANZADO = -2;
	public static final int MAXIMO_CITAS_DIA = 3;
	
	//Validacion de fechas de atención
	public static final int FECHA_ATENCION_NO_VALIDA =  130;
	public static final int FECHA_ATENCION_FERIADO = 110;
	public static final int FECHA_ATENCION_DOMINGO = 120;
	public static final int FECHA_ATENCION_VALIDA = 100;
	
	//Validacion de fecha de reserva	
	public static final int FECHA_RESERVA_NO_VALIDA = 210;
	public static final int FECHA_RESERVA_ANTICIPADA = 200;
	
	public static final LocalTime MAXIMA_HORA_PERMITIDA = LocalTime.of(17, 00, 00);
	public static final int DIAS_ANTICIPACION_PARA_RESERVA = 310;
	public static final int HORA_RESTRINGIDA = 320;
	
	public static final int MIN_DIAS_ANTI_PERMITIDA = 1;
	public static final int MAX_DIAS_ANTI_PERMITIDA = 30;
	public static final int MIN_DIAS_ANTI_PERMITIDA_SABADO = 2;
	public static final int CITA_ADICIONAL = 400;

	
	
	public static final int ENTRADA_INCORRECTA = 201;
	public static final int MAXIMO_ENTRADA_SALIDA_POR_TURNO = 202;
	public static final int TIEMPO_MINIMO_MINUTOS_ENTRE_MOVIMIENTO = 0;

	
	

	
	
}
