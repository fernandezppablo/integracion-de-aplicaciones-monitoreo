package negocio;




import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class Auditoria extends PersistentObject{
	
	@OneToMany
	@JoinColumn(name="auditoria")
	private List<ItemAuditoria> itemsauditoria; 

	public List<ItemAuditoria> getItemsauditoria() {
		return itemsauditoria;
	}

	public void setItemsauditoria(List<ItemAuditoria> itemsauditoria) {
		this.itemsauditoria = itemsauditoria;
	}



	
}
