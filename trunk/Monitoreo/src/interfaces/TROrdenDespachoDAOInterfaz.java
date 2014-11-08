package interfaces;

import javax.ejb.Local;

import negocio.Despacho;
import negocio.TROrdenDespacho;
import negocio.TROrdenVenta;

@Local
public interface TROrdenDespachoDAOInterfaz {
	public void cambiarEstadoDespacho(int numero) throws Exception;
	public TROrdenDespacho crearOrdenDeDespachoParaVenta(TROrdenVenta venta, Despacho despacho);
}
