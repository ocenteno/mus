package es.insa.proyecto.mus.web.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import es.insa.proyecto.dominio.cartas.AccionesLance;
import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.FaseDescartes;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGestorFaseDescartes;
import es.insa.proyecto.mus.contratos.IGestorFaseApuestas;
import es.insa.proyecto.mus.modelo.Lances;
import es.insa.proyecto.mus.modelo.Partida;

@Controller("controladorMesaJugador")
public class ControladorMesaJugador {

	/**
	 * La partida es única para todos los jugadores.
	 */
	@Autowired(required = true)
	private Partida partida;
	/**
	 * Es el jugador que utilizamos para pintar la cuatro vistas de la pantalla
	 * de juego.
	 */
	private Jugador jugadorEnviado;
	/**
	 * La posición del jugador que está conectado.
	 */
	private int intYo;
	/**
	 * Se utiliza para ver si estamos en la fase de descartes.
	 */
	@Autowired
	private IGestorFaseDescartes gestorFaseDescartes;
	/**
	 * Se utiliza para ver si estamos en la fase de apuestas.
	 */
	@Autowired
	private IGestorFaseApuestas gestorFaseApuestas;

	/**
	 * Este método inicia la partida
	 * 
	 * @param modelo
	 * @param sesión
	 * @return pantalla mesa jugador
	 */
	@RequestMapping("/iniciarJuego.html")
	public String iniciarPartida(Model m, HttpSession sesion) {
		Jugador[] mesa = partida.getMesa();
		intYo = buscarYo(sesion);
		jugadorEnviado = mesa[intYo];
		// turno de la partida
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes lance = gestorFaseDescartes.faseJuego();
		construirMesaJuego(m, turno, lance, sesion);
		return "mesaJugador";
	}

	/**
	 * Busca la posición del jugador conectado.
	 * 
	 * @param sesion
	 * @return la posición en la mesa.
	 */
	private int buscarYo(HttpSession sesion) {
		String yo = (String) sesion.getAttribute("jugadorActual");
		if (yo != null){
			Jugador[] mesa = partida.getMesa();
			// quien es el jugador de cada vista para buscarlo en la mesa.
			int post = 0;
			for (int i = 0; i < mesa.length; i++) {
				if (yo.equals(mesa[i].getNombre())) {
					post = i;
					break;
				}
			}
			return post;
			}
		return 0;
	}

	/**
	 * Refresca la mesa de la partida.
	 * 
	 * @param Modelo
	 * @param sesion
	 * @return pantalla mesa jugador
	 */
	@RequestMapping("/refrescarMesaPartida.html")
	public String refrescarMesaPartida(Model m, HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		int turno;
		if (fase == FaseDescartes.GRANDE) {
			turno = gestorFaseApuestas.getTurno();
		} else {
			turno = gestorFaseDescartes.turnoJuego();
		}
		construirMesaJuego(m, turno, fase, sesion);
		return "mesaJugador";
	}

	/**
	 * Se ha pulsado el botón MUS y va a pedir MUS.
	 * 
	 * @param modelo
	 * @param sesión
	 * @return pantalla mesa jugador
	 */
	@RequestMapping("/accionDarMus.html")
	public String accionDarMus(String mus,Model m, HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		if (fase == FaseDescartes.MUS) {
			
			if(gestorFaseDescartes.pedirMus(jugadorEnviado)){
				// Si estamos en fase CHICA, GRANDE, JUEGO, PARES,
				// PUNTO, DESCARTE, REPARTO no hay MUS.
				// m. Angeles dice MUS
				String mensajeJugador = jugadorEnviado.getNombre() + " dice : " + mus;
				m.addAttribute("accionAnterior", mensajeJugador);
				
				
			}
				fase = gestorFaseDescartes.faseJuego();
				turno = gestorFaseDescartes.turnoJuego();
				construirMesaJuego(m, turno, fase, sesion);
			
		}
		
		
		return "mesaJugador";
	}

