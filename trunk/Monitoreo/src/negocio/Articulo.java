package negocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Articulo extends PersistentObject{
	
	private String codigo;
	private String nombre;
	private float cantidadVentas;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getCantidadVentas() {
		return cantidadVentas;
	}
	public void setCantidadVentas(float cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}
	
	
	
}
