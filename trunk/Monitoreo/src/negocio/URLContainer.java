package negocio;

import javax.persistence.Entity;

@Entity
public class URLContainer extends PersistentObject{
	
	public String ipPortal = "";
	public String ipDespacho = "";
	public String ipDeposito = "";
	
	public URLContainer() {}
}
