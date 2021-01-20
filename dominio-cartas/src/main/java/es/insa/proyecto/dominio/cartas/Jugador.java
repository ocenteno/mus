package es.insa.proyecto.dominio.cartas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Esta clase contiene los mÈtodos que interact˙an con el Jugador. Las
 * operaciones que se pueden realizar son las siguientes:
 * 
 * @author Cristina y Javier
 * 
 */
public class Jugador implements Comparable<Jugador> {

	// nombre del jugador a tratar
	private String nombre;
	// cartas que tiene el jugador
	private List<Carta> manoJugador;
	// Booleano para devolver un error en la operaciÛn
	private boolean errorMano = false;

	public Jugador() {
		this("");
	}

	// Constructor para crear un jugador e inicializar la mano del jugador
	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
		this.manoJugador = new ArrayList<Carta>();
	}

	/**
	 * MÈtodo para aÒadir una Carta a la mano del jugador controlando que
	 * no exista y no se tenga ya 4 cartas, en ambos casos devolver· erroMano a true.
	 * 
	 * @param cartaAAÒadir
	 *            --> la carta que se pretende aÒadir
	 * @return 
	 */
	public boolean añadirCarta(Carta cartaAAÒadir) {
		if (this.manoJugador.size() < 4) {
			this.manoJugador.add(cartaAAÒadir);
		}else {
			errorMano = true;
		}
		return errorMano;
	}

	/**
	 * MÈtodo para quitar una Carta de la mano del jugador controlando que
	 * ya exista, devolver· erroMano a true si no existe.
	 * 
	 * @param cartaAQuitar
	 *            --> la carta que se pretende quitar
	 * @return 
	 */
	public boolean quitarCarta(Carta cartaAQuitar) {
		if (this.manoJugador.contains(cartaAQuitar)) {
			this.manoJugador.remove(cartaAQuitar);
		}else {
			errorMano = true;
		}
		return errorMano;
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

	/**
	 * @param manoJugador the manoJugador to set
	 */
	public void setManoJugador(List<Carta> manoJugador) {
		this.manoJugador = manoJugador;
	}

	/**
	 * Ordenamos la mano del jugador.
	 */
	public void ordenarMano() {
		Collections.sort(this.manoJugador);
	}
	
	/**
	 * Comprueba si el jugador tiene un conjunto de cartas en la mano
	 * @param cartas Conjunto de cartas a comprobar
	 * @return true si contiene todas las cartas, false en caso contrario
	 */
	public boolean tieneEnMano(Carta... cartas){
		return this.manoJugador.containsAll(Arrays.asList(cartas));
	}

	@Override
	public int compareTo(Jugador otro) {
		return nombre.compareTo(otro.nombre);
	}
	
}
