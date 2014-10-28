package interfaces;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import dto.AuditoriaDTO;
import dto.TROrdenVentaDTO;

@Local
public interface AuditoriaDAOInterfaz {
	public void grabarAuditoria(AuditoriaDTO aGrabar) throws Exception;
	public void grabarVenta(TROrdenVentaDTO trov) throws Exception;
	public List<Map<Integer,Integer>> rankingArticulos();

}
