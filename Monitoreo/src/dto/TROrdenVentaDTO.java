package dto;

import java.util.ArrayList;
import java.util.List;

import enums.Estado;


public class TROrdenVentaDTO {

	private String id;
	private Estado estado;
	private float latitud;
	private float longitud;
	private String fecha;
	private float monto;
	private String numero;
	private List<ItemOrdenVentaDTO> items = new ArrayList<ItemOrdenVentaDTO>();
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
	public List<ItemOrdenVentaDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemOrdenVentaDTO> items) {
		this.items = items;
	}
	
	public void agregarItem(ItemOrdenVentaDTO item){
		this.items.add(item);
	}
	public String getId() {
		return id;
	}
	
}
