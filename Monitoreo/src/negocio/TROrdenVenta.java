package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import enums.Estado;

@Entity
public class TROrdenVenta extends PersistentObject{
	
	private Estado estado;
	private float latitud;
	private float longitud;
	private String fecha;
	private float monto;
	
	private Integer numero;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="TROrdenVentaId")
	private List<ItemOrdenVenta> items = new ArrayList<ItemOrdenVenta>();
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public List<ItemOrdenVenta> getItems() {
		return items;
	}
	public void setItems(List<ItemOrdenVenta> items) {
		this.items = items;
	}
	
	public void agregarItem(ItemOrdenVenta item){
		this.items.add(item);
	}
}
