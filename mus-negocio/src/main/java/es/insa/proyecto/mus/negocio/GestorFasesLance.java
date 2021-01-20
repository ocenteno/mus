package es.insa.proyecto.mus.negocio;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import es.insa.proyecto.dominio.cartas.AccionesLance;
import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Pares;
import es.insa.proyecto.mus.contratos.IComprobadorParesJuego;
import es.insa.proyecto.mus.contratos.IGestorDeApuestas;
import es.insa.proyecto.mus.contratos.IGestorFaseApuestas;
import es.insa.proyecto.mus.contratos.IGestorTanteoParcial;
import es.insa.proyecto.mus.modelo.Lances;
import es.insa.proyecto.mus.modelo.Partida;

/**
 * Este gestor implementa la interface que se utiliza para permitir a cada
 * jugador realizar las diferentes acciones (pasar, envidar, etc.) de cada lance
 * de una mano. Además gestiona el flujo de los lances (grande, chica, etc.).
 * 
 * @author Cristina y José Antonio
 * 
 */

public class GestorFasesLance implements IGestorFaseApuestas {

	private List<AccionesLance> listaAcciones;
	private Lances faseActual;
	private boolean faseOrdago;
	private Set<Jugador> contadorDePaso;
	private IComprobadorParesJuego comprobadorParesJuego;
	private IGestorDeApuestas gestorApuestas;
	private Jugador[] jugadorPartida;
	private boolean[] verificador;
	private int noQuiero;

	private Partida partida;
	private int turno;
	private int mano;
	private IGestorTanteoParcial gestorTanteo;

	/**
	 * Constructor donde inicializamos la faseActual, el contador de paso y le
	 * pedimos a partida que nos de La Partida.
	 */
	public GestorFasesLance() {
		faseActual = Lances.GRANDE;
		contadorDePaso = new TreeSet<Jugador>();
		faseOrdago = false;
		listaAcciones = new LinkedList<AccionesLance>();
		cargarAccionesQueSePuedenRealizarCuandoNadieHaApostado();
	}

	@Override
	public boolean ejecutar(Jugador jugador, Lances fase, AccionesLance accion, int piedras) {
		if (faseActual == fase) {
			return dispararAccion(jugador, accion, piedras);
		}
		return false;
	}

	@Override
	public boolean ejecutar(Jugador jugador, Lances fase, AccionesLance accion) {
		if(accion == AccionesLance.APUESTA){
			return ejecutar(jugador, fase, accion, 0);
		}
		return true;
	}


	/**
	 * Este método gestiona las decisiones que un jugador concreto pueda tomar
	 * en el lance de Grande.
	 */
	@Override
	public boolean faseGrande(Jugador j, AccionesLance a, int p) {
		return ejecutar(j, Lances.GRANDE, a, p);
	}

	/**
	 * Este método gestiona las decisiones que un jugador concreto pueda tomar
	 * en el lance de Chica.
	 */
	@Override
	public boolean faseChica(Jugador j, AccionesLance a, int p) {
		return ejecutar(j, Lances.CHICA, a, p);
	}

	/**
	 * Este método gestiona las decisiones que un jugador concreto pueda tomar
	 * en el lance de Pares.
	 */
	@Override
	public boolean fasePares(Jugador j, AccionesLance a, int p) {
		return ejecutar(j, Lances.PARES, a, p);
	}

	/**
	 * Este método gestiona las decisiones que un jugador concreto pueda tomar
	 * en el lance de Juego.
	 */
	@Override
	public boolean faseJuego(Jugador j, AccionesLance a, int p) {
		return ejecutar(j, Lances.JUEGO, a, p);
	}

	/**
	 * Este método gestiona las decisiones que un jugador concreto pueda tomar
	 * en el lance de Punto.
	 */
	@Override
	public boolean fasePunto(Jugador j, AccionesLance a, int p) {
		return ejecutar(j, Lances.PUNTO, a, p);
	}

