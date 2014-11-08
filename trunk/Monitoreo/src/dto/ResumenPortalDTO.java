package dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class ResumenPortalDTO implements Serializable{
	public int id;
	public float total;
	public List<TROrdenVentaDTO> ventas;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<TROrdenVentaDTO> getVentas() {
		return ventas;
	}
	public void setVentas(List<TROrdenVentaDTO> ventas) {
		this.ventas = ventas;
	}
	
	
}
