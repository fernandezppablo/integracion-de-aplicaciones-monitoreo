package dto;

import java.util.ArrayList;
import java.util.List;

import enums.Estado;

public class TROrdenDespachoDTO {
	
	private String id;
	private Estado estado;
	private float latitud;
	private float longitud;
	private String fecha;
	private float monto;
	private String numero;
	private TROrdenVentaDTO asociada;
	private List<ItemOrdenDespachoDTO> items = new ArrayList<ItemOrdenDespachoDTO>();
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public List<ItemOrdenDespachoDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemOrdenDespachoDTO> items) {
		this.items = items;
	}
	
	public void agregarItem(ItemOrdenDespachoDTO item){
		this.items.add(item);
	}
	public TROrdenVentaDTO getAsociada() {
		return asociada;
	}
	//Con esta funcion copiamos todos los datos de la orden de venta
	public void setAsociada(TROrdenVentaDTO asociada) {
		if(asociada != null){
			this.asociada = asociada;
		

			this.monto = asociada.getMonto();

		}
	}
	public String getId() {
		return id;
	}
	
	
	
}