	/**
	 * Se ha pulsado el botón NO HAY MUS y va a apostar.
	 * 
	 * @param modelo
	 * @param sesión
	 * @return pantalla mesa jugador
	 */
	@RequestMapping("/accionNoHayMus.html")
	public String accionNoHayMus(String noMus,Model m, HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		if (fase == FaseDescartes.MUS) {
			gestorFaseDescartes.cortarMus(jugadorEnviado);
		}
		// Si estamos en fase CHICA, GRANDE, JUEGO, PARES,
		// PUNTO, DESCARTE, REPARTO no hay MUS.
		String mensajeJugador = jugadorEnviado.getNombre() + " dice : " + noMus;
		m.addAttribute("accionAnterior", mensajeJugador);
		fase = gestorFaseDescartes.faseJuego();
		turno = gestorFaseDescartes.turnoJuego();
		
		construirMesaJuego(m, turno, fase, sesion);
		return "mesaJugador";
	}

	/**
	 * Descarta las cartas seleccionas por cada jugador en la fase de DESCARTES
	 * 
	 * @param request
	 * @param modelo
	 * @param sesion
	 * @return pantalla mesa jugador
	 */
	@RequestMapping("/accionDescartar.html")
	public String accionDescartar(String descartar,HttpServletRequest req, Model m,
			HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		Carta[] arrayDescartado;
		if (fase == FaseDescartes.DESCARTE) {
			arrayDescartado = mirarDescartes(m, req);
			if(arrayDescartado.length > 0){
				// hay cartas para descartar
				if(gestorFaseDescartes.pedirDescarte(jugadorEnviado, arrayDescartado)){
					String mensajeJugador = jugadorEnviado.getNombre() + " dice : " + descartar;
					m.addAttribute("accionAnterior", mensajeJugador);
					fase = gestorFaseDescartes.faseJuego();
					turno = gestorFaseDescartes.turnoJuego();
				}
				
			} else {
				// no hay cartas para descartar
				m.addAttribute("mensajeError","Se debe descartar al menos de una carta");
			}
			
		}
		// Si estamos en fase CHICA, GRANDE, JUEGO, PARES,
		// PUNTO, DESCARTE, REPARTO no hay DESCARTE.
		fase = gestorFaseDescartes.faseJuego();
		turno = gestorFaseDescartes.turnoJuego();
		construirMesaJuego(m, turno, fase, sesion);
		return "mesaJugador";
	}

	/**
	 * Reparte las cartas despues del descarte en la fase de REPARTO
	 * 
	 * @param request
	 * @param modelo
	 * @param sesion
	 * @return pantalla mesa jugador
	 */
	@RequestMapping("/accionReparto.html")
	public String accionReparto(String reparto,HttpServletRequest req, Model m,
			HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		if (fase == FaseDescartes.REPARTO) {
			gestorFaseDescartes.reparte(jugadorEnviado);
		}
		// Si estamos en fase CHICA, GRANDE, JUEGO, PARES, PUNTO,
		// DESCARTE, MUS no hay REPARTO
		String mensajeJugador = jugadorEnviado.getNombre() + " dice : " + reparto;
		m.addAttribute("accionAnterior", mensajeJugador);
		fase = gestorFaseDescartes.faseJuego();
		turno = gestorFaseDescartes.turnoJuego();
		construirMesaJuego(m, turno, fase, sesion);
		return "mesaJugador";
	}

	/**
	 * Se ha pulsado el botón PASO y va a la acción PASAR tiene que estar en la
	 * fase de GRANDE.
	 * 
	 * @param modelo
	 * @param sesion
	 * @return pantalla mesa jugador.
	 */
	@RequestMapping("/accionPaso.html")
	public String accionPaso(String paso,Model m, HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		String mensajeJugador = jugadorEnviado.getNombre() + " dice : " + paso;
		m.addAttribute("accionAnterior", mensajeJugador);
		if (fase == FaseDescartes.GRANDE) {
			// Hay que coger los datos del gestor de apuestas
			Lances lance = gestorFaseApuestas.getFase();
			int turnoDescarte = gestorFaseApuestas.getTurno();
			gestorFaseApuestas.ejecutar(jugadorEnviado, lance,AccionesLance.PASO);
			lance = gestorFaseApuestas.getFase();
			construirMesaJuegoLances(m, turnoDescarte, lance, sesion);
		} else {
			fase = gestorFaseDescartes.faseJuego();
			turno = gestorFaseDescartes.turnoJuego();
			construirMesaJuego(m, turno, fase, sesion);
		}
		return "mesaJugador";
	}

