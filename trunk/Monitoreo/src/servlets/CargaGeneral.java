package servlets;

import interfaces.ServiciosVariosInterfaz;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CargaGeneral
 */
@WebServlet("/CargaGeneral")
public class CargaGeneral extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB(mappedName = "ServicosVariosDAO")
	private ServiciosVariosInterfaz sv;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaGeneral() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("ranking", sv.rankingArticulos());
		request.setAttribute("despachos", sv.getDespachos());
		request.setAttribute("asociadas", sv.getOrdenesVentaAsociadas());
		request.setAttribute("sinasociar", sv.getOrdenesVentaSinAsociar());
		request.setAttribute("auditorias", sv.getItemsAuditoria());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
