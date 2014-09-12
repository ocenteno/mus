package  es.insa.proyecto.mus.web;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.modelo.Partida;


@Controller("controladorMesaJugador")
public class ControladorMesaJugador {
	
	// para que no se pierda la patida
	Partida partidaInicial;
	int elQueHablaInicial;
	
	
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
	public String iniciarPartida(Model m, Partida partida, HttpSession sesion){
		
		partidaInicial = partida;
		String yo = (String) sesion.getAttribute("jugadorActual");
		
		// nos pasan en el Modelo el jugador conectado		
		// y nos pasan la partida
		
		// QUITARRRRRRRRR
		partida = TestInicializarPartida();
		yo="Pepito0";		
		//yo="Juanito1";
		//yo="Juanito2";
		//yo="Juanito3";
		
		//TENEMOS QUE LLAMAR A LOS METODOS PARA OBTENER ESTOS VALORES
		//  elQueHabla es la posición de la mesa del jugador que habla
		//  			Si devuelven
		//  loQueDice es lo que puede decir el jugador que habla (mus, descarte, repartir, apuestas)

		
		int elQueHabla = 0;
		String loQueDice = "Mus";
		
		//partida.getPareja1().getJuegosGanados();
		//partida.getPareja1().getPiedrasGanadas();
		
		
		Jugador[] mesa =  partida.getMesa();
		// pintar pantalla
		Jugador[] mesaPantalla = new Jugador[4];;
		 // quien es soy yo
		// vamos a buscar donde está ese jugador en la mesa
		int post = 0;
		for (int i = 0; i < mesa.length; i++) {
					
			if(mesa[i].getNombre().equals(yo)){
					post = i;
					break;
			}
		}
		elQueHablaInicial = post;
		
		// rellenar el array de mesa
		for (int i = 0; i < 4; i++) {
			
			mesaPantalla[i] = mesa[(post + i)%4];			
			
		} 
		
		
		int[] manoPantalla = new int[4];
		// Buscamos quien es la mano
		int mano = partida.getMano();
		
		// rellenar el array de mano
		for (int i = 0; i < 4; i++) {
			if(((post + i)%4) == mano){
				// hay que pintar el mazo
				manoPantalla[i]= 1;
			}else{
				// NOOO hay que pintar el mazo
				manoPantalla[i]= 0;
			}
			
		}
		// Mando el array de cartas que se ven
		//mesaPantalla[0].getMano()
		String[] cartasJugador = new String[4];
		Carta[] cartasYo = mesa[post].getMano();
		for (int i = 0; i < 4; i++){
			cartasJugador[i] = cartasYo[i].getPalo() + "" + cartasYo[i].getNumero() + ".jpg"; 
		}
			
		String activarBotones = "";	
		if (post == elQueHabla){
			activarBotones = "visible";
		}else {
			activarBotones = "hidden";
		}
		
		
		// PARA PINTAR EL MAZO
		
		m.addAttribute("mesaPantalla",mesaPantalla);
		m.addAttribute("manoPantalla",manoPantalla);
		m.addAttribute("cartasJugador",cartasJugador);
		m.addAttribute("activarBotones",activarBotones);
		m.addAttribute("mesa",mesa);
		
		m.addAttribute("loQueDice",loQueDice);
		
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionDarMus.html")
	public String accionDarMus(Jugador jugador){
		
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionNoHayMus.html")
	public String accionNoHayMus(Jugador jugador){
		
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionPaso.html")
	public String accionPaso(Jugador jugador){
		
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionQuiero.html")
	public String accionQuiero(Jugador jugador){
		
		
		return "mesaJugador";
	}
	@RequestMapping("/accionEnvido.html")
	public String accionEnvido(Jugador jugador){
		
		
		return "mesaJugador";
	}
	
	@RequestMapping("/accionXMas.html")
	public String accionXMas(Jugador jugador){
		
		
		return "mesaJugador";
	}
	

	@RequestMapping("/accionDescartar.html")
	public String accionDescartar(Jugador jugador, HttpServletRequest req, Model m){
		
		// Cuando damos a descartar primero hay que comprobar que hay por lo menos una seleccionada
		// sino hay seleccionada damos un mensaje de error y retornamos a la página
		
		//List<Carta> listaDeCartas = new ArrayList<Carta>();
		int contadorDescartes = 0;
		//Carta[] lista = new Carta[4]; 
		//	lista =	jugador.getMano();
		//System.out.println(jugador.getMano());
		
		//Carta[] listaCartas = jugador.getMano();
		
			//jugador.quitarCarta(mesa)
		String valor = req.getParameter("descarte0");
		if (! (valor==null)){
			contadorDescartes++;
			//System.out.println(elQueHablaInicial);
			//System.out.println(partidaInicial.getMesa());
			//Carta c = partidaInicial.getMesa()[elQueHablaInicial].getMano()[0];
			//jugador.quitarCarta(c);
			
			//System.out.println("descartado" + c);			
			//System.out.println("HOLAAAAAA"+ listaCartas[0]);
			
		}
		valor = req.getParameter("descarte1");
		if (! (valor==null)){
			contadorDescartes++;
			System.out.println("descartado" + valor);
			
		}
		valor = req.getParameter("descarte2");
		if (! (valor==null)){
			contadorDescartes++;
			System.out.println("descartado" + valor);
			
		}
		valor = req.getParameter("descarte3");
		if (! (valor==null)){
			contadorDescartes++;
			System.out.println("descartado" + valor);
			
		}
		
		if (contadorDescartes >1){
			// Se llama a descartar cartas de un jugado
			// FALTAAAAAA
			
		}else {
			m.addAttribute("mensajeError","Se debe descartar al menos de una carta");
			
		}
		
		
		System.out.println("----------------------------------");
		return "mesaJugador";
	}

	
	@RequestMapping("/accionOrdago.html")
	public String accionOrdago(Jugador jugador){
		
		
		
		return "mesaJugador";
	}
	
	public Partida TestInicializarPartida(){
		Partida p = new Partida();

		Jugador j0 = new Jugador("Pepito0");
		Jugador j1 = new Jugador("Juanito1");
		Jugador j2 = new Jugador("Juanito2");
		Jugador j3 = new Jugador("Juanito3");
		
		//Metemos cartar a jo
		
		j0.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
		j0.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j0.añadirCarta(new Carta(Palo.COPAS, 2, 2));
		j0.añadirCarta(new Carta(Palo.ESPADAS, 1, 1));
		
		
		j1.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
		j1.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j1.añadirCarta(new Carta(Palo.COPAS, 2, 2));
		j1.añadirCarta(new Carta(Palo.ESPADAS, 1, 1));
		
		j2.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
		j2.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j2.añadirCarta(new Carta(Palo.COPAS, 2, 2));
		j2.añadirCarta(new Carta(Palo.ESPADAS, 1, 1));
	
		j3.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
		j3.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j3.añadirCarta(new Carta(Palo.COPAS, 2, 2));
		j3.añadirCarta(new Carta(Palo.ESPADAS, 1, 1));
		p.setMano(3);
		p.sentarJugador(j0, 0);
		p.sentarJugador(j1, 1);
		p.sentarJugador(j2, 2);
		p.sentarJugador(j3, 3);
		
		return p;
	}

}
