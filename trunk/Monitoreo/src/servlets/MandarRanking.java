package servlets;

import interfaces.ServiciosVariosInterfaz;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ServiciosVariosDAO;

/**
 * Servlet implementation class MandarRanking
 */
@WebServlet("/MandarRanking")
public class MandarRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(mappedName = "ServicosVariosDAO")
	private ServiciosVariosInterfaz sv;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MandarRanking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			sv.enviarRanking(sv.rankingArticulos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
