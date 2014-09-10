package es.insa.proyecto.mus.negocio;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGestorLances;

public class GestorLances implements IGestorLances{

	private Jugador ganador;

	public GestorLances() {
	}

	public Jugador ganadorLance(Jugador... jugadores){
		
		return ganador;
	}
	
	@Override
	public Jugador[] ordenJugadoresSegunMano(Jugador... jugadores) {
//		Jugador[] ordenJugadores = new Jugador[4];
//		jugadorGanador.
		return jugadores;
		
		
	}
}
