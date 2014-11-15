package daos;

import interfaces.IURLManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import enums.IP;
import negocio.URLContainer;

/**
 * Session Bean implementation class URLManager
 */
@Stateless(name="URLManager")
public class URLManager implements IURLManager {

	@PersistenceContext(unitName="TP")
	private EntityManager em;
	
    public URLManager() {}
    
    
    public URLContainer getIPs() {
    	try {
    		return (URLContainer)em.createQuery("select ip from URLContainer ip").setMaxResults(1).getSingleResult();
    	} catch(ClassCastException cce) {
    		cce.printStackTrace();
    		return null;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public void setIP(String ip, IP type) {
    	URLContainer urls = this.getIPs();
		switch(type) {
    		case Portal:
    			urls.ipPortal = ip; break;
    		case Despacho:
    			urls.ipDespacho = ip; break;
    		case Deposito:
    			urls.ipDeposito = ip; break;
    	}
    }

}
