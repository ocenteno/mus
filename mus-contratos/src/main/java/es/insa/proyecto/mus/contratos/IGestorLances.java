package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * Interface que incluye todas las operaciones que se realizan con un lance
 * 
 * @author Cristina y Javier
 *
 */
public interface IGestorLances {

	/**
	 *  Este método permite saber que jugador, de un array de jugadores, ha ganado un lance
	 */
	Jugador ganadorLance(Jugador... jugadores);

	Jugador[] ordenJugadoresSegunMano(Jugador... jugadores);
	
	
}
