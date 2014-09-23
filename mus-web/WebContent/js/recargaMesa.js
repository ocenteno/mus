var ajax;

function recargaMesaDeJuego() {
	ajax = new XMLHttpRequest();
	ajax.open("POST", "./refrescarMesaPartida.html");
	ajax.responseURL = "./refrescarMesaPartida.html";
	// tengo que preparar la respuesta
	ajax.onreadystatechange = procesarRespuesta;
	// tengo que hacer la peticion
	ajax.send();
}

function recargaMesaInicial() {
	ajax = new XMLHttpRequest();
	ajax.open("POST", "./refrescarMesa.html");
	ajax.responseURL = "./refrescarMesa.html";
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
			// Si canbia el ID del body redirijo la p�gina
			var nuevoId = nuevoBody.id || nuevoBody.getAttribute("id");
			if(nuevoId != document.body.id){
				location.href = ajax.responseURL;
			}else{
				document.body.innerHTML =
					// Para todos, menos IExplorer
					nuevoBody.innerHTML ||
					// Para IExplorer
					nuevoBody.xml;
			}
		}
	}
}

/**
 * Procesa un texto con contenido XML y devuelve el resultado en formato DOM.
 * Dependiendo del navegador el procesamiento se hará mediante DOMParser (navegadores compatibles)
 * o mediante ActiveX (InternetExplorer)
 * @param {String} Texto a procesar
 * @return Documento DOM tras procesar el texto enviado por parámetro o null si se ha producido algún error
 */
function procesar(resultadoTxt) {
	var resultadoXML = null;
	if (resultadoTxt != null) {
		if (window.DOMParser && !esIExplorer10()) {
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

/**
 * Comprueba si el navegador es IExplorer 10
 * @return {Boolean} true si es IE10 y false en caso contrario
 */ 
function esIExplorer10(){
	return navigator.userAgent.toLowerCase().indexOf('trident') > -1;
}

