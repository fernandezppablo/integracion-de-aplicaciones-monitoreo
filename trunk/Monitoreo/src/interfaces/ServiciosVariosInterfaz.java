package interfaces;

import java.util.List;
import java.util.Map;

import dto.DespachoDTO;
import dto.ItemAuditoriaDTO;
import dto.ItemRankingDTO;
import dto.TROrdenDespachoDTO;
import dto.TROrdenVentaDTO;

public interface ServiciosVariosInterfaz {
	public List<ItemRankingDTO> rankingArticulos();
	public void enviarRanking(List<ItemRankingDTO> ranking) throws Exception ;
	public void mandarDespacho(TROrdenDespachoDTO despachoDTO) throws Exception;
	public List<DespachoDTO> getDespachos();
	public List<TROrdenVentaDTO> getOrdenesVentaSinAsociar();
	public List<TROrdenVentaDTO> getOrdenesVenta();
	public List<ItemAuditoriaDTO> getItemsAuditoria();
}
