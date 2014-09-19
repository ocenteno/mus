package es.insa.proyecto.mus.negocio;

import java.util.Arrays;
import java.util.Comparator;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGanadorLance;
import es.insa.proyecto.mus.modelo.Partida;

public class GanadorChica implements IGanadorLance, Comparator<Jugador>{
	
	private Partida partida;

	public GanadorChica() {
		
	}

	/**
	 * Método que devuelve el ganador de una jugada a Grande teniendo en cuenta quien es la
	 * mano o el mas cercano a ella
	 * @param jugadores - Los jugadores que intervienen en el lance
	 * @return jugador - Jugador ganador del lance
	 */
	public Jugador ganador(Jugador... jugadores) {
		Arrays.sort(jugadores, this);
		return jugadores[0];
	}
	
	/**
	 * Decide cual jugador de los dos que le entran tiene las cartas mas altas
	 * @param j1 - Primer jugador
	 * @param j2 - Segundo jugador
	 * @return 1 cuando gana el jugador j1 y -1 cuando gana el jugador j2
	 */
	public int compare(Jugador j1, Jugador j2) {
		j1.ordenarMano();
		j2.ordenarMano();
		// 0 indica que son iguales
		int resultado = 0;
		
		for (int i = 0; i < 4 && resultado == 0; i++) {
			resultado = j1.getMano()[i].compareTo(j2.getMano()[i]);			
		}
		// resultado == -1 indica que j1 tiene las cartas mas altas
		// resultado ==  1 indica que j2 tiene las cartas mas altas
		if (resultado == 0) {
			if (j1 == partida.jugadorMásCercaDeLaMano(j1, j2)){
				resultado = 1;
			}else {
				resultado = -1;
			}
		} 
		// devuelvo -1, 0 (no se puede dar en este caso), 1
		return resultado;
	}
	
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	

}
