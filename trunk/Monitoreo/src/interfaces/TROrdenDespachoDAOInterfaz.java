package interfaces;

import javax.ejb.Local;

@Local
public interface TROrdenDespachoDAOInterfaz {
	public void cambiarEstadoDespacho(int numero) throws Exception;
}
