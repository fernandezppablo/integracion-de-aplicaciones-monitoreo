package interfaces;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import dto.AuditoriaDTO;
import dto.TROrdenVentaDTO;
import negocio.Articulo;
@Local
public interface AuditoriaDAOInterfaz {
	public void grabarAuditoria(AuditoriaDTO aGrabar) throws Exception;
	public void grabarVenta(TROrdenVentaDTO trov) throws Exception;
	public Articulo traerArticuloPorCodigo(Integer id);
	public List<Map<Integer,Integer>> rankingArticulos();

}
