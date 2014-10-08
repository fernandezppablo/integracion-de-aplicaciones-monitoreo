package negocio;




import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Auditoria extends PersistentObject{
	
	@OneToMany(mappedBy="auditoria")
	private Set<ItemAuditoria> itemsauditoria = new HashSet<ItemAuditoria>(); 




	public Set<ItemAuditoria> getItemsauditoria() {
		return itemsauditoria;
	}




	public void setItemsauditoria(Set<ItemAuditoria> itemsauditoria) {
		this.itemsauditoria = itemsauditoria;
	}




	public void agregarItemAuditoria(ItemAuditoria agregar){
		this.itemsauditoria.add(agregar);
	}

	
}
