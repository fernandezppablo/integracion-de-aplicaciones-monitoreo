package daos;

import interfaces.ServiciosVariosInterfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ServiciosVariosDAO implements ServiciosVariosInterfaz{
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	
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
}
