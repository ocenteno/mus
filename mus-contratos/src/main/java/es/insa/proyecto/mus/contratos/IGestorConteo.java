package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * Esta Interfaces se encargará de contar las 
 * piedras que otorga una jugada.
 * @author Jose Antonio y Cristina
 *
 */
public interface IGestorConteo {
	
	public int contarPiedrasPorPares(Jugador j);
	
	public int contarPiedrasPorJuego(Jugador j);
	
}
