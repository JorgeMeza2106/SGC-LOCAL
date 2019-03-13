package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

public class HorarioClinicaBO {

	//private Integer idHorario;
//	private Short idCurso;
	private int idCurso;
	private ClinicaBO clinicaBO;
	private Integer dia;

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public ClinicaBO getClinicaBO() {
		return clinicaBO;
	}

	public void setClinicaBO(ClinicaBO clinicaBO) {
		this.clinicaBO = clinicaBO;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nHorairoBO\n");
		builder.append("idCurso : "+ getIdCurso()).append("\n");
		builder.append("dia : " + getDia()).append("\n");
		if(getClinicaBO()!=null){
			builder.append(clinicaBO.toString());
		}
		return builder.toString();
	}
}

/*
 * en javascript:
 * 
 * busco la recorro la lista de horarios y la que coincida se la envio.
 * 
 */

/*
 * por si las moscas tambien verifica con la cita :D
 * 
 * 
 * el formulario
 * 
 * th:field=idAsignacion th:field=idCurso th:field=idClinica:
 *
 * th:field=fechaAtencion:
 * 
 * th:field=turno:
 * 
 * submit:
 * 
 * 
 * El alumno tiene por defecto cargado sus cursos ni bien ingresa a la sesion:
 * 
 * AlumnoBO tiene una referencia a UserBO + List<CursoBO>
 * 
 * new SecurityUser...( .. ..... , AlumnoBO)
 * 
 * si elijo curso, consulta tabla horario (peticion ajax) y me saaca las
 * clinicas elijo curso 1 analiza: ---- curso 1 clinica 1 lunes ---- curso 1
 * clinica 1 martes ---- curso 1 clinica 2 miercoles
 * 
 * devuelve: --- 48 clinica 1 (CLINICA_BO) 49 clinica 2
 * 
 * asumiendo que no puede haber: ---- curso 1 clinica 1 lunes ---- curso 1
 * clinica 1 lunes
 * 
 * ahora debe elegir entre esas 2 clinicas elijo clinica 1
 * 
 * analiza : ---- curso 1 clinica 1 lunes ---- curso 1 clinica 1 martes
 * 
 * devuelve : ---- 677rr5r4rerq * si desactibva el js, ya no se podrian ejecutar
 * las sentencias de ajax...
 * 
 * 
 * 
 * 
 * 
 * 
 */
