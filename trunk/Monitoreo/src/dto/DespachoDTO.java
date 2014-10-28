package dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name="despacho")
public class DespachoDTO {
	private int nroDespacho;
	private int nroVenta;
	private int idModulo;
	private Date fecha;
	public List<ItemOrdenDespachoDTO> items;
	public int getNroDespacho() {
		return nroDespacho;
	}
	public void setNroDespacho(int nroDespacho) {
		this.nroDespacho = nroDespacho;
	}
	public int getNroVenta() {
		return nroVenta;
	}
	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
	}
	public int getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}
	@XmlElement(name = "fecha")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@XmlElement( name="item" )
	@XmlElementWrapper( name="items" )
	public List<ItemOrdenDespachoDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemOrdenDespachoDTO> items) {
		this.items = items;
	}
	

	
	
}