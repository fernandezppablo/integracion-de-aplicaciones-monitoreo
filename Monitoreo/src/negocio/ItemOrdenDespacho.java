package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class ItemOrdenDespacho extends PersistentObject{
	
	@ManyToOne
	private TROrdenDespacho transaccion;
	@OneToOne
	private Articulo articulo;
	@OneToOne
	private ItemOrdenVenta itemasociado;
	private float cantidad;
	public TROrdenDespacho getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(TROrdenDespacho transaccion) {
		this.transaccion = transaccion;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public ItemOrdenVenta getItemasociado() {
		return itemasociado;
	}
	public void setItemasociado(ItemOrdenVenta itemasociado) {
		if(itemasociado != null){
			this.itemasociado = itemasociado;
			this.articulo = itemasociado.getArticulo();
			this.cantidad = itemasociado.getCantidad();
		}
	
	}
	
	
	
}
