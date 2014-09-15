package es.insa.proyecto.mus.contratos;

import java.util.Map;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * Interface que se encarga de realizar de las
 * operaciones del mazo de reparto y del mazo de
 * descartes. 
 * @author José Antonio y Cristina
 *
 */
public interface ICrupier {

	public void ejecutarDescarte(Jugador j, Carta... cartas);
	
	public void ejecutarReparto(Jugador j, int numCarta);
	
}
