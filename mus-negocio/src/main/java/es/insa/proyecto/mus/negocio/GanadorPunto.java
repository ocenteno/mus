package es.insa.proyecto.mus.negocio;

import java.util.Arrays;
import java.util.Comparator;

import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGanadorLance;
import es.insa.proyecto.mus.contratos.IGestorLances;

public class GanadorPunto implements IGanadorLance, Comparator<Jugador> {
	private IGestorLances gestorLances;
	private ComprobadorParesJuego comprobadorJuego;
	
	/**
	 * Método que devuelve el ganador de una jugada a Punto teniendo en cuenta
	 * quien es la mano o el mas cercano a ella
	 * 
	 * @param jugadores
	 *            - Los jugadores que intervienen en el lance
	 * @return jugador - Jugador ganador del lance
	 */
	@Override
	public Jugador ganador(Jugador... jugadores) {
		// ORDENO LOS JUGADORES EN BASE AL COMPRADOR
		Arrays.sort(jugadores, this);
		// EL QUE GANA ES EL 0
		if(comprobadorJuego.comprobarJuego(jugadores[0]) != Juego.PUNTO)
			return null;
		return jugadores[0];
	}
/**
 * Rutina de comprobar Punto devuelve 1 si gana juagador 1 o
 * devuelve -1 si el ganador es el jugador 2
 * En caso de igualdad el ganador sera el jugador mas cercano a la mano
 */
	public int compare(Jugador j1, Jugador j2) {
		int orden = 0;
		int v1 =comprobadorJuego.obtenerPuntuacion(j1); 
		int v2 =comprobadorJuego.obtenerPuntuacion(j2);
		if (v1 == v2) {
			if (j1 == gestorLances.ordenJugadoresSegunMano(j1, j2)[0]) {
				orden = 1;
			} else {
				orden = -1;
			}
			return orden;
		}
		return -Integer.compare(v1, v2);
	}

	public void setGestorLances(IGestorLances gestorLances) {
		this.gestorLances = gestorLances;
	}

	public void setComprobadorJuego(ComprobadorParesJuego comprobadorJuego) {
		this.comprobadorJuego = comprobadorJuego;
	}

}
