package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import negocio.ItemAuditoria;
import dto.AuditoriaDTO;
import dto.TROrdenVentaDTO;

@Local
public interface AuditoriaDAOInterfaz {
	public void grabarAuditoria(AuditoriaDTO aGrabar) throws Exception;
	public List<ItemAuditoria> ultimosLogs() throws Exception;
}
