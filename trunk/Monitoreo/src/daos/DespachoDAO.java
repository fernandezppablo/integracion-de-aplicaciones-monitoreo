package daos;

import interfaces.DespachoDAOLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import negocio.Despacho;

@Stateless(name="DespachoDAO")
public class DespachoDAO implements DespachoDAOLocal {

	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	
    public DespachoDAO() {
        
    }
    
    public Despacho getDespacho(int numero) throws Exception{
    	try {
    		Despacho resultado = (Despacho)em
    				.createQuery("select d from Despacho d where d.numero = :NUMERO")
    				.setParameter("NUMERO", numero)
    				.getSingleResult();
    		return resultado;
    	} catch(ClassCastException cce) {
    		cce.printStackTrace();
    		throw cce;
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    }

}
