/**
 * 
 */
package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Pares;


/**
 * @author Herminio Acedo y Jose A. Torre
 *
 */
public interface IComprobadorParesJuego {

	public Juego tieneJuego(Jugador j);
	
	public Pares quePares(Jugador j);
	
}	