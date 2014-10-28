<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login Monitoreo</title>
	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/scripts/jquery.js" ></script>
	<script src="${pageContext.request.contextPath}/scripts/foundation.min.js" ></script>
	<!-- Styles -->
	<link type="text/css" href="${pageContext.request.contextPath}/styles/normalize.css" rel="stylesheet">
	<link type="text/css" href="${pageContext.request.contextPath}/styles/foundation.min.css" rel="stylesheet">
</head>
<body>

	<div id="login-wrapper" class="row">
		<div class="large-10 columns">
			<div class="row">
				<div class="large-12 columns">
					<h1>Ingrese su password y contraseña</h1>
				</div>
			</div>
			<div class="panel">
				<label>Usuario
					<input id="username" type="text" placeholder="Escriba su nombre de usuario">
				</label>
				<label>Contraseña
					<input id="password" type="password" placeholder="Escriba su contraseña">
				</label>
				<div class="row">
					<div class="large-3 large-offset-9 columns">
						<div id="submit-login" class="button success radius large">Ingresar</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>