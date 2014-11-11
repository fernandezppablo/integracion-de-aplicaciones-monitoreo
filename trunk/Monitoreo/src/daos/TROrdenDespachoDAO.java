package daos;

import interfaces.TROrdenDespachoDAOInterfaz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.ItemOrdenVentaDTO;
import enums.Estado;
import negocio.Despacho;
import negocio.ItemOrdenDespacho;
import negocio.TROrdenDespacho;
import negocio.TROrdenVenta;


@Stateless(name="TROrdenDespachoDAO")
public class TROrdenDespachoDAO implements TROrdenDespachoDAOInterfaz{
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;


	public void cambiarEstadoDespacho(int numero) throws Exception {
		Query q = em.createQuery("SELECT D FROM TROrdenDespacho D WHERE D.id = :NUMERO");
		q.setParameter("NUMERO", numero);
		List<TROrdenDespacho> despachos = (List<TROrdenDespacho>) q.getResultList();
		if(despachos.size()!=0){
			TROrdenDespacho despacho = despachos.get(0);
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
	
	public TROrdenDespacho crearOrdenDeDespachoParaVenta(TROrdenVenta venta, Despacho despacho) {
		TROrdenDespacho orden = new TROrdenDespacho();
		orden.setDespacho(despacho);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orden.setFecha(dateFormat.format(Calendar.getInstance().getTime()));
		orden.setEstado(Estado.Abierta);
		
		
		List<ItemOrdenDespacho> itemsDespacho = new ArrayList<ItemOrdenDespacho>();
		for(int i=0; i<venta.getItems().size(); i++) {
			ItemOrdenDespacho iod = new ItemOrdenDespacho();
			iod.setArticulo(venta.getItems().get(i).getArticulo());
			iod.setCantidad(venta.getItems().get(i).getCantidad());
			iod.setTransaccion(orden);
			iod.setItemasociado(venta.getItems().get(i));
			itemsDespacho.add(iod);
		}
		orden.setItems(itemsDespacho);
		
		try {
			orden.setAsociada(venta);
			//em.persist(orden);
			//venta.setAsociada(orden);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return orden;
	}
}
