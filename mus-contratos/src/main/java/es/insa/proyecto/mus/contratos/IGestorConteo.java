package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.modelo.Lance;

/**
 * Esta Interfaces se encargar� de contar las 
 * piedras que otorga una jugada.
 * @author Jose Antonio y Cristina
 *
 */
public interface IGestorConteo {
	
	public int contarLasPiedrasPorPares(Jugador J);
	
	public int contarLasPiedrasPorJuego(Jugador J);
	
}
