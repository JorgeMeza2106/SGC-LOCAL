package pe.com.fisi.cenpro.sigeco.mgc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class AppUtil {

	public static final String TIPO_PDF = "application/pdf";

	public static final String MANIANA = "Mañana";
	public static final String TARDE = "Tarde";
	public static final String M = "M";
	public static final String T = "T";

	public static final String PM = "pm";
	public static final String AM = "am";
	public static final String SPACE = " ";

	public static final String RUTA_LOGO = "jasper/logo2.png";
	public static final String RUTA_JASPER = "jasper/Papeleta.jasper";
	public static final String CLAVE_LOGO_REPORT_PAPELETA = "logo";
	
	public static final String CONTRATO_ULTIMO_GENERADO = "ULTIMO_GENERADO";
	public static final String CONTRATO_ACTIVO = "ACTIVO";
	public static final String CONTRATO_DESHABILITADO = "DESHABILITADO";

	public static Date StringToDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date fum = null;
		try {
			if (date != null)
				fum = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fum;
	}

	public static String ObjectToString(Object o) {
		if (o != null) {
			return o.toString();
		} else {
			return null;
		}
	}

	public static Integer ObjectToInteger(Object o) {
		if (o != null) {
			return Integer.parseInt(o.toString());
		} else {
			return null;
		}
	}

	public static Date ObjectToDate(Object o) {
		if (o != null) {
			return (Date) o;
		} else {
			return null;
		}
	}
	
	public static Time ObjectToTime(Object o) {
		if (o != null) {
			return (Time) o;
		} else {
			return null;
		}
	}

	public static byte ObjectToByte(Object o) {
		return (byte) o;
	}

	public static char ObjectToChar(Object o) {
		return (char) o;
	}

	public static Boolean ObjectToBoolean(Object o) {
		if (o != null) {
			return ((Boolean) o).booleanValue();
		} else {
			return null;
		}
	}

	public static BigDecimal ObjectoToBDecimal(Object o) {
		return (BigDecimal) o;
	}

	public static Short ObjectToShort(Object o) {
		return (Short) o;
	}

	public static String getTurno(Object o) {
		if (o.toString().equals(M)) {
			return MANIANA;
		}
		return TARDE;
	}

	public static String getNombreCompleto(Object nombre, Object apP, Object apM) {
		return nombre.toString() + " " + apP.toString() + " " + apM.toString();
	}

	public static String ObjectToStringDate(Object o) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date = ObjectToDate(o);
		String fum = null;
		if (date != null)
			fum = format.format(date);
		return fum;
	}

	public static String getEdad(Object o) {
		Date fechaNacimiento = ObjectToDate(o);
		if (fechaNacimiento != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			StringBuilder result = new StringBuilder();
			if (fechaNacimiento != null) {
				Calendar c = new GregorianCalendar();
				c.setTime(fechaNacimiento);
				result.append(calcularEdad(c));
				result.append(" años");
			}
			return result.toString();
		}
		return "";
	}

	private static int calcularEdad(Calendar fechaNac) {
		Calendar today = Calendar.getInstance();
		int diffYear = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
		int diffMonth = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
		int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
		// Si está en ese año pero todavía no los ha cumplido
		if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
			diffYear = diffYear - 1;
		}
		return diffYear;
	}

	public static String getHoraActual() {
		Date actual = Calendar.getInstance().getTime();

		DateFormat fHora = new SimpleDateFormat("hh:mm");
		DateFormat fh = new SimpleDateFormat("HH");

		StringBuilder builder = new StringBuilder();

		builder.append(fHora.format(actual));
		builder.append(SPACE);
		if (Integer.parseInt(fh.format(actual)) > 12) {
			builder.append(PM);
		} else {
			builder.append(AM);
		}

		return builder.toString();
	}

	public static String getFechaActual() {
		Date actual = Calendar.getInstance().getTime();

		StringBuilder builder = new StringBuilder();

		DateFormat formatNomDia = new SimpleDateFormat("EEEEEEEEE");
		DateFormat formatNomMes = new SimpleDateFormat("MMMMMMMMM");
		DateFormat formatDia = new SimpleDateFormat("dd");
		DateFormat formatAnio = new SimpleDateFormat("yyyy");

		builder.append(upperPrimeraLetra(formatNomDia.format(actual))).append(" ");
		builder.append(formatDia.format(actual));
		builder.append(" de ");
		builder.append(upperPrimeraLetra(formatNomMes.format(actual)));
		builder.append(" del ");
		builder.append(formatAnio.format(actual));

		return builder.toString();
	}

	private static String upperPrimeraLetra(String cadena) {
		if (cadena.isEmpty()) {
			return cadena;
		} else {
			return Character.toUpperCase(cadena.charAt(0)) + cadena.substring(1);
		}
	}

	@SuppressWarnings("deprecation")
	public static byte[] generarReporteEnByteArray(Object resultado) {
		ClassLoader classLoader = AppUtil.class.getClassLoader();
		String rutaParaJasper = RUTA_JASPER;
		try {
			JasperReport jasper = (JasperReport) JRLoader
					.loadObject(new File(classLoader.getResource(rutaParaJasper).getFile()));

			// Aqui se carga la imagen y sela envia por parámetro al jasper :V
			Map<String, Object> parameters = new HashMap<>();
			try (InputStream inputStream = AppUtil.class.getClassLoader().getResourceAsStream(RUTA_LOGO)) {
				parameters.put(CLAVE_LOGO_REPORT_PAPELETA, ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
			} catch (JRException | IOException e) {
				throw new RuntimeException("Failed to load images", e);
			}

			JasperPrint jp = JasperFillManager.fillReport(jasper, parameters,
					new JRBeanCollectionDataSource(Arrays.asList(resultado)));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			exporter.exportReport();
			return baos.toByteArray();
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
	}

}
