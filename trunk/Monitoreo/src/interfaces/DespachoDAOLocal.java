package interfaces;

import javax.ejb.Local;

import negocio.Despacho;

@Local
public interface DespachoDAOLocal {
	
	public Despacho getDespacho(int numero) throws Exception;

}
