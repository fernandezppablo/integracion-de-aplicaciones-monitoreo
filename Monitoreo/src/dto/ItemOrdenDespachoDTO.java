package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemOrdenDespachoDTO {
	private int codigoArticulo;
	private float cantidad;

	public int getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
	
}
