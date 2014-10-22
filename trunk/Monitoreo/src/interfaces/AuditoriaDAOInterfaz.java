package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.Articulo;
import negocio.Auditoria;
import negocio.ItemOrdenVenta;
import negocio.TROrdenVenta;
@Local
public interface AuditoriaDAOInterfaz {
	public void grabarAuditoria(Auditoria aGrabar);
	public void grabarVenta(TROrdenVenta trov);
	public Articulo traerArticuloPorCodigo(Integer id);
	public List<Map<Integer,Integer>> rankingArticulos();

}
