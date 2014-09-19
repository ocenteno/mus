<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
	<title>Insert title here</title>
	<script type="text/javascript" src="js/recargaMesa.js"></script>
<c:if test="${jugadorActual != null}">
	<script type="text/javascript">
		setInterval(recargaMesaDeJuego, 5000);
	</script>
</c:if>
</head>
<body id="mesaJuego">
<a href="./refrescarMesaPartida.html">Refrescar turno</a>
	<table width="100%">
		<tr height="25%">
			<td width="33%">
				<table border="1">
					<tr>
						<td width="50%"></td>
						<td width="25%">Juegos</td>
						<td width="25%">Puntos</td>
					</tr>
					<tr>
						<td>${mesa[0].nombre}<br/>${mesa[2].nombre}
						</td>
						<td align="center">1xxx</td>
						<td align="center">10xxx</td>
					</tr>
					<tr>
						<td>${mesa[1].nombre}<br/>${mesa[3].nombre}
						</td>
						<td align="center">0xxx</td>
						<td align="center">22xxx</td>
					</tr>
				</table>
			</td>
			<td width="34%" align="center">
				<table>
					<tr>
						<td colspan="4" align="center">${mesa[(yo+2)%4].nombre}</td>
					</tr>
					<tr>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
					</tr>
				</table>
			</td>
			<td width="33%"></td>
		</tr>
		<tr height="50%">
			<td align="right">
				<table>
					<tr>
						<td colspan="4" align="center">${mesa[(yo+3)%4].nombre}</td>
					</tr>
					<tr>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
					</tr>
				</table>
			</td>
			<td bgcolor="green">
				<table>
					<tr>
						<td width="5%">
							<c:if test="${mesa[mano] == mesa[(yo+3)%4]}">
								<img id="imagen" alt="" src="imagenes/mazoCartas.jpg"
										width="25px" height="25px" />
							</c:if>						
						</td>
						<td width="90%"></td>
						<td width="5%">
							<c:if test="${mesa[mano] == mesa[(yo+2)%4]}">
								<img id="imagen" alt="" src="imagenes/mazoCartas.jpg"
										width="25px" height="25px" />
							</c:if>						
						</td>
					</tr>
					<tr>
						<td></td>
						<td background="red" height="100px"></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<c:if test="${mesa[mano] == mesa[yo]}">
								<img id="imagen" alt="" src="imagenes/mazoCartas.jpg"
										width="25px" height="25px" />
							</c:if>						
						</td>
						<td></td>
						<td>						
							<c:if test="${mesa[mano] == mesa[(yo+1)%4]}">
								<img id="imagen" alt="" src="imagenes/mazoCartas.jpg"
										width="25px" height="25px" />
							</c:if>						
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td colspan="4" align="center">${mesa[(yo+1)%4].nombre}</td>
					</tr>
					<tr>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
						<td><img id="imagen" alt="" src="imagenes/reversoCarta.jpg"
							width="50px" height="75px" /></td>
					</tr>

				</table>
			</td>
		</tr>
		<tr height="25%">
			<td></td>
			<td align="center">
				<table>
					<tr>
						<td colspan="4" align="center">${mesa[yo].nombre}</td>
					</tr>
					<tr>
						<c:forEach var="i" items="${cartasJugador}">
							<td><img id="imagen" alt="" src="barajas/${i}" width="50px"
								height="75px" /></td>
						</c:forEach>
					</tr>
					<c:if test="${esMiTurno}">
						<c:if test="${hayDescarte}">
							<form action="./accionDescartar.html">
								<tr>
									<td><c:if test="${cartasJugador[0] != null}">
											<input name="descarte0" type="checkbox" />
										</c:if></td>
									<td><c:if test="${cartasJugador[1] != null}">
											<input name="descarte1" type="checkbox" />
										</c:if></td>
									<td><c:if test="${cartasJugador[2] != null}">
											<input name="descarte2" type="checkbox" />
										</c:if></td>
									<td><c:if test="${cartasJugador[3] != null}">
											<input name="descarte3" type="checkbox" />
										</c:if></td>
								</tr>
								<tr></tr>
								<tr>
									<td colspan="4"><input id="descartar" type="submit"
										value="Descartar" /> </td>
								</tr>
							</form>
						</c:if>
						<c:if test="${hayReparto}">
							<form action="./accionReparto.html">
								<tr>
									<td colspan="4"><input id="reparto" type="submit"
										value="Pedir Cartas" /> </td>
								</tr>
							</form>
						</c:if>
					</c:if>
					<tr></tr>
					<tr>
						<td colspan="4">${mensajeError}</td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
	</table>

	<table width="100%">

		<tr>
			<td width="65%"></td>
			<td align="left" width="35%">
				<table>
					<c:if test="${esMiTurno}">
						<c:if test="${hayMus}">
							<tr>
								<td>
									<form action="./accionDarMus.html">
										<input id="mus" type="submit" value="Mus" />
										
									</form>
								</td>
								<td>
									<form action="./accionNoHayMus.html">
										<input id="noMus" type="submit" value="No hay Mus" />
									</form>
								</td>
								<td></td>
							</tr>
						</c:if>
					</c:if>
					<c:if test="${esMiTurno}">
						<c:if test="${hayApuestas}">							
							<c:forEach var="accion" items="${arrayAcciones}">
								<c:if test="${accion == 'PASO'}">
									<tr>
										<td>
											<form action="./accionPaso.html">
												<input id="paso" type="submit" value="Paso" />
											</form>
										</td>
									</tr>
								</c:if>
								<c:if test="${accion == 'QUIERO'}">
									<tr>
										<td>
											<form action="./accionQuiero.html">
												<input id="veo" type="submit" value="Quiero" />
											</form>
										</td>
									</tr>
								</c:if>
								<c:if test="${accion == 'ENVIDO'}">
									<tr>
										<td>
											<form action="./accionEnvido.html">
												<input id="envido" type="submit" value="Envido" />
											</form>
										</td>
									</tr>
								</c:if>
								<c:if test="${accion == 'APUESTA'}">
									<tr>
										<td>
											<form action="./accionXMas.html">
												<input id="apuesta" size="1" maxlength="1" /> 
												<input id="subo" type="submit" value="Más" />
											</form>
										</td>
									</tr>
								</c:if>
								<c:if test="${accion == 'NOQUIERO'}">
									<tr>
										<td>
											<form action="./accionNoQuiero.html">
												<input id="noQuiero" type="submit" value="No quiero" />
											</form>
										</td>
									</tr>
								</c:if>
								<c:if test="${accion == 'ORDAGO'}">
									<tr>
										<td>
											<form action="./accionOrdago.html">
												<input id="ordago" type="submit" value="!Órdago!" />
											</form>
										</td>
									</tr>
								</c:if>							
							</c:forEach>							
						</c:if>
					</c:if>
				</table>
			</td>
		</tr>
	</table>
	<h3> Es el turno de : ${elTurno}</h3>
	<h3> ${accionAnterior}</h3>
</body>
</html>