	/**
	 * Se ha pulsado el botón QUIERO y va a la acción QUIERO tiene que estar en
	 * la fase de GRANDE.
	 * 
	 * @param modelo
	 * @param sesion
	 * @return pantalla mesa jugador.
	 */
	@RequestMapping("/accionQuiero.html")
	public String accionQuiero(String veo,Model m, HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		String mensajeJugador = jugadorEnviado.getNombre() + " dice : " + veo;
		m.addAttribute("accionAnterior", mensajeJugador);
		if (fase == FaseDescartes.GRANDE) {
			// Hay que coger los datos del gestor de apuestas
			Lances lance = gestorFaseApuestas.getFase();
			int turnoDescarte = gestorFaseApuestas.getTurno();
			gestorFaseApuestas.ejecutar(jugadorEnviado, lance,
					AccionesLance.QUIERO);
			lance = gestorFaseApuestas.getFase();
			construirMesaJuegoLances(m, turnoDescarte, lance, sesion);
		} else {
			turno = gestorFaseDescartes.turnoJuego();
			fase = gestorFaseDescartes.faseJuego();
			construirMesaJuego(m, turno, fase, sesion);
		}
		return "mesaJugador";
	}

	/**
	 * Se ha pulsado el botón ENVIDO y va a la acción ENVIDO tiene que estar en
	 * la fase de GRANDE.
	 * 
	 * @param modelo
	 * @param sesion
	 * @return pantalla mesa jugador.
	 */
	@RequestMapping("/accionEnvido.html")
	public String accionEnvido(String envido,Model m, HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		String mensajeJugador = jugadorEnviado.getNombre() + " dice : " + envido;
		m.addAttribute("accionAnterior", mensajeJugador);
		if (fase == FaseDescartes.GRANDE) {
			// Hay que coger los datos del gestor de apuestas
			Lances lance = gestorFaseApuestas.getFase();
			int turnoDescarte = gestorFaseApuestas.getTurno();
			gestorFaseApuestas.ejecutar(jugadorEnviado, lance,
					AccionesLance.ENVIDO);
			lance = gestorFaseApuestas.getFase();
			construirMesaJuegoLances(m, turnoDescarte, lance, sesion);
		} else {
			turno = gestorFaseDescartes.turnoJuego();
			fase = gestorFaseDescartes.faseJuego();
			construirMesaJuego(m, turno, fase, sesion);
		}
		return "mesaJugador";
	}

	/**
	 * Se ha pulsado el botón APOSTAR y va a la acción APOSTAR tiene que estar
	 * en la fase de GRANDE.
	 * 
	 * @param modelo
	 * @param sesion
	 * @return pantalla mesa jugador.
	 */
	@RequestMapping("/accionXMas.html")
	public String accionXMas(String subo,HttpServletRequest req, Model m, HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		String mensajeJugador = jugadorEnviado.getNombre() + " dice : " + subo;
		m.addAttribute("accionAnterior", mensajeJugador);
		if (fase == FaseDescartes.GRANDE) {
			int apuesta = Integer.parseInt(req.getParameter("apuesta"));
			// Hay que coger los datos del gestor de apuestas
			Lances lance = gestorFaseApuestas.getFase();
			int turnoDescarte = gestorFaseApuestas.getTurno();
			gestorFaseApuestas.ejecutar(jugadorEnviado, lance,
					AccionesLance.APUESTA, apuesta);
			lance = gestorFaseApuestas.getFase();
			construirMesaJuegoLances(m, turnoDescarte, lance, sesion);
		} else {
			turno = gestorFaseDescartes.turnoJuego();
			fase = gestorFaseDescartes.faseJuego();
			construirMesaJuego(m, turno, fase, sesion);
		}
		return "mesaJugador";
	}

