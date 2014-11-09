package daos;

import interfaces.ServiciosVariosInterfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import uade.fain.ia.tpo.interfaces.soap.Item;
import uade.fain.ia.tpo.interfaces.soap.OrdenDespacho;
import uade.fain.ia.tpo.interfaces.soap.OrdenDespachoSoapWS;
import uade.fain.ia.tpo.interfaces.soap.OrdenDespachoSoapWSBeanService;
import uade.fain.ia.tpo.interfaces.soap.RecibirOrdenDespacho;
import negocio.Despacho;
import negocio.ItemAuditoria;
import negocio.TROrdenVenta;
import dto.DespachoDTO;
import dto.ItemAuditoriaDTO;
import dto.ItemOrdenDespachoDTO;
import dto.ItemOrdenVentaDTO;
import dto.ItemRankingDTO;
import dto.MensajeRespuestaDTO;
import dto.RankingDTO;
import dto.TROrdenDespachoDTO;
import dto.TROrdenVentaDTO;
import enums.Estado;

@Stateless(name="serviciosVarios")
public class ServiciosVariosDAO implements ServiciosVariosInterfaz{
	
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	
	String ipportal = "172.16.163.15:8080"; //IP + PUERTO
	
	private String targetRanking = "http://" + ipportal + "/PortalWebCliente/rest/bestSeller/procesar";
	
	
	
	
	@Override
	public List<TROrdenVentaDTO> getOrdenesVentaSinAsociar() {
		List<TROrdenVentaDTO> ventasdto = new ArrayList<TROrdenVentaDTO>();
		
		
		try {
			List<TROrdenVenta> ventas = (List<TROrdenVenta>) em.createQuery("SELECT d FROM TROrdenVenta d where d.asociada is null").getResultList();
			
			for(TROrdenVenta actual: ventas){
				TROrdenVentaDTO nuevo = new TROrdenVentaDTO();
				nuevo.setFecha(actual.getFecha());
				nuevo.setCoordenadaX(actual.getLatitud());
				nuevo.setCoordenadaY(actual.getLongitud());
				nuevo.setModuloId(actual.getModuloId());
				nuevo.setMonto(actual.getMonto());
				nuevo.setVentaId(actual.getNumero());
				ventasdto.add(nuevo);
			}
		} catch(ClassCastException cce) {
			cce.printStackTrace();
		}
		
		return ventasdto;
	}
	
	public List<ItemRankingDTO> rankingArticulos()
	{
		List<ItemRankingDTO> resultado = new ArrayList<ItemRankingDTO>();
		Query q = em.createQuery(
				"select i.articulo, SUM(i.cantidad) as cant " + 
						"from ItemOrdenVenta i " +
						"group by i.articulo order by cant"
				);
		Iterator itr = q.getResultList().iterator();
		int pos = 1;
		while(itr.hasNext()){
			Object[] element = (Object[]) itr.next(); 
			ItemRankingDTO nuevo = new ItemRankingDTO();
			nuevo.setCodigoArticulo((Integer.valueOf(element[0].toString())));
			nuevo.setPosicion(pos);
			resultado.add(nuevo);
			pos = pos + 1;
		}
	
		return resultado;
	}
	public List<DespachoDTO> getDespachos(){
		List<DespachoDTO> despachosdto = new ArrayList<DespachoDTO>();
		
		try {
			List<Despacho> despachos = (List<Despacho>) em.createQuery("SELECT d FROM Despacho d").getResultList();
			
			for(Despacho actual: despachos){
				DespachoDTO nuevo = new DespachoDTO();
				nuevo.setLatitud(actual.getLatitud());
				nuevo.setLongitud(actual.getLongitud());
				nuevo.setNombre(actual.getNombre());
				nuevo.setNumero(actual.getNumero());
				despachosdto.add(nuevo);
			}
		} catch(ClassCastException cce) {
			cce.printStackTrace();
		}
		
		return despachosdto;
	}
	
