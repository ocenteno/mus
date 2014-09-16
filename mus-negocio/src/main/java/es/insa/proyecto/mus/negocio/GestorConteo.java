package es.insa.proyecto.mus.negocio;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Pares;
import es.insa.proyecto.mus.contratos.IComprobadorParesJuego;
import es.insa.proyecto.mus.contratos.IGestorConteo;

/**
 * Este gestor se encargará de contar las 
 * piedras que otorga una jugada.
 * @author Cristina y José Antonio
 *
 */
public class GestorConteo implements IGestorConteo{
	
	private IComprobadorParesJuego comprobadorParesJuego;
	
	
	
	/**
	 * Controlamos los puntos obtenidos a pares
	 */
	@Override
	public int contarPiedrasPorPares(Jugador j) {
		
		Pares pares = comprobadorParesJuego.comprobarPares(j);
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
	public int contarPiedrasPorJuego(Jugador j) {
		
		
		Juego juego = comprobadorParesJuego.comprobarJuego(j);
		switch (juego) {
		case TREINTAYUNA:
			return 3;
			
		case JUEGO:
			return 2;
			
		default:
			return 0;		
		}
	}


	public void setComprobadorParesJuego(
			IComprobadorParesJuego comprobadorParesJuego) {
		this.comprobadorParesJuego = comprobadorParesJuego;
	}
	
	
	

}
