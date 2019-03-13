package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ubigeo database table.
 * 
 */
@Entity
@NamedQuery(name="Ubigeo.findAll", query="SELECT u FROM Ubigeo u")
public class Ubigeo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUbigeo;

	@Column(name="departamento_id")
	private String departamentoId;

	@Column(name="distrito_id")
	private String distritoId;

	private String nombre;

	@Column(name="provincia_id")
	private String provinciaId;

	//bi-directional many-to-one association to DatosPersona
	@OneToMany(mappedBy="ubigeo1")
	private List<DatosPersona> datosPersonas1;

	//bi-directional many-to-one association to DatosPersona
	@OneToMany(mappedBy="ubigeo2")
	private List<DatosPersona> datosPersonas2;

	public Ubigeo() {
	}

	public int getIdUbigeo() {
		return this.idUbigeo;
	}

	public void setIdUbigeo(int idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	public String getDepartamentoId() {
		return this.departamentoId;
	}

	public void setDepartamentoId(String departamentoId) {
		this.departamentoId = departamentoId;
	}

	public String getDistritoId() {
		return this.distritoId;
	}

	public void setDistritoId(String distritoId) {
		this.distritoId = distritoId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvinciaId() {
		return this.provinciaId;
	}

	public void setProvinciaId(String provinciaId) {
		this.provinciaId = provinciaId;
	}

	public List<DatosPersona> getDatosPersonas1() {
		return this.datosPersonas1;
	}

	public void setDatosPersonas1(List<DatosPersona> datosPersonas1) {
		this.datosPersonas1 = datosPersonas1;
	}

	public DatosPersona addDatosPersonas1(DatosPersona datosPersonas1) {
		getDatosPersonas1().add(datosPersonas1);
		datosPersonas1.setUbigeo1(this);

		return datosPersonas1;
	}

	public DatosPersona removeDatosPersonas1(DatosPersona datosPersonas1) {
		getDatosPersonas1().remove(datosPersonas1);
		datosPersonas1.setUbigeo1(null);

		return datosPersonas1;
	}

	public List<DatosPersona> getDatosPersonas2() {
		return this.datosPersonas2;
	}

	public void setDatosPersonas2(List<DatosPersona> datosPersonas2) {
		this.datosPersonas2 = datosPersonas2;
	}

	public DatosPersona addDatosPersonas2(DatosPersona datosPersonas2) {
		getDatosPersonas2().add(datosPersonas2);
		datosPersonas2.setUbigeo2(this);

		return datosPersonas2;
	}

	public DatosPersona removeDatosPersonas2(DatosPersona datosPersonas2) {
		getDatosPersonas2().remove(datosPersonas2);
		datosPersonas2.setUbigeo2(null);

		return datosPersonas2;
	}

}