package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

public class CursoBO implements Comparable<CursoBO>{
//    private Short idCurso;
	private int idCurso;
    private String nombre;
    
//	public Short getIdCurso() {
//		return idCurso;
//	}
//	public void setIdCurso(Short idCurso) {
//		this.idCurso = idCurso;
//	}
    
    public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int compareTo(CursoBO otroCurso) {
		// TODO Auto-generated method stub
		return nombre.compareTo(otroCurso.nombre);
	}
    
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Id Curso : "+ getIdCurso()).append("\n");
		builder.append("Nombre : "+ getNombre()).append("\n");
		return builder.toString();
	}
    
	
}
