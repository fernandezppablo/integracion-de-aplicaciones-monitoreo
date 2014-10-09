package negocio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;



@Entity
public class ItemOrdenVenta  extends PersistentObject{
	
	@ManyToOne
	private TROrdenVenta transaccion;
	@OneToOne
	private Articulo articulo;
	private float cantidad;
	
	public TROrdenVenta getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(TROrdenVenta transaccion) {
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
}
