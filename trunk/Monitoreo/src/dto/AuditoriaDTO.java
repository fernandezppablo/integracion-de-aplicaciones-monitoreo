package dto;

import java.util.ArrayList;
import java.util.List;

import negocio.ItemAuditoria;

public class AuditoriaDTO {

	private String id;
	private List<ItemAuditoriaDTO> itemsauditoria = new ArrayList<ItemAuditoriaDTO>();

	public List<ItemAuditoriaDTO> getItemsauditoria() {
		return itemsauditoria;
	}

	public void setItemsauditoria(List<ItemAuditoriaDTO> itemsauditoria) {
		this.itemsauditoria = itemsauditoria;
	}
	
	
	public void agregarItemAuditoria(ItemAuditoriaDTO agregar){
		this.itemsauditoria.add(agregar);
	}
}
