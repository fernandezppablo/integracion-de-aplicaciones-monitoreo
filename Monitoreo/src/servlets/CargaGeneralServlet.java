package servlets;

import interfaces.DespachoDAOLocal;
import interfaces.IURLManager;
import interfaces.ServiciosVariosInterfaz;
import interfaces.TROrdenDespachoDAOInterfaz;
import interfaces.TROrdenVentaDAOInterfaz;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Despacho;
import negocio.ItemOrdenDespacho;
import negocio.TROrdenDespacho;
import negocio.TROrdenVenta;
import negocio.URLContainer;
import dto.ItemOrdenDespachoDTO;
import dto.ResumenPortalDTO;
import dto.TROrdenDespachoDTO;
import dto.TROrdenVentaDTO;
import enums.Estado;

@WebServlet(name="init", urlPatterns="/web/*")
public class CargaGeneralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB(name = "serviciosVarios")
	private ServiciosVariosInterfaz sv;
	@EJB(name = "URLManager")
	private IURLManager urls;

	private List<ResumenPortalDTO> buildPortalesSum(List<TROrdenVentaDTO> ventas) {

		List<ResumenPortalDTO> sumPortales = new ArrayList<ResumenPortalDTO>();
		
		HashMap<Integer, List<TROrdenVentaDTO>> portalesMap = new HashMap<Integer, List<TROrdenVentaDTO>>();
		
		for(int i=0; i<ventas.size(); i++) {
			int modId = ventas.get(i).getModuloId();
			List<TROrdenVentaDTO> portalVentas;
			if(portalesMap.containsKey(modId)) {
				portalVentas = portalesMap.get(modId);
				if(portalVentas == null) {
					portalVentas = new ArrayList<TROrdenVentaDTO>();
				}
			} else {
				portalVentas = new ArrayList<TROrdenVentaDTO>();
			}
			portalVentas.add(ventas.get(i));
			portalesMap.put(modId, portalVentas);
		}
		
		Iterator<Entry<Integer, List<TROrdenVentaDTO>>> it = portalesMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, List<TROrdenVentaDTO>> kv = (Map.Entry<Integer, List<TROrdenVentaDTO>>)it.next();
			Integer portalId = (Integer)kv.getKey();
			List<TROrdenVentaDTO> valores = (List<TROrdenVentaDTO>)kv.getValue();
			int totalPortal = 0;
			for(int i=0; i<valores.size(); i++) { 
				totalPortal += valores.get(i).getMonto();
			}
			
			ResumenPortalDTO resumen = new ResumenPortalDTO();
			resumen.id = portalId;
			resumen.ventas = valores;
			resumen.total = totalPortal;
			
			sumPortales.add(resumen);
		}
		
		return sumPortales;
	}
	
    public CargaGeneralServlet() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		URLContainer urlContainer = urls.getIPs();
		
		List<TROrdenVentaDTO> ventasSinAsociar = sv.getOrdenesVentaSinAsociar();
		List<TROrdenVentaDTO> ventas = sv.getOrdenesVenta();
		List<ResumenPortalDTO> portalesVentas = this.buildPortalesSum(ventas);
		
		request.setAttribute("ranking", sv.rankingArticulos());
		request.setAttribute("despachos", sv.getDespachos());
		request.setAttribute("ventas", ventas);
		request.setAttribute("ventasSinAsociar", ventasSinAsociar);
		//request.setAttribute("auditorias", sv.getItemsAuditoria());
		request.setAttribute("sumarizacionPortales", portalesVentas);
		
		if(urlContainer != null) {
			request.setAttribute("ipPortal", urlContainer.ipPortal);
			request.setAttribute("ipDespacho", urlContainer.ipDespacho);
			request.setAttribute("ipDeposito", urlContainer.ipDeposito);
		}
		
		request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
