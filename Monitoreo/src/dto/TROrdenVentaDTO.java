package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import enums.Estado;

@XmlRootElement(name="venta")
public class TROrdenVentaDTO {
	
	private int ventaId;
	private int moduloId;
	private float coordenadaX;
	private float coordenadaY;
	private Date fecha;
	private float monto;
	private List<ItemOrdenVentaDTO> ventaItems;
	public int getVentaId() {
		return ventaId;
	}
	public void setVentaId(int ventaId) {
		this.ventaId = ventaId;
	}
	public int getModuloId() {
		return moduloId;
	}
	public void setModuloId(int moduloId) {
		this.moduloId = moduloId;
	}
	public float getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(float coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public float getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(float coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	@XmlElement(name = "fecha")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	 @XmlElement( name="Item" )
	 @XmlElementWrapper( name="ventaItems" )
	public List<ItemOrdenVentaDTO> getVentaItems() {
		return ventaItems;
	}
	public void setVentaItems(List<ItemOrdenVentaDTO> ventaItems) {
		this.ventaItems = ventaItems;
	}
	

	
	
	
	
	
}
