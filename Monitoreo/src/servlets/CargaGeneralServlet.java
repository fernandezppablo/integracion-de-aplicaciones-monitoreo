package servlets;

import interfaces.ServiciosVariosInterfaz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ResumenPortalDTO;
import dto.TROrdenVentaDTO;

@WebServlet(name="init", urlPatterns="/web/*")
public class CargaGeneralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ServiciosVariosInterfaz sv;

	private List<ResumenPortalDTO> buildPortalesSum(List<TROrdenVentaDTO> ventas) {

		List<ResumenPortalDTO> sumPortales = new ArrayList<ResumenPortalDTO>();
		
		HashMap<Integer, List<TROrdenVentaDTO>> portalesMap = new HashMap<Integer, List<TROrdenVentaDTO>>();
		
		for(int i=0; i<ventas.size(); i++) {
			int modId = ventas.get(i).getModuloId();
			if(portalesMap.containsKey(modId)) {
				List<TROrdenVentaDTO> portalVentas = portalesMap.get(modId);
				if(portalVentas == null) {
					portalVentas = new ArrayList<TROrdenVentaDTO>();
				}
				portalVentas.add(ventas.get(i));
				portalesMap.put(modId, portalVentas);
			}
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
    
    @EJB(name = "serviciosVarios")
    public void setServiciosVarios(ServiciosVariosInterfaz servicios) {
    	this.sv = servicios;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<TROrdenVentaDTO> ventasSinAsociar = sv.getOrdenesVentaSinAsociar();
		List<TROrdenVentaDTO> ventas = sv.getOrdenesVentaAsociadas();
		List<ResumenPortalDTO> portalesVentas = this.buildPortalesSum(ventas);
		
		for(int i=0; i<ventasSinAsociar.size(); i++) {
			ventas.add(ventasSinAsociar.get(i));
		}
		
		request.setAttribute("ranking", sv.rankingArticulos());
		request.setAttribute("despachos", sv.getDespachos());
		request.setAttribute("ventas", ventas);
		request.setAttribute("ventasSinAsociar", ventasSinAsociar);
		request.setAttribute("auditorias", sv.getItemsAuditoria());
		request.setAttribute("sumarizacionPortales", portalesVentas);
		
		request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
