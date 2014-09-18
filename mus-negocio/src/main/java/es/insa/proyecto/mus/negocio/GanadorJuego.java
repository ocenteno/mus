package es.insa.proyecto.mus.negocio;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGanadorLance;
import es.insa.proyecto.mus.contratos.IGestorLances;

public class GanadorJuego implements IGanadorLance, Comparator<Jugador> {
	private IGestorLances gestorLances;
	private ComprobadorParesJuego comprobadorJuego;
	
	/**
	 * Método que devuelve el ganador de una jugada a Juego teniendo en cuenta
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
		if(comprobadorJuego.comprobarJuego(jugadores[0]) == Juego.PUNTO)
			return null;
		return jugadores[0];
	}
/**
 * Rutina de comprobar juego devuelve 1 si gana juagador 1 o
 * devuelve -1 si el ganador es el jugador 2
 * En caso de igualdad el ganador sera el jugador mas cercano a la mano
 */
	public int compare(Jugador j1, Jugador j2) {
		int orden = 0;
		Juego juego1 = comprobadorJuego.comprobarJuego(j1);
		Juego juego2 = comprobadorJuego.comprobarJuego(j2);
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
		
		orden = juego1.compareTo(juego2);
		if (orden == 0 && juego1 != Juego.PUNTO) {
			if(v1==32) return -1;
			if(v2==32) return 1;
			return Integer.compare(v1, v2);
		}
		return orden;
	}
	
// ESTA SERIA UNA FORMA DE RESOLUCION PARA ORDENAR A TRAVES DE UN MAP
	
//	Map<Integer, Integer> valores;
//	
//	public int compare(Jugador j1, Jugador j2) {
//		int v1 =comprobadorJuego.obtenerPuntuacion(j1); 
//		int v2 =comprobadorJuego.obtenerPuntuacion(j2);
//		return valores.get(v1).compareTo(v2);
//	}
	


	public void setGestorLances(IGestorLances gestorLances) {
		this.gestorLances = gestorLances;
	}

	public void setComprobadorJuego(ComprobadorParesJuego comprobadorJuego) {
		this.comprobadorJuego = comprobadorJuego;
	}

}
