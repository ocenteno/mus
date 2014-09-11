<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">


</script>
</head>
<body>


	<h1>BIENVENIDOS AL JUEGO DE MUS!!!!</h1>

	<h3>Para unirte a la partida, indica tu nombre y siéntate en una
		silla libre</h3>
	Nombre:
	<input name="nombre" id="jugador" type="text" />


	
	
	<c:forEach items="${resultado }" var="m">
	
		<  ${m.nombre == null ? "pintosilla" : "${m.nombre}"
		
		<form ></form>
	</c:forEach>
	
	
	<table>
	<form id="formulario" action="./mesaInicial.htlm"> 
		<tr>
			<td></td>
			<td><input id="silla0" type="submit" value="SIENTATE"/><img id="imagen" alt="" src="imagenes/silla.jpg" width="50px"
				height="75px"></td>
			<td></td>
		</tr>
		<tr>
			<td><input id="silla3" type="submit" value="SIENTATE"/><img id="imagen" alt="" src="imagenes/silla.jpg" width="50px"
				height="75px"></td>
			
			<td><img id="imagen" alt="" src="imagenes/mesa.jpg" width="50px" height="75px"></td>
			<td><input id="silla1" type="submit" value="SIENTATE"/><img id="imagen" alt="" src="imagenes/silla.jpg" width="50px"
				height="75px"></td>
		</tr>
		<tr>
			<td></td>
			<td><input id="silla2" type="submit" value="SIENTATE"/><img id="imagen" alt="" src="imagenes/silla.jpg" width="50px"
				height="75px"></td>
			<td></td>
		</tr>
		</form>
	</table>


</body>
</html>