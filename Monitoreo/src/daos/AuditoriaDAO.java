package daos;


import java.util.List;

import interfaces.AuditoriaDAOInterfaz;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




import javax.persistence.Query;

import negocio.Articulo;
import negocio.Auditoria;
import negocio.ItemAuditoria;
import negocio.ItemOrdenVenta;
import negocio.TROrdenVenta;

@Stateless
public class AuditoriaDAO implements AuditoriaDAOInterfaz{
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	

	public void grabarAuditoria(Auditoria aGrabar){
		em.getEntityManagerFactory().isOpen();
		em.persist(aGrabar);
		
		for(ItemAuditoria actual: aGrabar.getItemsauditoria()){
			em.persist(actual);
		}
	}
	
	public void grabarVenta(TROrdenVenta trov){
		
		em.getEntityManagerFactory().isOpen();
		em.persist(trov);
		
		for(ItemOrdenVenta iov: trov.getItems()){
			em.persist(iov);
		}
		
	}
	
	
	public Articulo traerArticuloPorCodigo(Integer id)
	{
		Articulo a = new Articulo();
		
		Query q =em.createQuery("select a.id from Articulo a where a.codigo=:artId").setParameter("artId", id);
		List<Integer> arts=q.getResultList();
		if(arts.size()!=1)
		{
			return null;
		}
		return em.find(Articulo.class, arts.get(0));
		
	}
	
	/*public void actualizarCantidadDeVentas(ItemOrdenVenta iov)
	{
		Query q =em.createQuery("update Articulo a "
				+ "set cantidadVentas=cantidadVentas+ :cantidad"
				+ " where a.codigo=:artId");
		q.setParameter("artId", iov.getArticulo().getCodigo());
		q.setParameter("cantidad",iov.getCantidad());
		q.executeUpdate();
		
	}
*/
	
	

}
