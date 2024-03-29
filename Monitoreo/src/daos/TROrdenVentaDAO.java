package daos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import negocio.ItemOrdenVenta;
import negocio.TROrdenVenta;
import dto.ItemOrdenVentaDTO;
import dto.TROrdenVentaDTO;
import interfaces.TROrdenVentaDAOInterfaz;

@Stateless(name="TROrdenVentaDAO")
public class TROrdenVentaDAO implements TROrdenVentaDAOInterfaz{
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	
	
	public void grabarVenta(TROrdenVentaDTO trov) throws Exception{
		

		TROrdenVenta nueva = new TROrdenVenta();
		nueva.setFecha(trov.getFecha());
		nueva.setLatitud(trov.getCoordenadaX());
		nueva.setLongitud(trov.getCoordenadaY());
		nueva.setMonto(trov.getMonto());
		nueva.setNumero(trov.getVentaId());
		nueva.setModuloId(trov.getModuloId());
		if(trov.getVentaItems().size()!=0){
			em.persist(nueva);
			List<ItemOrdenVenta> lista = new ArrayList<ItemOrdenVenta>();
			for(ItemOrdenVentaDTO iov: trov.getVentaItems()){
			
				ItemOrdenVenta item = new ItemOrdenVenta();
				item.setArticulo(iov.getProductoId());
				item.setCantidad(iov.getCantidad());
				item.setTROrdenVentaId(nueva);
				lista.add(item);
				
			}
			nueva.setItems(lista);
			em.persist(nueva);
		}else{
			throw new Exception("Transaccion sin items");
		}
		
		
	}
	
	public TROrdenVenta getVenta(int numero) throws Exception{
		try {
			TROrdenVenta venta = (TROrdenVenta)em
					.createQuery("select ov from TROrdenVenta ov where ov.numero = :NUMERO")
					.setParameter("NUMERO", numero)
					.getSingleResult();
			return venta;
		} catch(ClassCastException cce) {
			cce.printStackTrace();
			throw new Exception("No se pudo convertir el resultado de la consulta a TROrdenVenta");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
