package dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="despacho")
public class DespachoDTO {

	private String nombre;
	private float longitud;
	private float latitud;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	
	

	
	
}