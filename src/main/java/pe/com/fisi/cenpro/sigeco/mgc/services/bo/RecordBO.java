package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

public class RecordBO {
	private String nombreTratamiento;
	private float precioTratamiento;
	private int pago_cantidad;
	private int debe_cantidad;
	private int total_cantidad;
	private double pago;
	private double debe;
	private double monto;

	public String getNombreTratamiento() {
		return nombreTratamiento;
	}

	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
	}

	public float getPrecioTratamiento() {
		return precioTratamiento;
	}

	public void setPrecioTratamiento(float precioTratamiento) {
		this.precioTratamiento = precioTratamiento;
	}

	public int getPago_cantidad() {
		return pago_cantidad;
	}

	public void setPago_cantidad(int pago_cantidad) {
		this.pago_cantidad = pago_cantidad;
	}

	public int getDebe_cantidad() {
		return debe_cantidad;
	}

	public void setDebe_cantidad(int debe_cantidad) {
		this.debe_cantidad = debe_cantidad;
	}

	public int getTotal_cantidad() {
		return total_cantidad;
	}

	public void setTotal_cantidad(int total_cantidad) {
		this.total_cantidad = total_cantidad;
	}

	public double getPago() {
		return pago;
	}

	public void setPago(double pago) {
		this.pago = pago;
	}

	public double getDebe() {
		return debe;
	}

	public void setDebe(double debe) {
		this.debe = debe;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
}
