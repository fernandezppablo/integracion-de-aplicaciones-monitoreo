package interfaces;

import java.util.List;

import dto.DespachoDTO;
import dto.ItemRankingDTO;
import dto.MensajeRespuestaDTO;

public interface ServiceManagerInterfaz {
	public MensajeRespuestaDTO enviarRanking(List<ItemRankingDTO> lista) throws Exception;
	public void mandarDespacho(DespachoDTO aMandar) throws Exception;
	
	
}
