package interfaces;

import negocio.TROrdenVenta;
import dto.TROrdenVentaDTO;

public interface TROrdenVentaDAOInterfaz {
	public void grabarVenta(TROrdenVentaDTO trov) throws Exception;
	TROrdenVenta getVenta(int numero) throws Exception;
}
