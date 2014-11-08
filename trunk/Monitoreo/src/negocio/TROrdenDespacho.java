package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enums.Estado;

@Entity
public class TROrdenDespacho  extends PersistentObject{
	
	private Estado estado;
	private float latitud;
	private float longitud;
	private String fecha;
	private float monto;
	@ManyToOne
	private Despacho despacho;
	
	@OneToOne
	@JoinColumn(name="asociada")
	private TROrdenVenta asociada;
	@OneToMany(mappedBy="transaccion")
	private List<ItemOrdenDespacho> items = new ArrayList<ItemOrdenDespacho>();
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
	public List<ItemOrdenDespacho> getItems() {
		return items;
	}
	public void setItems(List<ItemOrdenDespacho> items) {
		this.items = items;
	}
	
	public void agregarItem(ItemOrdenDespacho item){
		this.items.add(item);
	}
	public TROrdenVenta getAsociada() {
		return asociada;
	}
	//Con esta funcion copiamos todos los datos de la orden de venta
	public void setAsociada(TROrdenVenta asociada) {
		if(asociada != null){
			this.asociada = asociada;
		//	this.fecha = asociada.getFecha();
			this.latitud = asociada.getLatitud();
			this.longitud =  asociada.getLongitud();
			this.monto = asociada.getMonto();
			for(ItemOrdenVenta actual: asociada.getItems()){
				ItemOrdenDespacho nuevo = new ItemOrdenDespacho();
				nuevo.setItemasociado(actual);
			}
		}
	}
	public Despacho getDespacho() {
		return despacho;
	}
	public void setDespacho(Despacho despacho) {
		this.despacho = despacho;
	}
	
	
	
}
