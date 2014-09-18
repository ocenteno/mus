package  es.insa.proyecto.mus.web.controladores;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import es.insa.proyecto.dominio.cartas.AccionesLance;
import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.FaseDescartes;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.contratos.IGestorFaseDescartes;
import es.insa.proyecto.mus.contratos.IGestorFaseApuestas;
import es.insa.proyecto.mus.modelo.Partida;


@Controller("controladorMesaJugador")
public class ControladorMesaJugador {
	
	// para que no se pierda la patida
	@Autowired(required=true)
	private Partida partida;
	
	private Jugador jugadorEnviado;
	int intYo;
	@Autowired
	private IGestorFaseDescartes gestorFaseDescartes;
	private IGestorFaseApuestas gestorFaseApuestas;
		
	
	/**
	 * Este método inicia la partida 
	 * 
	 * 
	 * @param m
	 * @param partida
	 * @param yo es el nombre del jugador que es mano
	 * @return
	 */
	@RequestMapping("/iniciarJuego.html")
	public String iniciarPartida(Model m, HttpSession sesion){
		
		String yo = (String) sesion.getAttribute("jugadorActual");
		
				
		// QUITARRRRRRRRRRRRRRRR  ---------------- < OJO > -----------------------
		
		partida.getMesa()[0].añadirCarta(new Carta(Palo.BASTOS, 1, 1));
		partida.getMesa()[0].añadirCarta(new Carta(Palo.COPAS, 4, 4));
		partida.getMesa()[0].añadirCarta(new Carta(Palo.OROS, 3, 3));
		partida.getMesa()[0].añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		
		partida.getMesa()[1].añadirCarta(new Carta(Palo.BASTOS, 3, 3));
		partida.getMesa()[1].añadirCarta(new Carta(Palo.COPAS, 1, 1));
		partida.getMesa()[1].añadirCarta(new Carta(Palo.OROS, 1, 1));
		partida.getMesa()[1].añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		
		partida.getMesa()[2].añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		partida.getMesa()[2].añadirCarta(new Carta(Palo.COPAS, 10, 10));
		partida.getMesa()[2].añadirCarta(new Carta(Palo.OROS, 4, 4));
		partida.getMesa()[2].añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		
		partida.getMesa()[3].añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		partida.getMesa()[3].añadirCarta(new Carta(Palo.COPAS, 12, 12));
		partida.getMesa()[3].añadirCarta(new Carta(Palo.OROS, 10, 10));
		partida.getMesa()[3].añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		
		//TENEMOS QUE LLAMAR A LOS METODOS PARA OBTENER ESTOS VALORES
		//  turno es la posición de la mesa del jugador que habla
		//  fase es lo que puede decir el jugador que habla (mus, descarte, repartir, apuestas)
		
		
		int turno = gestorFaseDescartes.turnoJuego();		
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		
		/* DESASTERISCAR CUANDO SE ACABE LA PRUEBA --------------  < OJO > 
		int turno;
		FasesJuego fase;
		turno = 0;
		fase = FasesJuego.REPARTO;
		*/
		fase = FaseDescartes.DESCARTE;
		//loQueDice = "DESCARTE";
		//loQueDice = "REPARTO";
		//loQueDice = "GRANDE";
		
		
		
		
		Jugador[] mesa =  partida.getMesa();
		 // quien es soy yo
		// vamos a buscar donde está ese jugador en la mesa
		int post = 0;
		for (int i = 0; i < mesa.length; i++) {
					
			if(mesa[i].getNombre().equals(yo)){
					post = i;
					break;
			}
		}
		intYo = post;		
		jugadorEnviado = mesa[intYo];
		construirMesaJuego(m, turno,fase);
		
		return "mesaJugador";
	}
	
