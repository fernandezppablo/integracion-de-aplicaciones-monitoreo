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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import negocio.Despacho;
import negocio.ItemAuditoria;
import negocio.TROrdenVenta;
import dto.DespachoDTO;
import dto.ItemAuditoriaDTO;
import dto.ItemRankingDTO;
import dto.MensajeRespuestaDTO;
import dto.TROrdenVentaDTO;

@Stateless(name="serviciosVarios")
public class ServiciosVariosDAO implements ServiciosVariosInterfaz{
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	String ipportal = "localhost:8080"; //IP + PUERTO
	private String targetRanking = "http://" + ipportal + "/PortalWeb/rest/bestSeller/procesar";
	
	
	public List<ItemRankingDTO> rankingArticulos()
	{
		List<ItemRankingDTO> resultado = new ArrayList<ItemRankingDTO>();
		Query q = em.createQuery(
				"select a.id, SUM(i.cantidad) " + 
						"from ItemOrdenVenta i inner join Articulo a on a.id = i.Articulo " +
						"group by a.id order by SUM(i,cantidad)"
				);
		Iterator itr = q.getResultList().iterator();
		while(itr.hasNext()){
			Object[] element = (Object[]) itr.next(); 
			ItemRankingDTO nuevo = new ItemRankingDTO();
			nuevo.setCodigoArticulo(Integer.valueOf(element[0].toString()));
			nuevo.setPosicion(Integer.valueOf(element[1].toString()));
			resultado.add(nuevo);
		}
	
		return resultado;
	}
	public List<DespachoDTO> getDespachos(){
		List<Despacho> despachos = (List<Despacho>) em.createQuery("SELECT d FROM Despacho d");
		List<DespachoDTO> despachosdto = new ArrayList<DespachoDTO>();
		
		for(Despacho actual: despachos){
			DespachoDTO nuevo = new DespachoDTO();
			nuevo.setLatitud(actual.getLatitud());
			nuevo.setLongitud(actual.getLongitud());
			nuevo.setNombre(actual.getNombre());
			despachosdto.add(nuevo);
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

            java.io.StringWriter sw = new StringWriter();
            OutputStream outputStream = httpConnection.getOutputStream();
            JAXBContext jc = JAXBContext.newInstance(ItemRankingDTO.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty("eclipselink.media.type", "application/json");
            marshaller.marshal(rankings,sw);
            
            outputStream.write(sw.toString().getBytes());

            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "

                    + httpConnection.getResponseCode());

            }

 

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(

                    (httpConnection.getInputStream())));

            String json= "";
            String output = "";
            JAXBContext jaxbcontext;
            while ((output = responseBuffer.readLine()) != null) {
            	json = json + output;
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
	public void mandarDespacho(DespachoDTO aMandar) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<TROrdenVentaDTO> getOrdenesVentaSinAsociar() {
		List<TROrdenVenta> ventas = (List<TROrdenVenta>) em.createQuery("SELECT d FROM TROrdenVenta d where d.asociada is null");
		List<TROrdenVentaDTO> ventasdto = new ArrayList<TROrdenVentaDTO>();
		
		for(TROrdenVenta actual: ventas){
			TROrdenVentaDTO nuevo = new TROrdenVentaDTO();
			nuevo.setFecha(actual.getFecha());
			nuevo.setCoordenadaX(actual.getLatitud());
			nuevo.setCoordenadaY(actual.getLongitud());
			nuevo.setModuloId(actual.getModuloId());
			nuevo.setMonto(actual.getMonto());
			nuevo.setVentaId(actual.getNumero());
		}
		
		return ventasdto;
	}
	@Override
	public List<TROrdenVentaDTO> getOrdenesVentaAsociadas() {
		// TODO Auto-generated method stub
		List<TROrdenVenta> ventas = (List<TROrdenVenta>) em.createQuery("SELECT d FROM TROrdenVenta d where d.asociada is not null");
		List<TROrdenVentaDTO> ventasdto = new ArrayList<TROrdenVentaDTO>();
		
		for(TROrdenVenta actual: ventas){
			TROrdenVentaDTO nuevo = new TROrdenVentaDTO();
			nuevo.setFecha(actual.getFecha());
			nuevo.setCoordenadaX(actual.getLatitud());
			nuevo.setCoordenadaY(actual.getLongitud());
			nuevo.setModuloId(actual.getModuloId());
			nuevo.setMonto(actual.getMonto());
			nuevo.setVentaId(actual.getNumero());
			
		}
		
		return ventasdto;
		
	}
	@Override
	public List<ItemAuditoriaDTO> getItemsAuditoria() {
		List<ItemAuditoria> auditorias = (List<ItemAuditoria>) em.createQuery("SELECT a FROM ItemAuditoria a");
		List<ItemAuditoriaDTO> auditoriasdto = new ArrayList<ItemAuditoriaDTO>();
		
		for(ItemAuditoria actual: auditorias){
			ItemAuditoriaDTO nuevo = new ItemAuditoriaDTO();
			nuevo.setFecha(actual.getFecha());
			nuevo.setIdModulo(actual.getIdModulo());
			nuevo.setLog(actual.getLog());
			auditoriasdto.add(nuevo);
			
		}
		return auditoriasdto;
	}
}
