package es.insa.proyecto.mus.modelo;

import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * Interface que incluye la comprobación del ganador de un lance
 * 
 * @author Cristina y Javier
 *
 */
public class Lance {

	Jugador jugadorGanador;
	
	/**
	 *  Este método permite saber que jugador, de un array de jugadores, ha ganado un lance
	 */
	public Jugador ganador(Jugador... jugadores){
		return jugadorGanador;
	}

	/**
	 * @return the jugadorGanador
	 */
	public Jugador getJugadorGanador() {
		return jugadorGanador;
	}
	
}