	@RequestMapping("/refrescarMesaPartida.html")
	public String refrescarMesaPartida(Model m, HttpSession sesion){
		int turno = gestorFaseDescartes.turnoJuego();		
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		
		construirMesaJuego(m,turno,fase);
		return "mesaJugador";
	}
	
	
	@RequestMapping("/accionDarMus.html")
	public String accionDarMus(Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		//Jugador jugador = req.getParameter("jugadorEnviado");

		//MUS
		switch (fase) {
		case MUS:{
			gestorFaseDescartes.pedirMus(jugadorEnviado);
			break;
		}
		default:
			break;
		}
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO, DESCARTE, REPARTO 
		//En estas fases no hay MUS
			
		construirMesaJuego(m,turno,fase);
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionNoHayMus.html")
	public String accionNoHayMus(Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();

		//MUS
		switch (fase) {
		case MUS:{
			gestorFaseDescartes.cortarMus(jugadorEnviado);
			break;
		}
		default:
			break;
		}
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO, DESCARTE, REPARTO 
		//En estas fases no hay MUS
			
		construirMesaJuego(m,turno,fase);
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionPaso.html")
	public String accionPaso(Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO
		switch (fase) {
		case GRANDE:{
			gestorFaseApuestas.faseGrande(jugadorEnviado, AccionesLance.PASO, 0);
			break;
		}			
		case CHICA:{
			gestorFaseApuestas.faseChica(jugadorEnviado, AccionesLance.PASO, 0);
			break;
		}	
		case JUEGO:{
			gestorFaseApuestas.faseJuego(jugadorEnviado, AccionesLance.PASO, 0);
			break;
		}			
		case PARES:{
			gestorFaseApuestas.fasePares(jugadorEnviado, AccionesLance.PASO, 0);
			break;
		}			
		case PUNTO:{
			gestorFaseApuestas.fasePunto(jugadorEnviado, AccionesLance.PASO, 0);
			break;
		}			
		default:
			break;
		}
		
		//DESCARTE, MUS, REPARTO
		//En estas fases no hay PASO
			
		construirMesaJuego(m,turno,fase);
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionQuiero.html")
	public String accionQuiero(Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO
		switch (fase) {
		case GRANDE:{
			gestorFaseApuestas.faseGrande(jugadorEnviado, AccionesLance.QUIERO, 0);
			break;
		}			
		case CHICA:{
			gestorFaseApuestas.faseChica(jugadorEnviado, AccionesLance.QUIERO, 0);
			break;
		}	
		case JUEGO:{
			gestorFaseApuestas.faseJuego(jugadorEnviado, AccionesLance.QUIERO, 0);
			break;
		}			
		case PARES:{
			gestorFaseApuestas.fasePares(jugadorEnviado, AccionesLance.QUIERO, 0);
			break;
		}			
		case PUNTO:{
			gestorFaseApuestas.fasePunto(jugadorEnviado, AccionesLance.QUIERO, 0);
			break;
		}			
		default:
			break;
		}
		
		//DESCARTE, MUS, REPARTO
		//En estas fases no hay QUIERO
		
		construirMesaJuego(m,turno,fase);
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionEnvido.html")
	public String accionEnvido(Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO
		switch (fase) {
		case GRANDE:{
			gestorFaseApuestas.faseGrande(jugadorEnviado, AccionesLance.ENVIDO, 2);
			break;
		}			
		case CHICA:{
			gestorFaseApuestas.faseChica(jugadorEnviado, AccionesLance.ENVIDO, 2);
			break;
		}	
		case JUEGO:{
			gestorFaseApuestas.faseJuego(jugadorEnviado, AccionesLance.ENVIDO, 2);
			break;
		}			
		case PARES:{
			gestorFaseApuestas.fasePares(jugadorEnviado, AccionesLance.ENVIDO, 2);
			break;
		}			
		case PUNTO:{
			gestorFaseApuestas.fasePunto(jugadorEnviado, AccionesLance.ENVIDO, 2);
			break;
		}			
		default:
			break;
		}
		
		//DESCARTE, MUS, REPARTO
		//En estas fases no hay ENVIDO
		
		construirMesaJuego(m,turno,fase);
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionXMas.html")
	public String accionXMas(HttpServletRequest req, Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		int apuesta = Integer.parseInt(req.getParameter("apuesta"));
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO
		switch (fase) {
		case GRANDE:{
			gestorFaseApuestas.faseGrande(jugadorEnviado, AccionesLance.APUESTA, apuesta);
			break;
		}			
		case CHICA:{
			gestorFaseApuestas.faseChica(jugadorEnviado, AccionesLance.APUESTA, apuesta);
			break;
		}	
		case JUEGO:{
			gestorFaseApuestas.faseJuego(jugadorEnviado, AccionesLance.APUESTA, apuesta);
			break;
		}			
		case PARES:{
			gestorFaseApuestas.fasePares(jugadorEnviado, AccionesLance.APUESTA, apuesta);
			break;
		}			
		case PUNTO:{
			gestorFaseApuestas.fasePunto(jugadorEnviado, AccionesLance.APUESTA, apuesta);
			break;
		}			
		default:
			break;
		}
		
		//DESCARTE, MUS, REPARTO
		//En estas fases no hay XMas
		
		construirMesaJuego(m,turno,fase);
		
		return "mesaJugador";
	}
	
	
	@RequestMapping("/accionDescartar.html")
	public String accionDescartar(HttpServletRequest req, Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();		
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		
		//DESCARTE
		switch (fase) {
		case DESCARTE:{
			mirarDescartes(m, req);
			break;
		}
		default:
			break;
		}
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO, REPARTO, MUS
		//En estas fases no hay DESCARTE
		
		construirMesaJuego(m, turno, fase);
		
		return "mesaJugador";
	}

	
	@RequestMapping("/accionOrdago.html")
	public String accionOrdago(Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO
		switch (fase) {
		case GRANDE:{
			gestorFaseApuestas.faseGrande(jugadorEnviado, AccionesLance.ORDAGO, 40);
			break;
		}			
		case CHICA:{
			gestorFaseApuestas.faseChica(jugadorEnviado, AccionesLance.ORDAGO, 40);
			break;
		}	
		case JUEGO:{
			gestorFaseApuestas.faseJuego(jugadorEnviado, AccionesLance.ORDAGO, 40);
			break;
		}			
		case PARES:{
			gestorFaseApuestas.fasePares(jugadorEnviado, AccionesLance.ORDAGO, 40);
			break;
		}			
		case PUNTO:{
			gestorFaseApuestas.fasePunto(jugadorEnviado, AccionesLance.ORDAGO, 40);
			break;
		}			
		default:
			break;
		}
		
		//DESCARTE, MUS, REPARTO
		//En estas fases no hay ORDAGO
		
		construirMesaJuego(m,turno,fase);
		
		return "mesaJugador";
	}
	
	
	
	public void mirarDescartes(Model m, HttpServletRequest req) {

		// Cuando damos a descartar primero hay que comprobar que hay por lo
		// menos una seleccionada
		// sino hay seleccionada damos un mensaje de error y retornamos a la
		// página

		int contadorDescartes = 0;
		boolean descartado0 = false;
		boolean descartado1 = false;
		boolean descartado2 = false;
		boolean descartado3 = false;
		// Contar nuemero de descartes
		String valor = req.getParameter("descarte0");
		if (!(valor == null)) {
			contadorDescartes++;
			descartado0 = true;
		}
		valor = req.getParameter("descarte1");
		if (!(valor == null)) {
			contadorDescartes++;
			descartado1 = true;
		}
		valor = req.getParameter("descarte2");
		if (!(valor == null)) {
			contadorDescartes++;
			descartado2 = true;
		}
		valor = req.getParameter("descarte3");
		if (!(valor == null)) {
			contadorDescartes++;
			descartado3 = true;
		}
		if (contadorDescartes >= 1) {
			// Ha seleccionado por lo menos 1
			Carta[] arrayCartasDescarte = new Carta[contadorDescartes];

			// Metemos las cartas en el array
			// cogemos la carta
			int contador = 0;
			if (descartado0) {
				arrayCartasDescarte[contador] = partida.getMesa()[intYo]
						.getMano()[0];
				contador++;
			}
			if (descartado1) {
				arrayCartasDescarte[contador] = partida.getMesa()[intYo]
						.getMano()[1];
				contador++;
			}
			if (descartado2) {
				arrayCartasDescarte[contador] = partida.getMesa()[intYo]
						.getMano()[2];
				contador++;
			}
			if (descartado3) {
				arrayCartasDescarte[contador] = partida.getMesa()[intYo]
						.getMano()[3];
				contador++;
			}

			// Descartamos las cartas
			//iGestorFaseJuego.pedirDescarte(partida.getMesa()[intYo], arrayCartasDescarte);
			gestorFaseDescartes.pedirDescarte(jugadorEnviado, arrayCartasDescarte);
			

		} else {
			// no ha seleccionado ninguno
			m.addAttribute("mensajeError",
					"Se debe descartar al menos de una carta");

		}

	}
	
	@RequestMapping("/accionReparto.html")
	public String accionReparto(HttpServletRequest req, Model m){
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		
		//REPARTO
		switch (fase) {
		case REPARTO:{
			gestorFaseDescartes.reparte(jugadorEnviado);
			break;
		}	
		default:
			break;
		}
		
		//CHICA, GRANDE, JUEGO, PARES, PUNTO, DESCARTE, MUS
		//En estas fases no hay REPARTO
		
		construirMesaJuego(m,turno,fase);
		
		return "mesaJugador";
	}
	

	
public void construirMesaJuego(Model m, int elQueHabla, FaseDescartes loQueDice){
		
		// pintar pantalla
		Jugador[] mesa =  partida.getMesa();
		Jugador[] mesaPantalla = new Jugador[4];
		// rellenar el array de mesa
				for (int i = 0; i < 4; i++) {
					
					mesaPantalla[i] = mesa[(intYo + i)%4];			
					
				} 
				
				
				int[] manoPantalla = new int[4];
				
				int mano = partida.getMano();
				
				// rellenar el array de mano
				for (int i = 0; i < 4; i++) {
					if(((intYo + i)%4) == mano){
						// hay que pintar el mazo
						manoPantalla[i]= 1;
					}else{
						// NOOO hay que pintar el mazo
						manoPantalla[i]= 0;
					}
					
				}
				// Mando el array de cartas que se ven
				//mesaPantalla[0].getMano()
				int numeroCartas = mesa[intYo].getMano().length;
				String[] cartasJugador = new String[numeroCartas];
				Carta[] cartasYo = mesa[intYo].getMano();
				
				for (int i = 0; i < numeroCartas; i++){
					cartasJugador[i] = cartasYo[i].getPalo() + "" + cartasYo[i].getNumero() + ".jpg"; 
				}
					
				String activarBotones = "";	
				if (intYo == elQueHabla){
					activarBotones = "visible";
				}else {
					activarBotones = "hidden";
				}
				
				boolean hayMus = false;
				boolean hayDescarte = false;
				boolean hayReparto = false;
				boolean hayApuestas = false;
				
				if(loQueDice.equals(FaseDescartes.MUS)){
					hayMus = true;
					
				} else if(loQueDice.equals(FaseDescartes.DESCARTE)){
					hayDescarte = true;
					
				} else if (loQueDice.equals(FaseDescartes.REPARTO)){
					hayReparto = true;
					
				} else if (loQueDice.equals(FaseDescartes.GRANDE)){
					hayApuestas = true;
					
				}
				
				// PARA PINTAR EL MAZO
				
				m.addAttribute("mesaPantalla",mesaPantalla);
				m.addAttribute("manoPantalla",manoPantalla);
				m.addAttribute("cartasJugador",cartasJugador);
				m.addAttribute("activarBotones",activarBotones);
				m.addAttribute("mesa",mesa);
				
				m.addAttribute("hayMus",hayMus);
				m.addAttribute("hayDescarte",hayDescarte);
				m.addAttribute("hayReparto",hayReparto);
				m.addAttribute("hayApuestas",hayApuestas);
				m.addAttribute("loQueDice",loQueDice);
				
				boolean esMiTurno = false;
				// elQueHabla
				// si elQueHabla no soy yo desactiva todos los botones porque no es su turno
				if (elQueHabla == intYo){
					// Se activan los botones correspondientes
					esMiTurno = true;
				}
				m.addAttribute("esMiTurno",esMiTurno);
		
	}
	

}
