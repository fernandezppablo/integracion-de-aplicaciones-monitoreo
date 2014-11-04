<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="dto.TROrdenVentaDTO"
    import="dto.TROrdenDespachoDTO"
    import="java.util.List"
    import="java.util.ArrayList"
    import="java.util.HashMap"
    import="java.util.Map"
    import="java.util.Iterator"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Monitoreo</title>
		<!-- Scripts -->
		<script src="${pageContext.request.contextPath}/scripts/jquery.js" ></script>
		<script src="${pageContext.request.contextPath}/scripts/foundation.min.js" ></script>
		<script src="${pageContext.request.contextPath}/scripts/app.js" ></script>
		<!-- Styles -->
		<link type="text/css" href="${pageContext.request.contextPath}/styles/foundation.css" rel="stylesheet">
		<link type="text/css" href="${pageContext.request.contextPath}/styles/foundation-icons/foundation-icons.css" rel="stylesheet">
		<link type="text/css" href="${pageContext.request.contextPath}/styles/app.css" rel="stylesheet">
	</head>
	
	<% 
		List<TROrdenVentaDTO> ventasSinAsociar = (List<TROrdenVentaDTO>)request.getAttribute("sinasociar");
		List<TROrdenVentaDTO> ventas = (List<TROrdenVentaDTO>)request.getAttribute("asociadas");
		
		for(int i=0; i<ventasSinAsociar.size(); i++) {
			ventas.add(ventasSinAsociar.get(i));
		}
	
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
	%>
	
	<body>
		<!-- Menu izquierda -->
		<div class="icon-bar vertical three-up">
			<a class="item logistica">
				<i class="fi-marker"></i>
				<label>Logística</label>
			</a>
			<a class="item monitoreo">
				<i class="fi-graph-bar"></i>
				<label>Monitoreo</label>
			</a>
			<a class="item reporte">
				<i class="fi-clipboard"></i>
				<label>Reporte</label>
			</a>
		</div>
		
		<!-- Banner -->
		<div id="banner">
			<h1>Logística y Monitoreo</h1>
		</div>
		
		<!-- Panel de contenidos -->
		<div id="all-content">
			<div id="content-pane">
			
			
				<!-- Panel de logistica -->
				<div id="content-logistica">
					<div class="content-title">
						<h3>Logística - Ventas sin despacho asociado</h3>
					</div>
					<div class="row">
						<ul id="ventas-container" class="small-block-grid-4">
						<% for(int i=0; i<ventas.size(); i++) { %>
							<%
							TROrdenVentaDTO venta = ventas.get(i);
							if(venta.getAsociada() != null) {  %>
								<li class="venta done">
							<% } else { %>
								<li class="venta pending">
							<% } %>
								<div class="venta-header">
									<h6 class="venta-titulo">
										Venta nro: <%=venta.getVentaId() %>
									</h6>
									<div class="venta-estado">
										<% if(venta.getAsociada() != null) { %>
											<div class="assign pending fi-info" style="display: none;"></div>
											<div class="assign done fi-check"></div>
										<% } else { %>
											<div class="assign pending fi-info"></div>
											<div class="assign done fi-check" style="display: none;"></div>
										<% } %>
									</div>
								</div>
								<ul class="venta-descripcion">
									<li class="venta-fecha"><label>Fecha:  </label><%=venta.getFecha() %></li>
									<li class="venta-portal"><label>Portal:  </label><%=venta.getModuloId() %></li>
									<li class="venta-monto"><label>Monto:  </label>$<%=venta.getMonto() %></li>
								</ul>
							</li>
						<% } %>
