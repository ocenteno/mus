/**
 * 
 */
package es.insa.proyecto.mus.negocio;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Pares;
import es.insa.proyecto.mus.contratos.IComprobadorParesJuego;

/**
 * @author Hermino Acedo y Jose A. Torre
 * 
 */
public class ComprobadorParesJuego implements
		IComprobadorParesJuego {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.insa.proyecto.mus.contratos.IGestorComprobarSiTieneJuego#tieneJuego
	 * (es.insa.proyecto.dominio.cartas.Jugador)
	 */
	@Override
	public Juego tieneJuego(Jugador j) {
		Carta[] manoJugador = j.getMano();
		int totalJuego = manoJugador[0].getValor() + manoJugador[1].getValor()
				+ manoJugador[2].getValor() + manoJugador[3].getValor();

		if (totalJuego == 31) {
			return Juego.TREINTAYUNA;
		} else if (totalJuego > 31){
				return Juego.JUEGO;
			  }else {
				   return Juego.PUNTO;
			}
		}
	

	@Override
	public Pares quePares(Jugador j) {
		Carta[] manoJugador = j.getMano();
		int hayPares = 0;
		int i = 0;
		int k = 0;
		for(i=1; i<4; i++){
			for(k=0;k<i; k++){
				if (manoJugador[i].equals(manoJugador[k])){
					hayPares++;
				}
					
			}
			
		}


		if (hayPares == 0) {
			
			return Pares.NO;
		} else if (hayPares == 1) {
			
			return Pares.PAR;
		} else if (hayPares == 2 ) {
			
			return Pares.DUPLES;
		} 
		else if (hayPares == 3 ) {
			
			return Pares.MEDIAS;
		}else {
			
			return Pares.DUPLES;
		}
	}
}
