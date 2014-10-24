package daos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.AuditoriaDAOInterfaz;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




import javax.persistence.Query;

import dto.AuditoriaDTO;
import dto.ItemAuditoriaDTO;
import dto.ItemOrdenVentaDTO;
import dto.TROrdenVentaDTO;
import negocio.Articulo;
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
			ItemAuditoria entidad = new ItemAuditoria(actual.getLog(), actual.getFecha(), actual.getIdModulo(), nueva);
			em.persist(entidad);
			lista.add(entidad);		
		}
		nueva.setItemsauditoria(lista);
		em.persist(nueva);

	}
	
	public void grabarVenta(TROrdenVentaDTO trov) throws Exception{
		

		TROrdenVenta nueva = new TROrdenVenta();
		nueva.setFecha(trov.getFecha());
		nueva.setLatitud(trov.getCoordenadaX());
		nueva.setLongitud(trov.getCoordenadaY());
		nueva.setMonto(trov.getMonto());
		nueva.setNumero(trov.getVentaId());
		if(trov.getVentaItems().size()!=0){
			em.persist(nueva);
			List<ItemOrdenVenta> lista = new ArrayList<ItemOrdenVenta>();
			for(ItemOrdenVentaDTO iov: trov.getVentaItems()){
				Articulo articulo = this.traerArticuloPorCodigo(iov.getProductoId());
				if(articulo!=null){
					ItemOrdenVenta item = new ItemOrdenVenta();
					item.setArticulo(articulo);
					item.setCantidad(iov.getCantidad());
					item.setTROrdenVentaId(nueva);
					lista.add(item);
				}else{
					em.remove(nueva);
					throw new Exception("Item con articulo inexistente. Id articulo inexistente: " + iov.getProductoId());
				}
			}
			nueva.setItems(lista);
			em.persist(nueva);
		}else{
			throw new Exception("Transaccion sin items");
		}
		
		
	}

	public Articulo traerArticuloPorCodigo(Integer id)
	{
		Articulo a = new Articulo();
		
		Query q =em.createQuery("select a from Articulo a where a.codigo=:artId").setParameter("artId", id);
		try
		{
			List<Articulo> arts= (List<Articulo>)q.getResultList();
			if(arts.size()!=1)
			{
				return null;
			}
			return arts.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Map<Integer,Integer>> rankingArticulos()
	{
		List<Map<Integer,Integer>> resultado = new ArrayList<Map<Integer,Integer>>();
		Query q = em.createQuery(
				"select new map(a.id, SUM(i.cantidad)) " + 
						"from ItemOrdenVenta i inner join Articulo a on a.id = i.Articulo " +
						"group by a.id order by SUM(i,cantidad)"
				);
		resultado = q.getResultList();
		return resultado;
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
