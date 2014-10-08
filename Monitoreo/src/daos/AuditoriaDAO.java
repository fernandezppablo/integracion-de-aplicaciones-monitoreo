package daos;


import interfaces.AuditoriaDAOInterfaz;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import negocio.Auditoria;
import negocio.ItemAuditoria;

@Stateless
public class AuditoriaDAO implements AuditoriaDAOInterfaz{
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	

	public void gravarAuditoria(Auditoria agravar){
		em.getEntityManagerFactory().isOpen();
		em.persist(agravar);
		
		for(ItemAuditoria actual: agravar.getItemsauditoria()){
			em.persist(actual);
		}
		
	
	}
	

}
