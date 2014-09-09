package es.insa.proyecto.dominio.cartas;

import java.util.HashSet;
import java.util.Set;

/**
 * Esta clase contiene los métodos que interactúan con el Jugador. Las
 * operaciones que se pueden realizar son las siguientes:
 * 
 * - Añadir Carta a la mano del jugador. - Quitar una Carta de la mano del
 * jugador. - Consultar la mano del jugador.
 * 
 * @author Cristina y Javier
 * 
 */
public class Jugador {

	private String nombre;
	private Set<Carta> manoJugador;

	public Jugador() {
	}

	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
		this.manoJugador = new HashSet<Carta>();
	}

	/**
	 * Método para añadir una Carta a la mano del jugador.
	 * 
	 * @param cartaAAñadir
	 *            --> la carta que se pretende añadir
	 */
	public void añadirCarta(Carta cartaAAñadir) {
		this.manoJugador.add(cartaAAñadir);
	}

	/**
	 * Método para quitar una Carta de la mano del jugador.
	 * 
	 * @param cartaAQuitar
	 *            --> la carta que se pretende quitar
	 */
	public void quitarCarta(Carta cartaAQuitar) {
		this.manoJugador.remove(cartaAQuitar);
	}

	/**
	 * La mano del jugador la guardamos como un Set pero la devolvemos como un
	 * Array de Cartas
	 * 
	 * @return devuelve un Array de Carta
	 */
	public Carta[] getMano() {
		return this.manoJugador.toArray(new Carta[0]);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
