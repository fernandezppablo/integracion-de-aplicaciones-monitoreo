package Web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import interfaces.AuditoriaDAOInterfaz;
import interfaces.DespachoDAOLocal;
import interfaces.IURLManager;
import interfaces.ServiciosVariosInterfaz;
import interfaces.TROrdenDespachoDAOInterfaz;
import interfaces.TROrdenVentaDAOInterfaz;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import negocio.Despacho;
import negocio.ItemAuditoria;
import negocio.ItemOrdenVenta;
import negocio.TROrdenDespacho;
import negocio.TROrdenVenta;
import dto.ItemAuditoriaSimpleDTO;
import dto.ItemOrdenDespachoDTO;
import dto.MensajeRespuestaDTO;
import dto.TROrdenDespachoDTO;
import enums.IP;

/**
 * Session Bean implementation class RSMonitoreo
 */
@Stateless
@Path("REST")
public class RSMonitoreo {
	@PersistenceContext(unitName = "TP")	
	private EntityManager em;
	@EJB(name="AuditoriaDAO")
	private AuditoriaDAOInterfaz IAuditoria;
	@EJB(name="TROrdenDespachoDAO")
    private TROrdenDespachoDAOInterfaz dao;
	@EJB(name="TROrdenVentaDAO")
	private TROrdenVentaDAOInterfaz IVentas;
	@EJB(name="DespachoDAO")
	private DespachoDAOLocal IDespachos;
	@EJB(name="serviciosVarios")
	private ServiciosVariosInterfaz IServicios;
	@EJB(name="URLManager")
	private IURLManager urls;
    /**
     * Default constructor. 
     */
    public RSMonitoreo() {
        // TODO Auto-generated constructor stub
    }
    

    /*
    @GET
    @Path("/procesar")
    @Produces("application/json")
    public List<Map<Integer,Integer>> getRanking() {
    	System.out.println("llegando solicitud");
        return dao.rankingArticulos();
    }
   */

  
 // This method is called if TEXT_PLAIN is requested
    @POST
    @Path("/cambioEstadoOrdenDespacho/{despacho}")
    @Produces(MediaType.APPLICATION_JSON)
    public MensajeRespuestaDTO cambioEstadoOrdenDespacho(@PathParam("despacho")int despacho) {	
      try {
		dao.cambiarEstadoDespacho(despacho);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return generarRespuesta(false,e.getMessage());
	}
      return generarRespuesta(true,"Proceso finalizado correctamente");
    }
    
    
   
   
    // This method is called if HTML is requested
    @GET
    @Path("/holamundo")
    @Produces(MediaType.TEXT_HTML)
    public String sayHelloInHtml() {
      return "<html> " + "<title>" + "Hello world!" + "</title>"
          + "<body><h1>" + "Hello world!" + "</body></h1>" + "</html> ";
    }
    
    public MensajeRespuestaDTO generarRespuesta(boolean estado,String mensaje){
    	MensajeRespuestaDTO respuesta = new MensajeRespuestaDTO();
    	if(estado){
    		respuesta.setEstado("OK");
    	}else{
    		respuesta.setEstado("ERROR");
    	}
    	respuesta.setMensaje(mensaje);
    	return respuesta;
    }
    
    
    @POST
    @Path("/asociarDespachoAVenta/{venta}/{despacho}")
    @Produces(MediaType.APPLICATION_JSON)
    public TROrdenDespachoDTO asociarDespachoAVenta(@PathParam("venta") int venta, @PathParam("despacho") int despacho) throws Exception{
		
		try {
			if(venta != -1 && despacho != -1) {
				TROrdenVenta Venta = IVentas.getVenta(venta);
				Despacho Despacho =  IDespachos.getDespacho(despacho);
				TROrdenDespacho orden = dao.crearOrdenDeDespachoParaVenta(Venta, Despacho);
				
				//TODO Llamar a web service de Despacho y mandarle la orden
				
				//serializar orden y mandarla de vuelta a la UI.
				TROrdenDespachoDTO despachoDTO = new TROrdenDespachoDTO();
				
				despachoDTO.setNroDespacho(orden.getDespacho().getNumero());
				despachoDTO.setNroVenta(orden.getAsociada().getNumero());
				despachoDTO.setIdModulo(orden.getAsociada().getModuloId());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date result =  df.parse(orden.getFecha()); 
				despachoDTO.setFecha(result);
				
				
				List<ItemOrdenDespachoDTO> items = new ArrayList<ItemOrdenDespachoDTO>();
				
				for(ItemOrdenVenta actual: Venta.getItems()){
					ItemOrdenDespachoDTO nuevo = new ItemOrdenDespachoDTO();
					nuevo.setCantidad(actual.getCantidad());
					nuevo.setCodigoArticulo(actual.getArticulo());
					items.add(nuevo);
				}
				despachoDTO.setItems(items);
				IServicios.mandarDespacho(despachoDTO);
				em.persist(orden);
				orden.getAsociada().setAsociada(orden);
				return despachoDTO;
				
			}
			
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
    
    @POST
    @Path("/enviarRanking")
    @Produces(MediaType.TEXT_HTML)
    public String mandarRanking() {
    	
    	try {
    		IServicios.enviarRanking(IServicios.rankingArticulos());
    		return "true";
    	} catch(Exception e) {
    		e.printStackTrace();
    		return "false";
    	}
    }
    
    
    @GET
    @Path("/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemAuditoriaSimpleDTO> getLogs() {
    	try {
    		List<ItemAuditoriaSimpleDTO> logsDtos = new ArrayList<ItemAuditoriaSimpleDTO>();
    		List<ItemAuditoria> logs = IAuditoria.ultimosLogs();
    		for(int i=0; i<logs.size(); i++) {
    			ItemAuditoriaSimpleDTO log = new ItemAuditoriaSimpleDTO();
    			log.setFecha(logs.get(i).getFecha());
    			log.setIdModulo(logs.get(i).getIdModulo());
    			log.setLog(logs.get(i).getLog());
    			logsDtos.add(log);
    		}
    		return logsDtos;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    @POST
    @Path("/cambiarIps")
    @Produces(MediaType.TEXT_HTML)
    public String updateIps(@FormParam("portal") String ipPortal, @FormParam("despacho") String ipDespacho, @FormParam("deposito") String ipDeposito) {
    	System.out.println(ipPortal + " " + ipDespacho + " " + ipDeposito);
    	urls.setIP(ipPortal, IP.Portal);
    	urls.setIP(ipDespacho, IP.Despacho);
    	urls.setIP(ipDeposito, IP.Deposito);
    	return "true";
    }
	
}