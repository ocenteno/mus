package es.insa.proyecto.mus.modelo;

import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * La clase Pareja está formada por dos jugadores y lleva el control de los puntos y juegos ganados. 
 * @author Eugenia Blanco y M.Angeles Pascual
 *
 */
public class Pareja {
	
	
	/**
	 * Uno de los jugadores de la pareja
	 */
	private Jugador jugador1;
	
	/**
	 * El otro jugador de la pareja
	 */
	private Jugador jugador2;
	
	/**
	 * Número de puntos de cada pareja en cada juego
	 */
	private int piedrasGanadas;
	
	/**
	 * Número de juegos ganados por la pareja
	 */
	private int juegosGanados;
	
	
	
	public Pareja(){
	}
	
	/**
	 * Constructor de Pareja, necesita dos jugadores
	 * @param jugador1
	 * @param jugador2
	 */
	public Pareja(Jugador jugador1, Jugador jugador2) {
		super();
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}
	
	public Jugador getJugador1() {
		return jugador1;
	}
	
	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}
	
	public Jugador getJugador2() {
		return jugador2;
	}
	
	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}
	
	public int getPiedrasGanadas() {
		return piedrasGanadas;
	}
	
	public void setPiedrasGanadas(int piedras) {
		this.piedrasGanadas = piedras;
	}
	
	public int getJuegosGanados() {
		return juegosGanados;
	}
	
	public void setJuegosGanados(int juegosGanados) {
		this.juegosGanados = juegosGanados;
	}

	// ZONA DE MÉTODOS
	
	/**
	 * Este método comprueba si un jugador pertenece a esta pareja
	 * @return true si el jugador pertenece a la pareja
	 * 			false en caso contrario
	 */
	public boolean comprobarPertenece(Jugador jugador) {
		if (this.jugador1 == jugador || this.jugador2 == jugador){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Este método va acumulando los puntos de la pareja en 
	 * cada juego.
	 * @param puntos
	 * @return el número de piedras de la pareja despues de sumarle 
	 * los nuevos puntos
	 */
	public int sumarPuntos(int puntos){
		this.piedrasGanadas += puntos;
		return piedrasGanadas;
	}
	
	
	/**
	 * Este método inicializa las piedras de la pareja
	 */
	public void nuevoJuego(){
		this.piedrasGanadas = 0;
	}
	 
	
	/**
	 * Este método añade 1 al contador de juegos ganados de la
	 * pareja
	 * @return el número de juegos ganados
	 */
	public int ganarJuego(){
		this.juegosGanados ++;
		return juegosGanados;
	}
	
	
	
	@Override
	public String toString() {
		return "Pareja [jugador1=" + jugador1 + ", jugador2=" + jugador2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jugador1 == null) ? 0 : jugador1.hashCode());
		result = prime * result
				+ ((jugador2 == null) ? 0 : jugador2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pareja other = (Pareja) obj;
		if (jugador1 == null) {
			if (other.jugador1 != null)
				return false;
		} else if (!jugador1.equals(other.jugador1))
			return false;
		if (jugador2 == null) {
			if (other.jugador2 != null)
				return false;
		} else if (!jugador2.equals(other.jugador2))
			return false;
		
		// Añadido para comparar el 1 con el 2 y el 2 con el 1
		if (jugador1.equals(other.jugador2) && 
					jugador2.equals(other.jugador1))
			return true;
		
		return true;
	}
	
	
	
	
}
