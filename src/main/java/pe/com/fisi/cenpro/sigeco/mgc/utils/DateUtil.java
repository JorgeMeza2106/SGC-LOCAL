package pe.com.fisi.cenpro.sigeco.mgc.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

	public static final ZoneId LOCALIDAD = ZoneId.of("America/Lima");

	// "dd/MM/yyyy"
	public static Date convertStringToDate(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static int getYear(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.get(Calendar.YEAR);
		} else
			return 0;

	}

	public static int getActualYear() {
		return getYear(new Date());
	}

	public static int getNumberDayOfWeek(String dia) {
		switch (dia) {
		case "DOMINGO":
			return 0;
		case "LUNES":
			return 1;
		case "MARTES":
			return 2;
		case "MIERCOLES":
			return 3;
		case "JUEVES":
			return 4;
		case "VIERNES":
			return 5;
		case "SABADO":
			return 6;
		default:
			return -1;
		}
	}

	public static int getNumberDayOfWeek(Date d) {
		GregorianCalendar cal = new GregorianCalendar(new Locale("es", "PE"));
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static String getNameDayOfWeek(int dia) {
		switch (dia) {
		case 7:
			return "DOMINGO";
		case 1:
			return "LUNES";
		case 2:
			return "MARTES";
		case 3:
			return "MIERCOLES";
		case 4:
			return "JUEVES";
		case 5:
			return "VIERNES";
		case 6:
			return "SABADO";
		default:
			return "-";
		}
	}

	public static String getNameDayOfTheWeek(int numberDay) {
		return getNameDayOfWeek(numberDay);
	}

	public static String getNameDayOfTheWeek(Date d) {
		return getNameDayOfWeek(getNumberDayOfWeek(d));
	}

	public static LocalDateTime getCurrentDateTimeWithTimeZone() {
		return LocalDateTime.now(LOCALIDAD);
	}

	public static Date getCurrentDateWithTimeZone() {
		return Date.from(getCurrentDateTimeWithTimeZone().atZone(LOCALIDAD).toInstant());
	}

	public static String getFormattedHour(LocalTime localTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return localTime.format(formatter);

	}

	public static String getFormattedHour(Date date) {
		DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		return formatoHora.format(date);
	}

	public static String getFormattedDate(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
		String formattedDateTime = localDateTime.format(formatter);
		return formattedDateTime;
	}

	public static String getFormattedDate(Date date) {
		DateFormat formatoHora = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		return formatoHora.format(date);
	}

	public static long daysBetween(LocalDate firstDate, LocalDate secondDate) {
		return ChronoUnit.DAYS.between(firstDate, secondDate);
	}

	public static int compareDate(java.sql.Date d1, java.sql.Date d2) {
		return toLocalDate(new Date(d1.getTime())).compareTo(toLocalDate(new Date(d2.getTime())));
	}

	public static boolean isBetween(LocalTime comp, LocalTime min, LocalTime max) {
		return comp.isAfter(min) && comp.isBefore(max);

	}

	public static boolean isSameDay(String day, LocalDate date) {
		System.out.println("ser?  " + day + "--" + DateUtil.getNameDayOfTheWeek(date.getDayOfWeek().getValue()));
		return day.equals(DateUtil.getNameDayOfTheWeek(date.getDayOfWeek().getValue()));
	}

	public static short getShiftOfDate(Date date) {
		short turno = 0;
		String formattedHour = getFormattedHour(date);
		String[] parts = formattedHour.split(":");
		String partHour = parts[0]; // HH
		int horaI = Short.parseShort(partHour);
		if (8 <= horaI && horaI < 13) {
			turno = 1;
		} else {
			if (13 < horaI && horaI < 18) {
				turno = 2;
			}
		}
		return turno;
	}

	public static long toMilliSeconds(LocalDateTime fechaHoraMovimiento) {
		return fechaHoraMovimiento.atZone(LOCALIDAD).toInstant().toEpochMilli();
	}

	public static Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(LOCALIDAD).toInstant());
	}

	public static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(LOCALIDAD).toInstant());
	}

	public static LocalDate toLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(LOCALIDAD).toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(LOCALIDAD).toLocalDateTime();
	}
	 
	public static Time toTime(LocalDateTime localDateTime){
		return new Time(toMilliSeconds(localDateTime));
	}
	

	public static int minutesBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
		return  (int) localDateTime1.until( localDateTime2, ChronoUnit.MINUTES);
	}

	public static int minutesBetween(Time time1, LocalDateTime localDateTime2) {
		return  (int) time1.toLocalTime().until(localDateTime2.toLocalTime(), ChronoUnit.MINUTES);
	}
	
	
}
