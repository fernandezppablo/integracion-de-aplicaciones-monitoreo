package rest;

import java.util.List;
import java.util.Map;

import interfaces.AuditoriaDAOInterfaz;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    @Path("procesar")
    @Produces("application/json")
    public List<Map<Integer,Integer>> getRanking() {
        return dao.rankingArticulos();
    }

}
