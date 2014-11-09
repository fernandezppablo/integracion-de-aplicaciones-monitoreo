<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Monitoreo</title>
		<!-- Scripts -->
		<script src="${pageContext.request.contextPath}/scripts/jquery.js" ></script>
		<script src="${pageContext.request.contextPath}/scripts/foundation.min.js" ></script>
		<script src="${pageContext.request.contextPath}/scripts/app.js" ></script>
		<!-- Styles -->
		<link type="text/css" href="${pageContext.request.contextPath}/styles/foundation.min.css" rel="stylesheet">
		<link type="text/css" href="${pageContext.request.contextPath}/styles/foundation-icons/foundation-icons.css" rel="stylesheet">
		<link type="text/css" href="${pageContext.request.contextPath}/styles/app.css" rel="stylesheet">
	</head>
	
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
				<label>Reportes</label>
			</a>
			<a class="item auditoria">
				<i class="fi-page-multiple"></i>
				<label>Auditoria</label>
			</a>
			<a class="item ranking">
				<i class="fi-upload-cloud"></i>
				<label>Enviar Ranking</label>
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
						<c:forEach items="${requestScope.ventasSinAsociar}" var="venta">
							<c:choose>
							<c:when test="${venta.asociada ne null}">
								<li class="venta done" data-id="${venta.ventaId}">
									<div class="venta-header">
										<h6 class="venta-titulo">
											Venta nro: <c:out value="${venta.ventaId}"></c:out>
										</h6>
										<div class="venta-estado">
											<div class="assign pending fi-info" style="display: none;"></div>
											<div class="assign done fi-check"></div>
										</div>
									</div>
									<ul class="venta-descripcion">
										<li class="venta-fecha"><label>Fecha:  </label><c:out value="${venta.fecha}"></c:out></li>
										<li class="venta-portal"><label>Portal:  </label><c:out value="${venta.moduloId}"></c:out></li>
										<li class="venta-monto"><label>Monto:  </label>$<c:out value="${venta.monto}"></c:out></li>
									</ul>
								</li>
							</c:when>
							<c:when test="${venta.asociada eq null}">
								<li class="venta pending" data-id="${venta.ventaId}">
									<div class="venta-header">
										<h6 class="venta-titulo">
											Venta nro: <c:out value="${venta.ventaId}"></c:out>
										</h6>
										<div class="venta-estado">
											<div class="assign pending fi-info"></div>
											<div class="assign done fi-check" style="display: none;"></div>
										</div>
									</div>
									<ul class="venta-descripcion">
										<li class="venta-fecha"><label>Fecha:  </label><c:out value="${venta.fecha}"></c:out></li>
										<li class="venta-portal"><label>Portal:  </label><c:out value="${venta.moduloId}"></c:out></li>
										<li class="venta-monto"><label>Monto:  </label>$<c:out value="${venta.monto}"></c:out></li>
									</ul>
								</li>
							</c:when>
							</c:choose>
						</c:forEach>
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
						  <c:forEach items="${requestScope.ventas}" var="venta">
							<tr class="venta-resumen" data-id="${venta.ventaId}">
							  <td><c:out value="${venta.ventaId}"></c:out></td>
							  <td><c:out value="${venta.fecha}"></c:out></td>
							  <td>$<c:out value="${venta.monto}"></c:out></td>
							  <td><c:out value="${venta.moduloId}"></c:out></td>
							  <td><c:out value="${venta.coordenadaX}"></c:out></td>
							  <td><c:out value="${venta.coordenadaY}"></c:out></td>
							  <td class="monitoreo-con-despacho">
							  	<c:choose>
							  		<c:when test="${venta.asociada eq null}">
							  			No
							  		</c:when>
							  		<c:otherwise>
							  			Si
							  		</c:otherwise>
							  	</c:choose>
							  </td>
							  <td class="monitoreo-status"><c:out value="${venta.asociada.estado}"></c:out></td>
							</tr>
							</c:forEach>
						  </tbody>
						</table>
					</div>
				</div>
				
				
				<!-- Panel de reportes -->
				<div id="content-reportes" style="display: none;">
					<div class="content-title">
						<h3>Reportes - Ventas totales</h3>
					</div>
					<div class="row portales-reporte">
					
					<c:forEach items="${requestScope.sumarizacionPortales}" var="portal">
						<div class="portal-suma shadow large-12 columns">
							<h3 class="portal-nombre row">Portal <c:out value="${portal.id}"></c:out></h3>
							<ul class="row portal-ventas">
							
							<c:forEach items="${portal.ventas}" var="venta">
								<li class="portal-venta large-6 large-offset-6 columns" data-id="${venta.ventaId}">
									<div class="row">
										<div class="large-6 columns">
											<h6>Venta nro: <span class="portal-venta-nro"><c:out value="${venta.ventaId}"></c:out></span></h6>
										</div>
										<div class="large-6 columns portal-venta-monto">$<c:out value="${venta.monto}"></c:out></div>
									</div>
								</li>
							</c:forEach>
							</ul>
							<div class="row">
								<div class="large-6 large-offset-6 columns portal-resumen">
									<div class="row">
										<span class="large-6 columns">Total</span>
										<span class="portal-total large-6 columns">$<c:out value="${portal.total}"></c:out></span>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					</div>
				</div>
				
				
				<!-- Panel de Ranking -->
				<div id="content-ranking" style="display: none;">
					<div class="content-title">
						<h3>
							Ranking de Ventas
							<span class="button alert radius medium enviar-ranking">
								Enviar Ranking
								<!-- Spinner  -->
								<span class="loader load-button" title="loading" style="display: none;">
								  <svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
								     width="15px" height="15px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
									<path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
									    <animateTransform attributeType="xml"
									      attributeName="transform"
									      type="rotate"
									      from="0 25 25"
									      to="360 25 25"
									      dur="0.6s"
									      repeatCount="indefinite"/>
								   	</path>
								  </svg>
								</span>
							</span>
						</h3>
					</div>
					<div class="row ranking-items">
					
					<c:forEach items="${requestScope.ranking}" var="item">
						<div class="ranking-item shadow large-12 columns">
							<div class="row">
								<div class="ranking-item-position large-2 columns">
									<span class="label round alert"><c:out value="${item.posicion}"></c:out></span>
								</div>
								<h4 class="ranking-item-nombre large-10 columns">Articulo: <c:out value="${item.codigoArticulo}"></c:out></h4>
							</div>							
						</div>
					</c:forEach>
					</div>
				</div>
				
				
				<!-- Panel de Auditoria -->
				<div id="content-audit" style="display: none;">
					<div id="log-template" style="display: none;">
						<div class="log shadow large-12 columns">
							<div class="log-header row">
								<h4 class="large-10 columns">Portal: <span class="log-portal"></span> | Fecha: <span class="log-date"></span></h4>
							</div>
							<div class="row">
								<p class="log-message large-12 columns"></p>
							</div>							
						</div>
					</div>
					<div class="content-title">
						<h3>
							Logs de Auditoria
						</h3>
						<!-- Spinner  -->
						<div class="loader" title="loading" style="display: none;">
						  <svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
						     width="20px" height="20px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
						  <path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
						    <animateTransform attributeType="xml"
						      attributeName="transform"
						      type="rotate"
						      from="0 25 25"
						      to="360 25 25"
						      dur="0.6s"
						      repeatCount="indefinite"/>
						    </path>
						  </svg>
						</div>
					</div>
					<div class="row log-items">
						
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
						<div class="mid-row">
							<ul class="despachos">
								<!-- Cargar los despachos de forma dinámica -->
								<c:forEach items="${requestScope.despachos}" var="despacho">
									<li class="despacho shadow row" data-id="${despacho.numero}">
										<div class="small-12 columns">
											<h4 class="despacho-nombre row">
												<span class="small-10 columns">Despacho: <c:out value="${despacho.nombre}"></c:out></span>
												<span class="small-2 columns alert label recomend">Recomendado</span>
											</h4>
											<div class="row coordenadas">
												<span class="small-6 columns"><label>Latitud</label><label class="latitud"><c:out value="${despacho.latitud}"></c:out></label></span>
												<span class="small-6 columns"><label>Longitud</label><label class="longitud"><c:out value="${despacho.longitud}"></c:out></label></span>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<div class="row bottom-row">
							<span id="confirmar-despacho" class="button success radius large small-10 small-offset-1 columns">
								Seleccionar
								<!-- Spinner  -->
								<span class="loader load-button" title="loading" style="display: none;">
								  <svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
								     width="17px" height="17px" viewBox="0 0 50 50" style="enable-background:new 0 0 50 50;" xml:space="preserve">
									<path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
									    <animateTransform attributeType="xml"
									      attributeName="transform"
									      type="rotate"
									      from="0 25 25"
									      to="360 25 25"
									      dur="0.6s"
									      repeatCount="indefinite"/>
								   	</path>
								  </svg>
								</span>
							</span>
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

		var estados = {
			'1': 'Abierta',
			'2': 'Anulada',
			'3': 'Cerrada'
				};
		var ventas = {};
		if(Venta) {
			<c:forEach items="${requestScope.ventas}" var="venta">
			ventas["${venta.ventaId}"] = new Venta(
						"${venta.ventaId}",
						"${venta.coordenadaY}", 
						"${venta.coordenadaX}", 
						"${venta.fecha}", 
						"${venta.monto}", 
						"${venta.moduloId}"
						);
				<c:if test="${venta.asociada ne null}">
					ventas["${venta.ventaId}"]['asociada'] = new OrdenDespacho(
							"${venta.asociada.nroDespacho}",
							"${venta.asociada.nroVenta}",
							"${venta.asociada.estado}", 
							"${venta.asociada.idModulo}", 
							"${venta.asociada.fecha}"
							);
				</c:if>
				
				var itemsVenta = [];
				
				<c:forEach items="${venta.ventaItems}" var="item">
					var it = new Item("${item.productoId}", "${item.cantidad}");
					itemsVenta.push(it); 
				</c:forEach>
				
				ventas["${venta.ventaId}"]['ventaItems'] = itemsVenta;
			</c:forEach>
			}

		var despachos = {};
		<c:forEach items="${requestScope.despachos}" var="despacho">
			despachos["${despacho.numero}"] = new Despacho(
					"${despacho.nombre}", 
					"${despacho.latitud}", 
					"${despacho.longitud}", 
					"${despacho.numero}"
					);
		</c:forEach>
	  $(document).foundation();
	</script>
</html> 