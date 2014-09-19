var ajax;

function recargaMesaDeJuego() {
	ajax = new XMLHttpRequest();
	ajax.open("POST", "./refrescarMesaPartida.html");
	// tengo que preparar la respuesta
	ajax.onreadystatechange = procesarRespuesta;
	// tengo que hacer la peticion
	ajax.send();
}

function recargaMesaInicial() {
	ajax = new XMLHttpRequest();
	ajax.open("POST", "./refrescarMesa.html");
	// tengo que preparar la respuesta
	ajax.onreadystatechange = procesarRespuesta;
	// tengo que hacer la peticion
	ajax.send();
}

function procesarRespuesta() {
	if (ajax.readyState == 4 && ajax.status == 200) {
		// LA RESPUESTA CONTIENE DATOS txt
		resultadoTxt = ajax.responseText; 
		// Lo convierto a XML
		resultadoXML = procesar(resultadoTxt);
		// Cambio el body del document
		if(resultadoXML){
			var nuevoBody = resultadoXML.getElementsByTagName("body")[0];
			document.body.innerHTML =
				// Para todos, menos IExplorer
				nuevoBody.innerHTML ||
				// Para IExplorer
				nuevoBody.xml;
		}
	}
}

function procesar(resultadoTxt) {
	if (resultadoTxt != null) {
		if (window.DOMParser) {
			// Para todos menos IExplorer
			parser = new DOMParser();
			resultadoXML = parser.parseFromString(resultadoTxt, "text/xml");
		} else {
			// Para IExplorer
			resultadoXML = new ActiveXObject("Microsoft.XMLDOM");
			resultadoXML.async = false;
			resultadoXML.loadXML(resultadoTxt);
		}
	}
	return resultadoXML;
}

