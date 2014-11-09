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
	@JoinColumn(name="TROrdenVentaId")
	private TROrdenVenta TROrdenVentaId;
	private int articulo;
	private int cantidad;
	
	
	public TROrdenVenta getTROrdenVentaId() {
		return TROrdenVentaId;
	}
	public void setTROrdenVentaId(TROrdenVenta tROrdenVentaId) {
		TROrdenVentaId = tROrdenVentaId;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getArticulo() {
		return articulo;
	}
	public void setArticulo(int articulo) {
		this.articulo = articulo;
	}
	
	
}
