package negocio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistentObject {
	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}

	
	
}
