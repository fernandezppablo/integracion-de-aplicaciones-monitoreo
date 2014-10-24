package daos;

import java.util.List;

import interfaces.DespachoDAOInterfaz;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Path;

import enums.Estado;
import negocio.Despacho;


@Stateless
public class DespachoDAO {
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;


	public void cambiarEstadoDespacho(int numero) throws Exception {
		Query q = em.createQuery("SELECT D FROM Despacho D WHERE D.id = :NUMERO");
		q.setParameter("NUMERO", numero);
		List<Despacho> despachos = (List<Despacho>) q.getResultList();
		if(despachos.size()!=0){
			Despacho despacho = despachos.get(0);
			if(despacho.getEstado().equals(Estado.Abierta)){
				despacho.setEstado(Estado.Cerrada);
				em.persist(despacho);
			}else if(despacho.getEstado().equals(Estado.Anulada)){
				throw new Exception("El despacho que se ha pasado esta en estado anulado");
			}else if(despacho.getEstado().equals(Estado.Cerrada)){
				throw new Exception("El despacho que se ha pasado esta en estado cerrado");
			}
		}else{
			throw new Exception("No existe un despacho con ese numero");
		}
		
	}
}
