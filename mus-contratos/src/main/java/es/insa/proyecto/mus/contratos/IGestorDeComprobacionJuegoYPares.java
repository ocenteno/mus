/**
 * 
 */
package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Jugador;


/**
 * @author Herminio Acedo y Jose A. Torre
 *
 */
public interface IGestorDeComprobacionJuegoYPares {

	public QueJuego tieneJuego(Jugador j);
	
	public QuePares quePares(Jugador j);
	
}	