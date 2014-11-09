package daos;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import interfaces.AuditoriaDAOInterfaz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




import javax.persistence.Query;

import dto.AuditoriaDTO;
import dto.ItemAuditoriaDTO;
import dto.ItemOrdenVentaDTO;
import dto.TROrdenVentaDTO;
import negocio.Auditoria;
import negocio.ItemAuditoria;
import negocio.ItemOrdenVenta;
import negocio.TROrdenVenta;

@Stateless
public class AuditoriaDAO implements AuditoriaDAOInterfaz{
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	

	public void grabarAuditoria(AuditoriaDTO aGrabar) throws Exception{
		Auditoria nueva = new Auditoria();
		em.persist(nueva);
		List<ItemAuditoria> lista = new ArrayList<ItemAuditoria>();
		for(ItemAuditoriaDTO actual: aGrabar.getItemsauditoria()){
			ItemAuditoria entidad = new ItemAuditoria(actual.getLog(), null, actual.getIdModulo(), nueva);
			em.persist(entidad);
			lista.add(entidad);		
		}
		nueva.setItemsauditoria(lista);
		em.persist(nueva);

	}

	



	


	
}