	/**
	 * Se ha pulsado el botón ORDAGO y va a la acción ORDAGO tiene que estar en
	 * la fase de GRANDE.
	 * 
	 * @param modelo
	 * @param sesion
	 * @return pantalla mesa jugador.
	 */
	@RequestMapping("/accionOrdago.html")
	public String accionOrdago(Model m, HttpSession sesion) {
		// primero buscamos quien soy
		intYo = buscarYo(sesion);
		jugadorEnviado = partida.getMesa()[intYo];
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		if (fase == FaseDescartes.GRANDE) {
			// Hay que coger los datos del gestor de apuestas
			Lances lance = gestorFaseApuestas.getFase();
			int turnoDescarte = gestorFaseApuestas.getTurno();
			gestorFaseApuestas.ejecutar(jugadorEnviado, lance,
					AccionesLance.ORDAGO);
			lance = gestorFaseApuestas.getFase();
			construirMesaJuegoLances(m, turnoDescarte, lance, sesion);
		} else {
			turno = gestorFaseDescartes.turnoJuego();
			fase = gestorFaseDescartes.faseJuego();
			construirMesaJuego(m, turno, fase, sesion);
		}
		return "mesaJugador";
	}

	/**
	 * Este método mira las cartas de las que se ha descartado un jugador
	 * 
	 * @param modelo
	 * @param request
	 */
	public Carta[] mirarDescartes(Model m, HttpServletRequest req) {
		// Cuando marcamos las cartas a descartar, primero hay que comprobar
		// que al menos hay una seleccionada, si no damos mensaje de error y
		// retornamos a la página.
		int contadorDescartes = 0;
		boolean descartado0 = false;
		boolean descartado1 = false;
		boolean descartado2 = false;
		boolean descartado3 = false;
		// Contar numero de descartes
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
		Carta[] arrayCartasDescarte = new Carta[contadorDescartes];
		
		if (contadorDescartes >= 1) {
			// Ha seleccionado por lo menos 1			
			// Cogemos la carta y la metemos en el array
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
			
			
			 

		} 
		// Descartamos las cartas
		return arrayCartasDescarte;
				
				
	}

	/**
	 * 
	 * @param req
	 * @param m
	 * @param sesion
	 * @return
	 */
	@RequestMapping("/accionNoQuiero.html")
	public String accionNoQuiero(HttpServletRequest req, Model m,
			HttpSession sesion) {
		int turno = gestorFaseDescartes.turnoJuego();
		FaseDescartes fase = gestorFaseDescartes.faseJuego();
		if (fase == FaseDescartes.GRANDE) {
			// Hay que coger los datos del gestor de apuestas
			Lances lance = gestorFaseApuestas.getFase();
			int turnoDescarte = gestorFaseApuestas.getTurno();
			gestorFaseApuestas.ejecutar(jugadorEnviado, lance,
					AccionesLance.NOQUIERO);
			lance = gestorFaseApuestas.getFase();
			construirMesaJuegoLances(m, turnoDescarte, lance, sesion);
		} else {
			turno = gestorFaseDescartes.turnoJuego();
			fase = gestorFaseDescartes.faseJuego();
			construirMesaJuego(m, turno, fase, sesion);
		}
		return "mesaJugador";

	}

	/**
	 * Construir la mesa de juego
	 * 
	 * @param modelo
	 * @param elQueHabla
	 *            es el que le toca jugar
	 * @param loQueDice
	 *            es la fase en la que estamos
	 * @param sesion
	 */
	
