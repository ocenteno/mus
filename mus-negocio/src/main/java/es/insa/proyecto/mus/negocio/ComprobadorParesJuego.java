/**
 * 
 */
package es.insa.proyecto.mus.negocio;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Pares;
import es.insa.proyecto.mus.contratos.IComprobadorParesJuego;

/**
 * Comprueba si un jugador tiene pares y/o juego
 * 
 * @author Hermino Acedo y Jose A. Torre
 */
public class ComprobadorParesJuego implements IComprobadorParesJuego {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.insa.proyecto.mus.contratos.IGestorComprobarSiTieneJuego#tieneJuego
	 * (es.insa.proyecto.dominio.cartas.Jugador)
	 */
	@Override
	public Juego comprobarJuego(Jugador j) {
		Carta[] manoJugador = j.getMano();
		int totalJuego = manoJugador[0].getValor() + manoJugador[1].getValor()
				+ manoJugador[2].getValor() + manoJugador[3].getValor();

		if (totalJuego == 31) {
			return Juego.TREINTAYUNA;
		} else if (totalJuego > 31) {
			return Juego.JUEGO;
		} else {
			return Juego.PUNTO;
		}
	}

	@Override
	public Pares comprobarPares(Jugador j) {
		Carta[] manoJugador = j.getMano();
		int hayPares = 0;
		for (int i = 1; i < 4; i++) {
			for (int k = 0; k < i; k++) {
				// Si son la misma carta, al ordenar dará 0
				if (manoJugador[i].compareTo(manoJugador[k]) == 0) {
					hayPares++;
				}
			}
		}

		switch (hayPares) {
			case 0:
				return Pares.NO;
			case 1:
				return Pares.PAR;
			case 2:
				return Pares.DUPLES;
			case 3:
				return Pares.MEDIAS;
			default:
				return Pares.DUPLES;
		}
	}
	

	@Override
	public Carta[] obtenerEmparejadas(Jugador j) {
	//	Carta[] manoEmparejadas = new Carta[4];
	  	Set<Carta> manoEmparejadas = new LinkedHashSet<Carta>();
		
		Carta[] manoJugador = j.getMano();
		int hayPares = 0;
		for (int i = 1; i < 4; i++) {
			for (int k = 0; k < i; k++) {
				// Si son la misma carta, al ordenar dará 0
				if (manoJugador[i].compareTo(manoJugador[k]) == 0) {
						manoEmparejadas.add(manoJugador[k]);
						manoEmparejadas.add(manoJugador[i]);
				}
				hayPares++;
			}
		}
		

		return (Carta[]) manoEmparejadas.toArray(new Carta[0]);
	}

}
