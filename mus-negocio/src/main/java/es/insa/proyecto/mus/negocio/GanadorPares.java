package es.insa.proyecto.mus.negocio;

import java.util.Arrays;
import java.util.Comparator;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Pares;
import es.insa.proyecto.mus.contratos.IGanadorLance;

public class GanadorPares implements IGanadorLance, Comparator<Jugador>{
	private ComprobadorParesJuego comprobadorPares;
	private GanadorGrande comparadorGrande;

	public GanadorPares() {

	}

	/**
	 * Método que devuelve el ganador de una jugada a Grande teniendo en cuenta
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
		return jugadores[0];
	}

	public void setComparadorGrande(GanadorGrande comparadorGrande) {
		this.comparadorGrande = comparadorGrande;
	}

	public void setComprobadorPares(ComprobadorParesJuego comprobadorPares) {
		this.comprobadorPares = comprobadorPares;
	}

	@Override
	public int compare(Jugador j1, Jugador j2) {
		// CASOS: COMPARAMOS J1,J2	(-1 es que J1 es más pequeño, 1 es que J2 es más pequeño)
		Pares parJ1 = comprobadorPares.comprobarPares(j1);
		Pares parJ2 = comprobadorPares.comprobarPares(j2);
		
		// si tiene distinto par, se ordena ello solo
		int orden = -parJ1.compareTo(parJ2);
		
		// SI LOS COMPARO Y SON IGUALES, GANA LA CARTA MÁS ALTA O LA MANO
		// eso lo sabe hacer el comparador de grande
		if(orden == 0){
			orden = compararCartaMásAlta(j1, j2);
		}
		return orden;
		
	}

	private int compararCartaMásAlta(Jugador j1, Jugador j2) {
		// ESO ES COMPARAR POR GRANDE PERO SÓLO LAS CARTAS EMPAREJADAS
		Carta[] emparejadasJ1 = comprobadorPares.obtenerEmparejadas(j1);
		Carta[] emparejadasJ2 = comprobadorPares.obtenerEmparejadas(j2);
		// Creo 2 jugadores temporales para darles las cartas ordenadas
		Jugador tmpJ1 = new Jugador();
		Jugador tmpJ2 = new Jugador();
		for (int i = 0; i < emparejadasJ1.length; i++) {
			tmpJ1.añadirCarta(emparejadasJ1[i]);
			tmpJ2.añadirCarta(emparejadasJ2[i]);
		}
		// Ordenamos los jugadores temporales
		return comparadorGrande.compare(tmpJ1, tmpJ2);
	}

}
