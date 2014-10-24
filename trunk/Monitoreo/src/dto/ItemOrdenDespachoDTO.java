package dto;


public class ItemOrdenDespachoDTO {
	private String id;

	private TROrdenDespachoDTO transaccion;
	private ArticuloDTO articulo;
	private ItemOrdenVentaDTO itemasociado;
	private float cantidad;
	public TROrdenDespachoDTO getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(TROrdenDespachoDTO transaccion) {
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
	public ItemOrdenVentaDTO getItemasociado() {
		return itemasociado;
	}
	public void setItemasociado(ItemOrdenVentaDTO itemasociado) {
		if(itemasociado != null){
			this.itemasociado = itemasociado;
	
			this.cantidad = itemasociado.getCantidad();
		}
	
	}
	public String getId() {
		return id;
	}

	
	
	
	
}
