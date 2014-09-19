package es.insa.proyecto.mus.negocio;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGestorDeApuestas;
import es.insa.proyecto.mus.contratos.IGestorTanteoParcial;
import es.insa.proyecto.mus.modelo.Lances;
import es.insa.proyecto.mus.modelo.Pareja;
import es.insa.proyecto.mus.modelo.Partida;

public class GestorTanteoParcial implements IGestorTanteoParcial{
	
	private GanadorGrande ganadorGrande;
	private GanadorChica ganadorChica;
	private GanadorPares ganadorPares;
	private GanadorJuego ganadorJuego;
	private GanadorPunto ganadorPunto;
	private Pareja pareja;
	private Partida partida;
	private IGestorDeApuestas gestorApuestas;
	private Jugador jugador;

	public GestorTanteoParcial() {
	}

	@Override
	public Jugador sacarPiedrasLance(Lances lance, Jugador... jugadores) {
		switch (lance) {
		case GRANDE:
			jugador = ganadorGrande.ganador(jugadores);
			break;
		case CHICA:
			jugador = ganadorChica.ganador(jugadores);
			break;
		case PARES:
			jugador = ganadorPares.ganador(jugadores);
			break;
		case JUEGO:
			jugador = ganadorJuego.ganador(jugadores);
			break;
		case PUNTO:
			jugador = ganadorPunto.ganador(jugadores);
			break;
		default:
			break;
	}
		int boteLance = gestorApuestas.getApuestas(lance);
		if (partida.getPareja1().comprobarPertenece(jugador)){
			partida.sumarPuntosPareja1(boteLance);
		}else{
			partida.sumarPuntosPareja2(boteLance);
		}
		
		return jugador;
		
	}
	
}
