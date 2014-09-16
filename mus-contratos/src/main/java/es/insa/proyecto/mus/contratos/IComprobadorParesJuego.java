/**
 * 
 */
package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Pares;


/**
 * @author Herminio Acedo y Jose A. Torre
 *
 */
public interface IComprobadorParesJuego {

	/**
	 * 
	 * @param j
	 * @return
	 */
	public Juego comprobarJuego(Jugador j);
	
	/**
	 * 
	 * @param j
	 * @return
	 */
	public Pares comprobarPares(Jugador j);

	public Carta[] obtenerEmparejadas(Jugador j);
	
}	