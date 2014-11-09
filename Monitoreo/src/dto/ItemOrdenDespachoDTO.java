package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemOrdenDespachoDTO {
	private int codigoArticulo;
	private Integer cantidad;

	public int getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
	
}
