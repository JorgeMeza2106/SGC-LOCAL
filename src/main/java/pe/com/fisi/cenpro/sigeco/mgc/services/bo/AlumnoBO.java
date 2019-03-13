package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlumnoBO {

	private UsuarioBO usuarioBO;
	private List<CursoBO> cursosActuales = new ArrayList<>();
	private String codigo;

	public UsuarioBO getUsuarioBO() {
		return usuarioBO;
	}

	public void setUsuarioBO(UsuarioBO usuarioBO) {
		this.usuarioBO = usuarioBO;
	}

	public List<CursoBO> getCursosActuales() {
		Collections.sort(cursosActuales);
		return cursosActuales;
	}

	public void setCursosActuales(List<CursoBO> cursosActuales) {
		this.cursosActuales = cursosActuales;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Còdigo : " + getCodigo()).append("\n");
		builder.append("Usuario BO : ").append("\n");
		if (getUsuarioBO() != null) {
			builder.append(getUsuarioBO().toString()).append("\n");
		}
		if (getCursosActuales() != null) {
			builder.append("Lista de cursos Actuales").append("\n");
			for (CursoBO cursoBO : cursosActuales) {
				builder.append(cursoBO).toString();
			}
			builder.append("\n");
		} else {
			builder.append("La lista de cursos Actuales es nula\n");
		}
		return builder.toString();
	}
}