<!-- 							<li class="venta pending"> -->
<!-- 								<div class="venta-header"> -->
<!-- 									<h6 class="venta-titulo">Venta nro: 8965</h6> -->
<!-- 									<div class="venta-estado"> -->
<!-- 										<div class="assign pending fi-info"></div> -->
<!-- 										<div class="assign done fi-check" style="display: none;"></div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<ul class="venta-descripcion"> -->
<!-- 									<li class="venta-fecha"><label>Fecha:  </label>5/10/2014</li> -->
<!-- 									<li class="venta-portal"><label>Portal:  </label>2</li> -->
<!-- 									<li class="venta-monto"><label>Monto:  </label>$2500</li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
<!-- 							<li class="venta"> -->
<!-- 								<div class="venta-header"> -->
<!-- 									<h6 class="venta-titulo">Venta nro: 32128</h6> -->
<!-- 									<div class="venta-estado"> -->
<!-- 										<div class="assign pending fi-info" style="display: none;"></div> -->
<!-- 										<div class="assign done fi-check" style="display: none;"></div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<ul class="venta-descripcion"> -->
<!-- 									<li class="venta-fecha"><label>Fecha:  </label>5/10/2014</li> -->
<!-- 									<li class="venta-portal"><label>Portal:  </label>3</li> -->
<!-- 									<li class="venta-monto"><label>Monto:  </label>$6750</li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
						</ul>
					</div>
				</div>
				
				
				
				<!-- Panel de monitoreo -->
				<div id="content-monitoreo" style="display: none;">
					<div class="content-title">
						<h3>Monitoreo - Llegada de ventas de Portales</h3>
					</div>
					<div class="row monitoreo-tabla">
						<table>
						  <thead>
							<tr>
							  <th width="200">Venta</th>
							  <th>Fecha</th>
							  <th width="150">Total</th>
							  <th width="150">Portal</th>
							  <th width="150">Latitud</th>
							  <th width="150">Longitud</th>
							  <th width="150">Orden de Despacho</th>
							  <th width="150">Estado</th>
							</tr>
						  </thead>
						  <tbody>
						  <% for(int i=0; i<ventas.size() ; i++) { 
						  		TROrdenVentaDTO venta = ventas.get(i);
						  %>
							<tr>
							  <td><%=venta.getVentaId() %></td>
							  <td><%=venta.getFecha() %></td>
							  <td>$<%=venta.getMonto() %></td>
							  <td><%=venta.getModuloId() %></td>
							  <td><%=venta.getCoordenadaX() %></td>
							  <td><%=venta.getCoordenadaY() %></td>
							  <td>
							  	<% if(venta.getAsociada() != null) { %>
							  		Si
							  	<% } else { %>
							  		No
							  	<% } %>
							  </td>
							  <% 
							  	TROrdenDespachoDTO despacho = venta.getAsociada();
							  %>
							  <td class="monitoreo-status"><%=despacho.getEstado() %></td>
							</tr>
<!-- 							<tr> -->
<!-- 							  <td>8965</td> -->
<!-- 							  <td>5/10/2014</td> -->
<!-- 							  <td>$2500</td> -->
<!-- 							  <td>2</td> -->
<!-- 							  <td></td> -->
<!-- 							  <td></td> -->
<!-- 							  <td>No</td> -->
<!-- 							  <td class="monitoreo-status">-</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 							  <td>32128</td> -->
<!-- 							  <td>5/10/2014</td> -->
<!-- 							  <td>$6750</td> -->
<!-- 							  <td>3</td> -->
<!-- 							  <td></td> -->
<!-- 							  <td></td> -->
<!-- 							  <td>Si</td> -->
<!-- 							  <td class="monitoreo-status">Cerrada</td> -->
<!-- 							</tr> -->
						  </tbody>
						</table>
					</div>
				</div>
				
				
				<!-- Panel de reportes -->
				<div id="content-reportes" style="display: none;">
					<div class="content-title">
						<h3>Reporte - Ventas totales</h3>
					</div>
					<div class="row portales-reporte">
					
					<%
						Iterator it = portalesMap.entrySet().iterator();
						while(it.hasNext()) {
							Map.Entry kv = (Map.Entry)it.next();
							Integer portalId = (Integer)kv.getKey();
							List<TROrdenVentaDTO> valores = (List<TROrdenVentaDTO>)kv.getValue();
							
					%>
					
						<div class="portal-suma shadow large-12 columns">
							<h3 class="portal-nombre">Portal <%=portalId %></h3>
							<ul class="row portal-ventas">
							
							<% 
							long totalPortal = 0;
							for(int i=0; i<valores.size(); i++) { 
								totalPortal += valores.get(i).getMonto();
							%>
							
								<li class="portal-venta large-6 large-offset-6 columns">
									<div class="row">
										<div class="large-6 columns">
											<h6>Venta nro: <span class="portal-venta-nro"><%=valores.get(i).getVentaId() %></span></h6>
										</div>
										<div class="large-6 columns portal-venta-monto">$<%=valores.get(i).getMonto() %></div>
									</div>
								</li>
								
							<% } %>
							</ul>
							<div class="row">
								<div class="large-6 large-offset-6 columns portal-resumen">
									<div class="row">
										<span class="large-6 columns">Total</span>
										<span class="portal-total large-6 columns">$<%=totalPortal %></span>
									</div>
								</div>
							</div>
						</div>
						<% } %>
<!-- 						<div class="portal-suma shadow large-12 columns"> -->
<!-- 							<h3 class="portal-nombre">Portal 2</h3> -->
<!-- 							<ul class="row portal-ventas"> -->
<!-- 								<li class="portal-venta large-6 large-offset-6 columns"> -->
<!-- 									<div class="row"> -->
<!-- 										<div class="large-6 columns"> -->
<!-- 											<h6>Venta nro: <span class="portal-venta-nro">8965</span></h6> -->
<!-- 										</div> -->
<!-- 										<div class="large-6 columns portal-venta-monto">$2500</div> -->
<!-- 									</div> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 							<div class="row"> -->
<!-- 								<div class="large-6 large-offset-6 columns portal-resumen"> -->
<!-- 									<div class="row"> -->
<!-- 										<span class="large-6 columns">Total</span> -->
<!-- 										<span class="portal-total large-6 columns">$2500</span> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 						<div class="portal-suma shadow large-12 columns"> -->
<!-- 							<h3 class="portal-nombre">Portal 3</h3> -->
<!-- 							<ul class="row portal-ventas"> -->
<!-- 								<li class="portal-venta large-6 large-offset-6 columns"> -->
<!-- 									<div class="row"> -->
<!-- 										<div class="large-6 columns"> -->
<!-- 											<h6>Venta nro: <span class="portal-venta-nro">32128</span></h6> -->
<!-- 										</div> -->
<!-- 										<div class="large-6 columns portal-venta-monto">$6750</div> -->
<!-- 									</div> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 							<div class="row"> -->
<!-- 								<div class="large-6 large-offset-6 columns portal-resumen"> -->
<!-- 									<div class="row"> -->
<!-- 										<span class="large-6 columns">Total</span> -->
<!-- 										<span class="portal-total large-6 columns">$6750</span> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
				</div>
				
				
			</div>
			
			
			
			<!-- Panel de la derecha de datos extra -->
			<div id="data-pane">
				<div class="data-pane-header">
					<span class="fi-x-circle data-pane-close"></span>
					<h1>Detalles</h1>
				</div>
				<div class="data-pane-content">
				
					<!-- Subpanel seleccionar despacho -->
					<div id="data-seleccionar-despacho" style="display: none">
						<div class="data-header">
							<h3>Venta nro: <span class="nro-venta"></span></h3>
							<div class="row coordenadas">
								<div class="small-6 columns">
									<label>Latitud</label>
									<label class="latitud">-45.765</label>
								</div>
								<div class="small-6 columns">
									<label>Longitud</label>
									<label class="longitud">-67.998</label>
								</div>
							</div>
						</div>
						<div>
							<ul class="despachos">
								<!-- Cargar los despachos de forma dinámica -->
								<li class="despacho shadow row" data-id="1">
									<div class="small-12 columns">
										<h4 class="despacho-nombre row">
											<span class="small-12 columns">Despacho 1<span>
										</h4>
										<div class="row coordenadas">
											<span class="small-6 columns"><label>Latitud</label><label class="latitud">67.987</label></span>
											<span class="small-6 columns"><label>Longitud</label><label class="longitud">-78.568</label></span>
										<div>
									</div>
								</li>
								<li class="despacho shadow row" data-id="2">
									<div class="small-12 columns">
										<h4 class="despacho-nombre row">
											<span class="small-12 columns">Despacho 2<span>
										</h4>
										<div class="row coordenadas">
											<span class="small-6 columns"><label>Latitud</label><label class="latitud">98.654</label></span>
											<span class="small-6 columns"><label>Longitud</label><label class="longitud">-02.987</label></span>
										<div>
									</div>
								</li>
								<li class="despacho shadow row" data-id="3">
									<div class="small-12 columns">
										<h4 class="despacho-nombre row">
											<span class="small-12 columns">Despacho 3<span>
										</h4>
										<div class="row coordenadas">
											<span class="small-6 columns"><label>Latitud</label><label class="latitud">98.789</label></span>
											<span class="small-6 columns"><label>Longitud</label><label class="longitud">98.980</label></span>
										<div>
									</div>
								</li>
								<li class="despacho shadow row" data-id="4">
									<div class="small-12 columns">
										<h4 class="despacho-nombre row">
											<span class="small-12 columns">Despacho 4<span>
										</h4>
										<div class="row coordenadas">
											<span class="small-6 columns"><label>Latitud</label><label class="latitud">-36.251</label></span>
											<span class="small-6 columns"><label>Longitud</label><label class="longitud">103.435</label></span>
										<div>
									</div>
								</li>
							</ul>
						</div>
						<div class="row bottom-row">
							<span id="confirmar-despacho" class="button success radius large small-10 small-offset-1 columns">Seleccionar<span>
						</div>
					</div>
					
					
					<!-- Subpanel mostrar detalle de ventas -->
					<div id="data-ver-detalle-venta">
						<div class="data-header">
							<h3>Venta nro: <span class="nro-venta"></span></h3>
							<h5>Fecha: <span class="fecha"></span></h5>
							<h5>Monto: <span class="monto"></span></h5>
							<h5>Portal: <span class="portal"></span></h5>
							<div class="row coordenadas">
								<div class="small-6 columns">
									<label>Latitud</label>
									<label class="latitud">-45.765</label>
								</div>
								<div class="small-6 columns">
									<label>Longitud</label>
									<label class="longitud">-67.998</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script>
	  $(document).foundation();
	</script>
</html> 