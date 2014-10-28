package Web;

import interfaces.TROrdenDespachoDAOInterfaz;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dto.MensajeRespuestaDTO;

/**
 * Session Bean implementation class RSMonitoreo
 */
@Stateless
@Path("REST")
public class RSMonitoreo {
	//(name = "DespachoDAO")
	@EJB(name="TROrdenDespachoDAO")
    private TROrdenDespachoDAOInterfaz dao;
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
    @Path("/cambioEstadoDespacho")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MensajeRespuestaDTO cambiarOrdenEstadoDespacho(int despacho) {	
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
	
}