package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.orm.hibernate4.SpringFlushSynchronization;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsignacionForm;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.HistoriaClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DatosPersonaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;

@Service
public class UtilPoiService {

	File localFile;
	FileOutputStream os;

	FileInputStream input;
	POIFSFileSystem fs;
	HSSFWorkbook wb1;
	HSSFSheet sheet1;

	XSSFWorkbook wb;
	XSSFSheet sheet;

	Row row;

	List<Object[]> listaPacientes;
	List<HistoriaClinicaForm> listaForms;
	private String mensajeError = "";
	private String nombre = "", columna = "";
	public boolean error, errorCab;
	static final int NUMERO_FILA_EMPIEZA = 1;
	static final int NUMERO_COLUMNAS = 18;

	public List<Object[]> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<Object[]> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public List<HistoriaClinicaForm> getListaForms() {
		return listaForms;
	}

	public void setListaForms(List<HistoriaClinicaForm> listaForms) {
		this.listaForms = listaForms;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void importar(MultipartFile uploaded) throws IOException {

		localFile = new File(uploaded.getOriginalFilename());
		os = null;

		try {

			os = new FileOutputStream(localFile);
			os.write(uploaded.getBytes());

		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<HistoriaClinicaForm> procesar() throws IllegalStateException {

		listaPacientes = new ArrayList<Object[]>();
		listaForms = new ArrayList<HistoriaClinicaForm>();

		String nombrePaciente = "";

		try {
			error = false;
			errorCab = false;
			input = new FileInputStream(localFile);
			fs = new POIFSFileSystem(input);
			wb1 = new HSSFWorkbook(fs);
			sheet1 = wb1.getSheetAt(0);
			System.out.println("Numero de hojas: " + wb1.getNumberOfSheets());
			System.out.println("Numero de filas: " + sheet1.getLastRowNum());

			if (validarCabecera(sheet1.getRow(0))) {
				for (int i = NUMERO_FILA_EMPIEZA; i <= sheet1.getLastRowNum(); i++) {
					Object[] tupla = new Object[NUMERO_COLUMNAS];
					HistoriaClinicaForm hcForm = new HistoriaClinicaForm();
					HistoriaClinicaGeneralBO hcBO = new HistoriaClinicaGeneralBO();
					DatosPersonaBO datosPersonaBO = new DatosPersonaBO();
					AsignacionForm aForm = new AsignacionForm();

					row = sheet1.getRow(i);
					if (error == false) {
						if (validarFila(row)) {
							DataFormatter formatter = new DataFormatter();
							Cell cell;
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

							int hc = (int) row.getCell(0).getNumericCellValue();
							tupla[0] = hc;
							hcBO.setNroHC(hc);
							System.out.println("Nro HC: " + hc);

							String apellidoPatPaciente = row.getCell(1).getStringCellValue();
							tupla[1] = apellidoPatPaciente;
							hcBO.setApellidoPatPaciente(apellidoPatPaciente);
							nombrePaciente += apellidoPatPaciente;

							String apellidoMatPaciente = row.getCell(2).getStringCellValue();
							tupla[2] = apellidoMatPaciente;
							hcBO.setApellidoMatPaciente(apellidoMatPaciente);
							nombrePaciente += " " + apellidoMatPaciente;

							String nombrePac = row.getCell(3).getStringCellValue();
							tupla[3] = nombrePac;
							hcBO.setNombrePaciente(nombrePac);
							nombrePaciente += " " + nombrePac;

							/*
							 * int edad = (int)
							 * row.getCell(4).getNumericCellValue(); tupla[4] =
							 * edad;
							 */

							String sexo = row.getCell(5).getStringCellValue();
							switch (sexo) {
							case "M":
								sexo = "MASCULINO";
								break;
							case "F":
								sexo = "FEMENINO";
								break;
							}
							tupla[5] = sexo;
							datosPersonaBO.setSexo(sexo);

							String ocupacion = row.getCell(6).getStringCellValue();
							tupla[6] = ocupacion;
							datosPersonaBO.setOcupacion(ocupacion);

							String direccion = row.getCell(7).getStringCellValue();
							tupla[7] = direccion;
							datosPersonaBO.setDomicilio(direccion);

							String distrito = row.getCell(8).getStringCellValue();
							tupla[8] = distrito;
							datosPersonaBO.setDistrito(distrito);

							cell = row.getCell(9);

							String telefono = formatter.formatCellValue(cell);
							tupla[9] = telefono;
							datosPersonaBO.setTelefonoCelular(telefono);

							// ---------------PROBANDO LECTURA DE CELDA TIPO
							// TEXTO
							// (con
							// datos numerico)-----------

							cell = row.getCell(10);
							// String j_username =
							// formatter.formatCellValue(cell);
							System.out.println("Tipo de celda DNI: " + row.getCell(10).getCellType());
							String dni = formatter.formatCellValue(cell);
							tupla[10] = dni;
							hcBO.setDniPaciente(dni);
							
							System.out.println("Lectura del dni correcta");

							// -----------------------------------------------------------------------

							String estadoCivil = row.getCell(11).getStringCellValue();
							tupla[11] = estadoCivil;
							if (estadoCivil.equals("SOLTERA") || estadoCivil.equals("CASADA")
									|| estadoCivil.equals("VIUDA") || estadoCivil.equals("DIVORCIADA")) {
								switch (estadoCivil) {
								case "SOLTERA":
									estadoCivil = "SOLTERO";
									break;
								case "CASADA":
									estadoCivil = "CASADO";
									break;
								case "VIUDA":
									estadoCivil = "VIUDO";
									break;
								case "DIVORCIADA":
									estadoCivil = "DIVORCIADO";
									break;
								}
							}
							datosPersonaBO.setEstadoCivil(estadoCivil);
							System.out.println("Lectura del estado civil correcta");
							
							Date fechaHC = row.getCell(12).getDateCellValue();
							hcBO.setFechaHC(fechaHC);
							if (fechaHC != null) {
								String fechaString = sdf.format(fechaHC);
								tupla[12] = fechaString;
							} else {
								tupla[12] = fechaHC;
							}
							System.out.println("Lectura de la fecha HC correcta");

							String contratoPre = row.getCell(13).getStringCellValue();
							tupla[13] = contratoPre;
							if (!contratoPre.equals("")) {
								String contratoAnio[] = contratoPre.split("-");
								hcBO.setNroContrato((Integer.parseInt(contratoAnio[0])));

								Date fechaContrato = row.getCell(16).getDateCellValue();
								hcBO.setFechaContrato(fechaContrato);
								if (fechaContrato != null) {
									String fechaString = sdf.format(fechaContrato);
									tupla[16] = fechaString;
								} else {
									tupla[16] = fechaContrato;
								}
								cell = row.getCell(17);
								// String j_username =
								// formatter.formatCellValue(cell);
								String codigoAlumno = formatter.formatCellValue(cell);
								tupla[17] = codigoAlumno;
								aForm.setCodigoAlumno(codigoAlumno);
								aForm.setEstado("ASIGNADA");
								aForm.setDniDoctor(null);
							} else {
								hcBO.setNroContrato(0);
								aForm.setCodigoAlumno("");
								// aForm.setEstado("ASIGNADA");
								aForm.setDniDoctor(null);
							}
							System.out.println("Lectura del contrato Pre correcta");

							String contratoPost = row.getCell(14).getStringCellValue();
							tupla[14] = contratoPost;
							System.out.println("Lectura del contrato Post correcta");
							
							String contratoSar = row.getCell(15).getStringCellValue();
							tupla[15] = contratoSar;
							System.out.println("Lectura del contratoSar correcta");

							// LLenando los atributos del HistoriaClinicaForm
							hcForm.setAsignacionForm(aForm);
							hcForm.setDatosPersonaBO(datosPersonaBO);
							hcForm.setHistoriaClinicaGeneralBO(hcBO);

							listaForms.add(hcForm);
							listaPacientes.add(tupla);

							/*
							 * input = new FileInputStream(localFile); wb = new
							 * XSSFWorkbook(input); sheet = wb.getSheetAt(0);
							 * 
							 * for (int i = 1; i <= sheet.getLastRowNum(); i++)
							 * { Object[] tupla = new Object[9]; row =
							 * sheet.getRow(i);
							 * 
							 * int HC = (int)
							 * row.getCell(0).getNumericCellValue(); tupla[0] =
							 * HC; //aca va lo demas
							 */
							System.out.println("PASE TODO!!!");
						}

					}

				}
			} else {
				// error = true;
				errorCab = true;
				mensajeError = "Error, el archivo no tiene la estructura correcta, falta la columna " + columna;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			error = true;
			mensajeError = "Ocurrio un error de formato en la fila del paciente: ";
			nombre = nombrePaciente;
			System.out.println("Nombre: " + nombre);
			e.printStackTrace();
		} catch (OfficeXmlFileException e) {
			error = true;
			mensajeError = "Error en tipo de archivo, elija un archivo " + "con formato correcto";
		}

		return listaForms;
	}

	private boolean validarCabecera(Row cabecera) {
		boolean correcto = true;

		if ((cabecera.getCell(0).toString().indexOf("HISTORIAS")) == -1) {
			correcto = false;
			columna = "HISTORIAS";
		} else if (!(cabecera.getCell(1).toString().replace(" ", "").equals("APELLIDOPATERNO"))) {
			columna = "APELLIDO PATERNO";
			correcto = false;
		} else if (!(cabecera.getCell(2).toString().replace(" ", "").equals("APELLIDOMATERNO"))) {
			columna = "APELLIDO MATERNO";
			correcto = false;
		} else if (!(cabecera.getCell(3).toString().equals("NOMBRES"))) {
			columna = "NOMBRES";
			correcto = false;
		} else if (!(cabecera.getCell(4).toString().equals("EDAD"))) {
			columna = "EDAD";
			correcto = false;
		} else if (!(cabecera.getCell(5).toString().equals("SEXO"))) {
			columna = "SEXO";
			correcto = false;
		} else if (!(cabecera.getCell(6).toString().equals("OCUPACION"))) {
			columna = "OCUPACION";
			correcto = false;
		} else if (!(cabecera.getCell(7).toString().equals("DOMICILIO"))) {
			columna = "DOMICILIO";
			correcto = false;
		} else if (!(cabecera.getCell(8).toString().equals("DISTRITO"))) {
			columna = "DISTRITO";
			correcto = false;
		} else if (!(cabecera.getCell(9).toString().equals("TELEFONO"))) {
			columna = "TELEFONO";
			correcto = false;
		} else if (!(cabecera.getCell(10).toString().equals("DNI"))) {
			columna = "DNI";
			correcto = false;
		} else if (!(cabecera.getCell(11).toString().replace(" ", "").equals("ESTADOCIVIL"))) {
			columna = "ESTADO CIVIL";
			correcto = false;
		} else if (!(cabecera.getCell(12).toString().replace(" ", "").equals("FECHAHISTORIA"))) {
			columna = "FECHA HISTORIA";
			correcto = false;
		} else if (!(cabecera.getCell(13).toString().replace(" ", "").equals("CONTRATOPRE"))) {
			columna = "CONTRATO PRE";
			correcto = false;
		} else if (!(cabecera.getCell(14).toString().replace(" ", "").substring(0, 12).equals("CONTRATOPOST"))) {
			columna = "CONTRATO POST";
			correcto = false;
		} else if (!(cabecera.getCell(15).toString().replace(" ", "").equals("CONTRATOSAR"))) {
			columna = "CONTRATO SAR";
			correcto = false;
		} else if (!(cabecera.getCell(16).toString().replace(" ", "").equals("FECHACONTRATO"))) {
			columna = "FECHA CONTRATO";
			correcto = false;
		} else if (!(cabecera.getCell(17).toString().equals("OPERADOR"))) {
			columna = "OPERADOR";
			correcto = false;
		}

		return correcto;
	}

	private boolean validarFila(Row fila) {
		int numFila = fila.getRowNum() + 1;

		DataFormatter formatter = new DataFormatter();
		// formatter.formatCellValue(celda);

		if (validarTipoNumero(formatter.formatCellValue(fila.getCell(0))) == false) {
			error = true;
			mensajeError = "Ingrese un valor numerico para N° HC en la fila :" + numFila;
			return false;
		} else if (vacioTodoElNombre(formatter.formatCellValue(fila.getCell(1)),
				formatter.formatCellValue(fila.getCell(2)), formatter.formatCellValue(fila.getCell(3))) == false) {

			if (vacio(formatter.formatCellValue(fila.getCell(1))) 
					|| !validaSoloTexto(formatter.formatCellValue(fila.getCell(1)))) {
				error = true;
				mensajeError = "Falta el campo APELLIDO PATERNO o no es valido en la fila: " + numFila;
				return false;
			} else if (vacio(formatter.formatCellValue(fila.getCell(2))) 
					|| !validaSoloTexto(formatter.formatCellValue(fila.getCell(2)))) {
				error = true;
				mensajeError = "Falta el campo APELLIDO MATERNO o no es valido en la fila: " + numFila;
				return false;
			} else if (vacio(formatter.formatCellValue(fila.getCell(3))) 
					|| !validaSoloTexto(formatter.formatCellValue(fila.getCell(3)))) {
				error = true;
				mensajeError = "Falta el campo NOMBRES o no es valido en la fila: " + numFila;
				return false;
			} else if (validarTipoNumero(formatter.formatCellValue(fila.getCell(4))) == false) {
				error = true;
				mensajeError = "Ingrese un valor valido para la columna EDAD en la fila: " + numFila;
				return false;
			} else if (!fila.getCell(5).toString().equals("M") && !fila.getCell(5).toString().equals("F")) {
				error = true;
				mensajeError = "Ingrese 'M' o 'F' para la columna SEXO en la fila: " + numFila;
				return false;
			} else if ((validarTipoNumero(formatter.formatCellValue(fila.getCell(9))) == false)
					|| (!(tieneNDigitos(formatter.formatCellValue(fila.getCell(9)), 7))
							&& !(tieneNDigitos(formatter.formatCellValue(fila.getCell(9)), 9)))) {
				error = true;
				mensajeError = "Ingrese un valor numerico de 7 o 9 digitos para la columna TELEFONO en la fila: "
						+ numFila;
				return false;
			} else if ((validarTipoNumero(formatter.formatCellValue(fila.getCell(10))) == false)
					|| !(tieneNDigitos(formatter.formatCellValue(fila.getCell(10)), 8))) {
				error = true;
				mensajeError = "Ingrese un valor numerico de 8 digitos para la columna DNI en la fila: " + numFila;
				return false;
			} else if (validaEstadoCivil(formatter.formatCellValue(fila.getCell(11))) == false) {
				error = true;
				mensajeError = "Ingrese un valor valido para la columna ESTADO CIVIL en la fila: " + numFila;
				return false;
			} else if (validarTipoFecha(formatter.formatCellValue(fila.getCell(12))) == false) {
				error = true;
				mensajeError = "Ingrese un valor valido para la columna FECHA HC en la fila: " + numFila;
				return false;
			} else if (validaContrato(formatter.formatCellValue(fila.getCell(13))) == false) {
				error = true;
				mensajeError = "Ingrese un valor numerico para CONTRATO PRE en la fila: " + numFila;
				return false;
			} else if (validarTipoFecha(formatter.formatCellValue(fila.getCell(16))) == false) {
				error = true;
				mensajeError = "Ingrese un valor numerico para la columna FECHA CONTRATO en la fila: " + numFila;
				return false;
			} else if (!vacio(formatter.formatCellValue(fila.getCell(17)))
					&& ((validarTipoNumero(formatter.formatCellValue(fila.getCell(17))))
							&& !(tieneNDigitos(formatter.formatCellValue(fila.getCell(17)), 8)))) {
				error = true;
				mensajeError = "Ingrese un valor numerico de 8 digitos para la columna OPERADOR en la fila: " + numFila;
				return false;
			}

		}
		return true;
	}

	private boolean validarTipoFecha(String fecha) {
		System.out.println("Fecha: " + fecha);
		if (vacio(fecha)) {
			return true;
		}
		try {
			fecha.split("/")[0].equals(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
			fecha.split("/")[1].equals(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)));
			fecha.split("/")[2].equals(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private boolean validarTipoNumero(String cadena) {
		System.out.println(cadena);
		if (vacio(cadena)) {
			return true;
		}
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private boolean vacioTodoElNombre(String apPaterno, String apMaterno, String nombres) {
		if (vacio(apPaterno) && vacio(apMaterno) && vacio(nombres)) {
			return true;
		}
		return false;
	}
	
	private boolean validaSoloTexto(String cadena){
		if ((cadena).matches("([a-z ñáéíóú]|[A-Z ÑÁÉÍÓÚ]|\\s)+")) { 
			return true; 
		} else 
			return false;
	}

	private boolean vacio(String cadena) {
		if (cadena.equals("")) {
			return true;
		}
		return false;
	}

	private boolean tieneNDigitos(String numero, int numDigitos) {

		System.out.println("Nro digitos: " + numero.length());
		return (numero.length() == numDigitos) ? true : false;
	}

	private boolean validaEstadoCivil(String cadena) {
		if (cadena.equals("SOLTERO") || cadena.equals("SOLTERA")) {
			return true;
		} else if (cadena.equals("CASADO") || cadena.equals("CASADA")) {
			return true;
		} else if (cadena.equals("VIUDO") || cadena.equals("VIUDA")) {
			return true;
		} else if (cadena.equals("DIVORCIADO") || cadena.equals("DIVORCIADA")) {
			return true;
		} else if (cadena.equals("CONVIVIENTE")) {
			return true;
		} else {
			return false;
		}
	}
	private boolean validaContrato(String cadena){
		if (vacio(cadena)) {
			return true;
		}
		if (cadena.indexOf("-") != -1){			
			return (validarTipoNumero(cadena.split("-")[0]) && validarTipoNumero(cadena.split("-")[1]));
		} else {
			return validarTipoNumero(cadena);			
		}

		
	}
}
