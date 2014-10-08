package dto;


public class ItemOrdenVentaDTO {

	private String id;
	private TROrdenVentaDTO transaccion;
	private ArticuloDTO articulo;
	private float cantidad;
	public TROrdenVentaDTO getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(TROrdenVentaDTO transaccion) {
		this.transaccion = transaccion;
	}
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public String getId() {
		return id;
	}

	
}
