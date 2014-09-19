package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.modelo.Lances;

/**
 * Interface que realiza la suma del tanteo parcial al bote general
 * de cada pareja.
 * @author Cristina y Javier
 *
 */
public interface IGestorTanteoParcial {
	
	/**
	 * Buscamos al jugador que ha ganado el lance
	 */
	public Jugador sacarPiedrasLance(Lances lance);

	public void sacarPiedras(int turnoSiguiente, int piedras);
	
}

