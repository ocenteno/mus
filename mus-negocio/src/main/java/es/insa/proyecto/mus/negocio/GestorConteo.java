package es.insa.proyecto.mus.negocio;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGestorConteo;
import es.insa.proyecto.mus.contratos.QueJuego;
import es.insa.proyecto.mus.contratos.QuePares;

/**
 * Este gestor se encargará de contar las 
 * piedras que otorga una jugada.
 * @author Cristina y José Antonio
 *
 */
public class GestorConteo implements IGestorConteo{
	
	GestorComprobadorSiTieneJuegoYPares gcpj = new GestorComprobadorSiTieneJuegoYPares();
		
	/**
	 * Controlamos los puntos obtenidos a pares
	 */
	@Override
	public int contarLasPiedrasPorPares(Jugador j) {
		
		QuePares pares = gcpj.quePares(j);
		switch (pares) {
		case PAR:
			return 1;
			
		case MEDIAS:
			return 2;
			
		case DUPLES:
			return 3;

		default:
			return 0;
		}
	}

	/**
	 * Controlamos los puntos obtenidos a Juego/Punto
	 */
	@Override
	public int contarLasPiedrasPorJuego(Jugador j) {
		
		QueJuego juego = gcpj.tieneJuego(j);
		switch (juego) {
		case TREINTAYUNA:
			return 3;
			
		case JUEGO:
			return 2;
			
		case PUNTO:
			return 1;		
		}
		return 0;
	}

	

}
