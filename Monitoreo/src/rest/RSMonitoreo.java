package rest;

import java.util.List;
import java.util.Map;

import interfaces.AuditoriaDAOInterfaz;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Session Bean implementation class RSMonitoreo
 */
@Stateless
@Path("bestSeller")
public class RSMonitoreo {

        @EJB(name = "AuditoriaDAO")
        private AuditoriaDAOInterfaz dao;
    /**
     * Default constructor. 
     */
    public RSMonitoreo() {
        // TODO Auto-generated constructor stub
    }
    

  
    @GET
    @Path("/procesar")
    @Produces("application/json")
    public List<Map<Integer,Integer>> getRanking() {
        return dao.rankingArticulos();
    }
   

  
    /*
 // This method is called if TEXT_PLAIN is requested
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloInPlainText() {		  
      return "Hello world!";
    }
   
    // This method is called if HTML is requested
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHelloInHtml() {
      return "<html> " + "<title>" + "Hello world!" + "</title>"
          + "<body><h1>" + "Hello world!" + "</body></h1>" + "</html> ";
    }
	*/
}