	public void construirMesaJuegoLances(Model m, int elQueHabla,
			Lances loQueDice, HttpSession sesion) {
		intYo = buscarYo(sesion);
		// pintar pantalla
		Jugador[] mesa = partida.getMesa();
		int numeroCartas = mesa[intYo].getMano().length;
		String[] cartasJugador = new String[numeroCartas];
		Carta[] cartasYo = mesa[intYo].getMano();
		for (int i = 0; i < numeroCartas; i++) {
			cartasJugador[i] = cartasYo[i].getPalo() + ""
					+ cartasYo[i].getNumero() + ".jpg";
		}
		boolean hayMus = false;
		boolean hayDescarte = false;
		boolean hayReparto = false;
		boolean hayApuestas = false;
		if (loQueDice.equals(FaseDescartes.MUS)) {
			hayMus = true;
		} else if (loQueDice.equals(FaseDescartes.DESCARTE)) {
			hayDescarte = true;
		} else if (loQueDice.equals(FaseDescartes.REPARTO)) {
			hayReparto = true;
		} else if (loQueDice.equals(FaseDescartes.GRANDE)) {
			hayApuestas = true;
		}
		String elTurno = mesa[elQueHabla].getNombre();
		m.addAttribute("cartasJugador", cartasJugador);
		m.addAttribute("mesa", mesa);
		m.addAttribute("partida", partida);
		m.addAttribute("hayMus", hayMus);
		m.addAttribute("hayDescarte", hayDescarte);
		m.addAttribute("hayReparto", hayReparto);
		m.addAttribute("hayApuestas", hayApuestas);
		m.addAttribute("loQueDice", loQueDice);
		m.addAttribute("elTurno", elTurno);
		// acciones
		m.addAttribute("arrayAcciones", gestorFaseApuestas.getAcciones());
		m.addAttribute("yo", intYo);
		m.addAttribute("mano", partida.getMano());
		boolean esMiTurno = false;
		// si elQueHabla soy yo se activan todos los botones porque no es su
		// turno
		if (elQueHabla == intYo) {
			esMiTurno = true;
		}
		m.addAttribute("esMiTurno", esMiTurno);
		

	}
	public void construirMesaJuego(Model m, int elQueHabla,
			FaseDescartes loQueDice, HttpSession sesion) {
		intYo = buscarYo(sesion);
		// pintar pantalla
		Jugador[] mesa = partida.getMesa();
		int numeroCartas = mesa[intYo].getMano().length;
		String[] cartasJugador = new String[numeroCartas];
		Carta[] cartasYo = mesa[intYo].getMano();
		for (int i = 0; i < numeroCartas; i++) {
			cartasJugador[i] = cartasYo[i].getPalo() + ""
					+ cartasYo[i].getNumero() + ".jpg";
		}
		boolean hayMus = false;
		boolean hayDescarte = false;
		boolean hayReparto = false;
		boolean hayApuestas = false;
		if (loQueDice.equals(FaseDescartes.MUS)) {
			hayMus = true;
		} else if (loQueDice.equals(FaseDescartes.DESCARTE)) {
			hayDescarte = true;
		} else if (loQueDice.equals(FaseDescartes.REPARTO)) {
			hayReparto = true;
		} else if (loQueDice.equals(FaseDescartes.GRANDE)) {
			hayApuestas = true;
		}
		String elTurno = mesa[elQueHabla].getNombre();
		m.addAttribute("cartasJugador", cartasJugador);
		m.addAttribute("mesa", mesa);
		m.addAttribute("partida", partida);
		m.addAttribute("hayMus", hayMus);
		m.addAttribute("hayDescarte", hayDescarte);
		m.addAttribute("hayReparto", hayReparto);
		m.addAttribute("hayApuestas", hayApuestas);
		m.addAttribute("loQueDice", loQueDice);
		m.addAttribute("elTurno", elTurno);
		// acciones
		m.addAttribute("arrayAcciones", gestorFaseApuestas.getAcciones());
		m.addAttribute("yo", intYo);
		m.addAttribute("mano", partida.getMano());
		boolean esMiTurno = false;
		// si elQueHabla soy yo se activan todos los botones porque no es su
		// turno
		if (elQueHabla == intYo) {
			esMiTurno = true;
		}
		m.addAttribute("esMiTurno", esMiTurno);
		

	}

}
