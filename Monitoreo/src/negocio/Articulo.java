package negocio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Articulo  extends PersistentObject{
	
	private int codigo;
	private String nombre;
	//private float cantidadVentas;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
/*	public float getCantidadVentas() {
		return cantidadVentas;
	}
	public void setCantidadVentas(float cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}*/
	
	
	
}
