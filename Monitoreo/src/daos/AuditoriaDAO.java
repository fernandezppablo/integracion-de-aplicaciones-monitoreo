package daos;


import java.util.ArrayList;
import java.util.List;

import interfaces.AuditoriaDAOInterfaz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dto.AuditoriaDTO;
import dto.ItemAuditoriaDTO;
import negocio.Auditoria;
import negocio.ItemAuditoria;

@Stateless(name="AuditoriaDAO")
public class AuditoriaDAO implements AuditoriaDAOInterfaz{
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	

	public void grabarAuditoria(AuditoriaDTO aGrabar) throws Exception{
		Auditoria nueva = new Auditoria();
		em.persist(nueva);
		List<ItemAuditoria> lista = new ArrayList<ItemAuditoria>();
		for(ItemAuditoriaDTO actual: aGrabar.getItemsauditoria()){ 
			ItemAuditoria entidad = new ItemAuditoria(actual.getLog(), actual.getFecha(), actual.getIdModulo(), nueva);
			em.persist(entidad);
			lista.add(entidad);		
		}
		nueva.setItemsauditoria(lista);
		em.persist(nueva);

	}

	public List<ItemAuditoria> ultimosLogs() throws Exception {
		try {
			return (List<ItemAuditoria>)em
					.createQuery("select l from ItemAuditoria l order by l.fecha desc")
					.setMaxResults(10)
					.getResultList();
		} catch(ClassCastException cce) {
			cce.printStackTrace();
			throw cce;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