	@Override
	public void enviarRanking(List<ItemRankingDTO> rankings) throws Exception {
		try {
			
            URL targetUrl = new URL(targetRanking);

            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();

            httpConnection.setDoOutput(true);

            httpConnection.setRequestMethod("POST");

            httpConnection.setRequestProperty("Content-Type", "application/json");
            
            RankingDTO ranking = new RankingDTO(rankings);
            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
    		String json = mapper.writeValueAsString(ranking);
            
    		OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(json.getBytes());

            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "

                    + httpConnection.getResponseCode());

            }

 

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(

                    (httpConnection.getInputStream())));

            String json2= "";
            String output = "";
            JAXBContext jaxbcontext;
            while ((output = responseBuffer.readLine()) != null) {
            	json2 = json2 + output;
            }
            System.out.println("Texto xml de ranking: " + json);
            
            
            jaxbcontext = JAXBContext.newInstance(MensajeRespuestaDTO.class);
            javax.xml.bind.Unmarshaller desencripta = jaxbcontext.createUnmarshaller();
            MensajeRespuestaDTO mensaje = (MensajeRespuestaDTO) desencripta.unmarshal(new StringReader(json));
 
          } catch (MalformedURLException e) {

            e.printStackTrace();

          } catch (IOException e) {

            e.printStackTrace();

         }


	}

	@Override
	public void mandarDespacho(TROrdenDespachoDTO aMandar) throws Exception {
		OrdenDespachoSoapWSBeanService service1 = new OrdenDespachoSoapWSBeanService();
        System.out.println("Create Web Service...");
        OrdenDespachoSoapWS port1 = service1.getOrdenDespachoSoapWSImplPort();
        OrdenDespacho nuevo = new OrdenDespacho();
        nuevo.setCodigoDespacho(String.valueOf(aMandar.getNroDespacho()));
        nuevo.setCodigoVenta(String.valueOf(aMandar.getNroVenta()));
        nuevo.setIdMonitoreo("3");
        nuevo.setIdPortal("1");
        for(ItemOrdenDespachoDTO actual: aMandar.getItems()){
        	Item item = new Item();
        	item.setArticuloId(String.valueOf(actual.getCodigoArticulo()));
        	item.setCantidad(actual.getCantidad());
        	nuevo.getItems().add(item);
        }
        
        try{
        	port1.recibirOrdenDespacho(nuevo);        	
        }catch(Exception e){
        	System.out.println("Falló el envio de Orden de Despacho.");
        }
		
	}
	
	
	@Override
	public List<TROrdenVentaDTO> getOrdenesVenta() {
		List<TROrdenVentaDTO> ventasdto = new ArrayList<TROrdenVentaDTO>();
		
		try {
			List<TROrdenVenta> ventas = (List<TROrdenVenta>) em.createQuery(
					"SELECT DISTINCT d FROM TROrdenVenta d left join fetch d.asociada left join fetch d.items").getResultList();
			
			for(TROrdenVenta actual: ventas){
				TROrdenVentaDTO nuevo = new TROrdenVentaDTO();
				nuevo.setFecha(actual.getFecha());
				nuevo.setCoordenadaX(actual.getLatitud());
				nuevo.setCoordenadaY(actual.getLongitud());
				nuevo.setModuloId(actual.getModuloId());
				nuevo.setMonto(actual.getMonto());
				nuevo.setVentaId(actual.getNumero());
				
				List<ItemOrdenVentaDTO> itemsVenta = new ArrayList<ItemOrdenVentaDTO>();
				for(int i=0; i<actual.getItems().size(); i++) {
					ItemOrdenVentaDTO iov = new ItemOrdenVentaDTO();
					iov.setProductoId(actual.getItems().get(i).getArticulo());
					iov.setCantidad(actual.getItems().get(i).getCantidad());
					itemsVenta.add(iov);
				}
				nuevo.setVentaItems(itemsVenta);
				
				if(actual.getAsociada() != null) {
					TROrdenDespachoDTO orden = new TROrdenDespachoDTO();
					orden.setNroDespacho(actual.getAsociada().getDespacho().getNumero());
					orden.setNroVenta(actual.getNumero());
					orden.setIdModulo(actual.getModuloId());
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					orden.setFecha(dateFormat.parse(actual.getAsociada().getFecha()));
					orden.setEstado(Estado.Abierta);
					nuevo.setAsociada(orden);
				}
				
				ventasdto.add(nuevo);
			}
		} catch(ClassCastException cce) {
			cce.printStackTrace();
		} catch(ParseException pe) {
			pe.printStackTrace();
		}
		
		
		return ventasdto;
		
	}
	
	@Override
	public List<ItemAuditoriaDTO> getItemsAuditoria() {
		List<ItemAuditoriaDTO> auditoriasdto = new ArrayList<ItemAuditoriaDTO>();
		try {
			List<ItemAuditoria> auditorias = (List<ItemAuditoria>) em.createQuery("SELECT a FROM ItemAuditoria a").getResultList();
			
			for(ItemAuditoria actual: auditorias){
				ItemAuditoriaDTO nuevo = new ItemAuditoriaDTO();
				//nuevo.setFecha(actual.getFecha());
				nuevo.setIdModulo(actual.getIdModulo());
				nuevo.setLog(actual.getLog());
				auditoriasdto.add(nuevo);
			}
		} catch(ClassCastException cce) {
			cce.printStackTrace();
		}
		return auditoriasdto;
	}
}
