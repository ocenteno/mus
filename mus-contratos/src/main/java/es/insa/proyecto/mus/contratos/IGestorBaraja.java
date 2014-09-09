package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * Interface que incluye todas las operaciones que se realizan con una baraja
 * 
 * @author Cristina y Javier
 *
 */
public interface IGestorBaraja {

	/**
	 *  Este método permite mezclar las cartas de forma aleatoria
	 */
	void barajar();
	
	/**
	 *  Este método permite cargar el mazo de reparto con la baraja completa
	 *  e inicializar el mazo de descartes
	 */
	void inicializarMazo();
	
	/**
	 * Este método permite repartir del mazo un número de cartas concreto al
	 * jugador
	 * @param numCartas
	 */
	void repartirCartas(int numCartas, Jugador j);
	
	/**
	 * Este método permite recoger unas cartas concretas de la mano de jugador
	 * y ponerlas en el mazo de descartes 
	 * @param numCartas
	 */
	Carta[] descartarCartas(Carta[] cartasADescartar, Jugador j);
	
	/**
	 * Este método permite recoger las cartas del mazo de descartes y ponerlas
	 * en el mazo de reparto (cambia uno por otro)
	 */
	void recogerDescartes();
	
	
	
	
}
