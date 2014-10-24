package Web;

import interfaces.DespachoDAOInterfaz;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import daos.AuditoriaDAO;
import daos.DespachoDAO;


@ApplicationPath("Web")
public class RestServices extends Application {

}