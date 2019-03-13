package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

public enum EstadoAsignacion {
	ASIGNADA("ASIGNADA"), 
	AUTORIZADA("AUTORIZADA"), 
	ARCHIVADA("ARCHIVADA");

	public static final EstadoAsignacion[] ALL = { ASIGNADA, AUTORIZADA, ARCHIVADA };
	public static final EstadoAsignacion[] NO_ARCHIVADA = { AUTORIZADA, ASIGNADA };
	
	private final String nombre;

	public static EstadoAsignacion forName(final String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException("El nombre no puede ser nulo para este estado");
		}
		if (nombre.toUpperCase().equals("ASIGNADA")) {
			return ASIGNADA;
		} else if (nombre.toUpperCase().equals("AUTORIZADA")) {
			return AUTORIZADA;
		} else if (nombre.toUpperCase().equals("ARCHIVADA")) {
			return ARCHIVADA;
		}
		throw new IllegalArgumentException("El nombre  \"" + nombre + "\" no corresponde a ningun estado");
	}

	private EstadoAsignacion(final String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String toString() {
		return getNombre();
	}

}