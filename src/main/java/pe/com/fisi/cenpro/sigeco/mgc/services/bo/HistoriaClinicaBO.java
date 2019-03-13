package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.List;

import javax.validation.Valid;

public class HistoriaClinicaBO {
	@Valid
	private HistoriaClinicaGeneralBO datosGeneralesPaciente;
	@Valid
	private DatosPersonaBO datosAdicionalesPaciente;

	private List<AsignacionDetalleBO> listaAsignacionDetalle;

	public HistoriaClinicaGeneralBO getDatosGeneralesPaciente() {
		return datosGeneralesPaciente;
	}

	public void setDatosGeneralesPaciente(HistoriaClinicaGeneralBO datosGeneralesPaciente) {
		this.datosGeneralesPaciente = datosGeneralesPaciente;
	}

	public DatosPersonaBO getDatosAdicionalesPaciente() {
		return datosAdicionalesPaciente;
	}

	public void setDatosAdicionalesPaciente(DatosPersonaBO datosAdicionalesPaciente) {
		this.datosAdicionalesPaciente = datosAdicionalesPaciente;
	}

	public List<AsignacionDetalleBO> getListaAsignacionDetalle() {
		return listaAsignacionDetalle;
	}

	public void setListaAsignacionDetalle(List<AsignacionDetalleBO> listaAsignacionDetalle) {
		this.listaAsignacionDetalle = listaAsignacionDetalle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("\n\nDatos Generales" + "\n\n");
		if (getDatosGeneralesPaciente() != null) {
			builder.append(getDatosGeneralesPaciente().toString());
		}
		builder.append("\n\nDatos Adicionales del paciente \n\n");
		if (getDatosAdicionalesPaciente() != null) {
			builder.append(getDatosAdicionalesPaciente().toString());
		}
		if (getListaAsignacionDetalle() != null) {
			for (AsignacionDetalleBO asignacionDetalleBO : getListaAsignacionDetalle()) {
				builder.append("\n");
				builder.append(asignacionDetalleBO.toString());
				builder.append("\n");
			}
		} else {
			builder.append("Lista de asignaciones es nula");
		}

		return builder.toString();
	}

}
