package dto;

import javax.xml.bind.annotation.XmlRootElement;


public class ItemRankingDTO {
	public int codigoArticulo;
	public int posicion;
	public int getCodigoArticulo() {
		return codigoArticulo;
	}
	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	
	
	
	
}
