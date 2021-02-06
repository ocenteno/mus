package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * Interface que incluye la comprobación del ganador de un lance
 * 
 * @author Cristina y Javier
 *
 */
public interface IGanadorLance {
	
	/**
	 *  Este método permite saber que jugador, de un array de jugadores, ha ganado un lance
	 */
	public Jugador ganador(Jugador... jugadores);

}