	/**
	 * Este método coordina determina que método ejecutar para cada acción.
	 * 
	 * @param jugador jugador que intenta ejecutar la acción
	 * @param accion Accion realizada por dicho jugador
	 * @param piedras piedras apostadas
	 * @return
	 */
	public boolean dispararAccion(Jugador j, AccionesLance a, int p) {

		// SI SE PUEDE DISPARAR LA ACCIÓN LA DISPARO
		if (chequearAccionSolicitada(a)) {
			// PERO LA DISPARO SEGÚN EL CASO
			switch (a) {
				case PASO:
					return paso(j);
				case ENVIDO:
					envidar();
					break;
				case APUESTA:
					apostar(p);
					break;
				case QUIERO:
					quiero();
					break;
				case NOQUIERO:
					noQuiero();
					break;
				case ORDAGO:
					ordago();
					break;
				default:
					return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Controla un lance en "paso" y cuál es el siguiente lance a jugar.
	 * 
	 * @param j
	 * @return
	 */
	private boolean paso(Jugador j) {
		// añado el jugador al contador de jugadores que han pasado
		if(!contadorDePaso.add(j)){
			// error pq ya habia pasado
			return false;
		}
		// SI PASAN LOS 4
		if (contadorDePaso.size() == 4) {
			// si es la última fase hay q iniciar un nuevo juego
			if (faseActual == Lances.JUEGO || faseActual == Lances.PUNTO) {
				mano = partida.cambiarMano();
			}
			// Limpiamos el contador y cambiamos de fase
			contadorDePaso.clear();
			faseSiguiente();
			turno = mano;
		}else{ 
			turnoSiguiente();
		}
		return true;
	}

	/**
	 * Controla un lance en "envido" y cuál es el siguiente lance a jugar.
	 * 
	 * @param j
	 * @return
	 */
	private void envidar() {
		gestorApuestas.envido(faseActual);
		cargarAccionesQueSePuedenRealizarCuandoHayApuesta();
		turnoSiguiente();
	}

	private void apostar(int p) {
		// Si la apuesta supera las 40 piedras se considera órdago 
		if(p + gestorApuestas.getApuestas(faseActual) >= 40){
			ordago();
		}else{
			gestorApuestas.apostar(p, faseActual);
			cargarAccionesQueSePuedenRealizarCuandoHayApuesta();
			turnoSiguiente();
		}
	}

	private void ordago() {
		gestorApuestas.ordago(faseActual);
		faseOrdago = true;
		cargarAccionesQueSePuedenRealizarAnteOrdago();
		turnoSiguiente();
	}

	/**
	 * Si no ha aceptado la apuesta (no quiero) se le dice al gestor de conteo
	 * que determine el bote. Se determina la siguiente fase.
	 * 
	 * @param
	 */
	private void noQuiero() {
		int piedras = gestorApuestas.noQuiero(faseActual);
		// Si soy el primero que no ha querido
		if(noQuiero==0){
			// pasa el turno a mi pareja
			turnoSiguiente();
			turnoSiguiente();
			// y yo ya he "no querido"
			noQuiero = 1;
		}else{
			// soy el compañero, y si yo no quiero se acabó
			faseSiguiente();
			cargarAccionesQueSePuedenRealizarCuandoNadieHaApostado();
			// reseteamos el noQuiero
			noQuiero = 0;
			// La pareja que apostó se lleva las piedras
			gestorTanteo.sacarPiedras(turnoSiguiente(), piedras);
			// y el turno pasa a la mano
			turno = mano;
		}
	}

	/**
	 * Si ha aceptado la apuesta (quiero) se le dice al gestor de conteo que
	 * determine el bote. Si con anterioridad se lanzo un ordago o la apuesta
	 * acumulada iguala las 40 piedras se finaliza la mano y se cuentan las
	 * piedras.
	 * 
	 * @param a
	 */
	private void quiero() {
		// cuando quiero se deja de contar el noQuiero
		noQuiero = 0;
		// el turno es el de la mano
		turno = mano;
		int bote = gestorApuestas.quiero(faseActual);
		if (faseOrdago || bote == 40) {
			faseActual = Lances.CONTEO;
			// devolvemos vacío las acciones posibles.
		} else {
			faseSiguiente();
			cargarAccionesQueSePuedenRealizarCuandoNadieHaApostado();
		}
	}

	/**
	 * Este método se encarga de avanzar el turno al jugador siguiente
	 * @return El índice de turno actual
	 */
	private int turnoSiguiente() {
		turno = (turno+1)%4;
		return turno;
	}
	
	/**
	 * Este método se encarga de determinar cual será la siguiente fase del
	 * juego.
	 */
	private void faseSiguiente() {
		switch (faseActual) {
			case GRANDE:
				faseActual = Lances.CHICA;
				break;
			// Recuperamos en el constructor la partida, y vemos si los jugadores
			// de dicha partida tienen pares o no para determinar si habrá lance
			// de pares.
			case CHICA:
				if (hayPares()) {
					faseActual = Lances.PARES;
				}else if (hayJuego()){
					faseActual = Lances.JUEGO;
				} else {
					faseActual = Lances.PUNTO;
				}
				break;
			case PARES:
				if (hayJuego()){
					faseActual = Lances.JUEGO;
				} else {
					faseActual = Lances.PUNTO;
				}
				break;
			case JUEGO:
			case PUNTO:
				faseActual = Lances.CONTEO;
				break;
			default:
				break;
		}
	}

	/**
	 * Cargamos el array de acciones realizables antes de que existan apuestas
	 */
	private void cargarAccionesQueSePuedenRealizarCuandoNadieHaApostado() {
		listaAcciones.clear();
		listaAcciones.add(AccionesLance.PASO);
		listaAcciones.add(AccionesLance.ENVIDO);
		listaAcciones.add(AccionesLance.APUESTA);
		listaAcciones.add(AccionesLance.ORDAGO);
	}

	/**
	 * Cargamos el array de acciones realizables cuando ya existe una apuesta
	 */
	private void cargarAccionesQueSePuedenRealizarCuandoHayApuesta() {
		listaAcciones.clear();
		listaAcciones.add(AccionesLance.QUIERO);
		listaAcciones.add(AccionesLance.NOQUIERO);
		listaAcciones.add(AccionesLance.ENVIDO);
		listaAcciones.add(AccionesLance.APUESTA);
		listaAcciones.add(AccionesLance.ORDAGO);
	}

	/**
	 * Cargamos el array de acciones realizables a partir de un ordago
	 */
	private void cargarAccionesQueSePuedenRealizarAnteOrdago() {
		listaAcciones.clear();
		listaAcciones.add(AccionesLance.QUIERO);
		listaAcciones.add(AccionesLance.NOQUIERO);
	}

	/**
	 * Comprobamos que jugadores tienen pares para saber si habrá lance de pares
	 * o no.
	 * 
	 * @return
	 */
	private boolean hayPares(){
		verificador = new boolean[4];
		for (int i=0; i<jugadorPartida.length;i++) {
			Jugador j = jugadorPartida[i];
			Pares pares = comprobadorParesJuego.comprobarPares(j);
			verificador[i] = (pares != Pares.NO);
		}
		return evaluarVerificador();
	}

	/**
	 * Comprobamos que jugadores tienen juego para saber si habrá lance de juego
	 * o no.
	 * 
	 * @return
	 */
	private boolean hayJuego() {
		verificador = new boolean[4];
		for (int i=0; i<jugadorPartida.length;i++) {
			Jugador j = jugadorPartida[i];
			Juego juego = comprobadorParesJuego.comprobarJuego(j);
			verificador[i] = (juego != Juego.PUNTO);
		}
		return evaluarVerificador();
	}

	/**
	 * Devuelve false si una de las 2 parejas ha evaluado a false
	 * y true en caso contrario 
	 * @return
	 */
	private boolean evaluarVerificador() {
		return ! ((!verificador[0] && !verificador[2]) || 
			     (!verificador[1] && !verificador[3]));
	}
	
	/**
	 * Comprobamos que la acción solicitada es posible. Para ello comprobamos
	 * que está dentro de las acciones permitidas.
	 * 
	 * @param a
	 *            acción solicitada
	 * @return existe
	 */
	public boolean chequearAccionSolicitada(AccionesLance a) {
		return listaAcciones.contains(a);
	}
	
	/************************************************************************************************/
	/*									GETTERS Y SETTERS											*/
	/************************************************************************************************/
	
	/**
	 * Devolvemos la fase en la que nos encontramos.
	 */
	@Override
	public Lances getFase() {
		return faseActual;
	}

	/**
	 * Devolvemos las posibles acciones que puede realizar el siguiente jugador,
	 * en función de la acción realizada por el jugador anterior.
	 */
	@Override
	public AccionesLance[] getAcciones() {
		return listaAcciones.toArray(new AccionesLance[0]);
	}

	/**
	 * Recuperamos el índice del jugador que actualmente tiene el turno.
	 */
	@Override
	public int getTurno() {
		return turno;
	}

	public void setParesJuegos(IComprobadorParesJuego comprobadorParesJuego) {
		this.comprobadorParesJuego = comprobadorParesJuego;
	}

	public void setPartida(Partida partidaActual) {
		this.partida = partidaActual;
		this.jugadorPartida = partida.getMesa();
		this.mano = partida.getMano();
		this.turno = mano;
	}

	public void setGestorTanteo(IGestorTanteoParcial gestorTanteo) {
		this.gestorTanteo = gestorTanteo;
	}

	public void setGestorApuestas(IGestorDeApuestas gestorApuestas) {
		this.gestorApuestas = gestorApuestas;
	}

}
