<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login Monitoreo</title>
	<!-- Scripts -->
</head>
<body>
	<%-- <jsp:include page="default.jsp"></jsp:include> --%>
	<%@include file="default.jsp" %>
	<br>
	<div id="login-wrapper" class="row">
		<div class="large-10 columns">
			<div class="row">
				<div class="large-12 columns">
					<h3>Ingrese su password y contraseña</h3>
				</div>
			</div>
			<div>
				<label>Usuario &nbsp;
					<input id="username" type="text" placeholder="Escriba su nombre de usuario">
				</label>
				<label>&nbsp; Contraseña &nbsp;
					<input id="password" type="password" placeholder="Escriba su contraseña">
				</label>
				<br>
				<br>
				<div style="margin-left:40%;"class="btn btn-primary">
					<div class="large-3 large-offset-9 columns">
						<div id="submit-login" class="button success radius large">Ingresar</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>