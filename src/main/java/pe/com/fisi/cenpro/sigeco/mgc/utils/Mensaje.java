package pe.com.fisi.cenpro.sigeco.mgc.utils;

public class Mensaje {
	private int tipo;
	private String titulo;
	private String descripcion;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	private Object resultado;
	public Mensaje() {
		
	}
	public Mensaje(int tipo, String descripcion ){
		this.tipo = tipo;
		this.descripcion = descripcion;
		
	}
	public Mensaje(String titulo, String descripcion ){
		this.titulo = titulo;
		this.descripcion = descripcion;
		
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String mensaje) {
		this.descripcion = mensaje;
	}
	public Object getResultado() {
		return resultado;
	}
	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}
	
}
