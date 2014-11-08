package dto;

import java.util.ArrayList;
import java.util.List;

public class ResumenPortalDTO {
	
	public int id;
	public List<TROrdenVentaDTO> ventas;
	public int total;
	
	public ResumenPortalDTO() {
		this.ventas = new ArrayList<TROrdenVentaDTO>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<TROrdenVentaDTO> getVentas() {
		return ventas;
	}
	
	public void setVentas(List<TROrdenVentaDTO> ventas) {
		this.ventas = ventas;
	}

}
