package interfaces;

import javax.ejb.Local;

@Local
public interface DespachoDAOInterfaz {
	public void cambiarEstadoDespacho(int numero) throws Exception;
}
