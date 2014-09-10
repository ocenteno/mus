package es.insa.proyecto.mus.contratos.test;

import org.junit.Assert;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.contratos.IGestorLances;
import es.insa.proyecto.mus.modelo.Grande;

public class TestGrande {

//	@Test
	public void testGanador() {
		Jugador jugador1 = new Jugador("Jugador1");
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 10, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		Jugador jugador2 = new Jugador("Jugador2");
		jugador2.añadirCarta(new Carta(Palo.OROS, 10, 10));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador2.añadirCarta(new Carta(Palo.COPAS, 12, 10));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		Jugador jugador3 = new Jugador("Jugador3");
		jugador3.añadirCarta(new Carta(Palo.COPAS, 7, 7));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 10, 10));
		jugador3.añadirCarta(new Carta(Palo.OROS, 11, 10));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		Jugador jugador4 = new Jugador("Jugador4");
		jugador4.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		jugador4.añadirCarta(new Carta(Palo.COPAS, 11, 10));
		jugador4.añadirCarta(new Carta(Palo.OROS, 10, 10));
		jugador4.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		
		Grande grande = new Grande();
		grande.setGestorLances(new IGestorLances() {
			@Override
			public Jugador[] ordenJugadoresSegunMano(Jugador... jugadores) {
				return jugadores;
			}
			@Override
			public Jugador ganadorLance(Jugador... jugadores) {
				return null;
			}
		});
		
		
		Jugador ganadorGrande = grande.ganador(jugador3, jugador2, jugador1, jugador4);
		Assert.assertEquals("Debe ganar el jugador2", jugador2, ganadorGrande);
		
		ganadorGrande = grande.ganador(jugador1, jugador2, jugador3, jugador4);
		Assert.assertEquals("Debe ganar el jugador1", jugador1, ganadorGrande);
		
		ganadorGrande = grande.ganador(jugador4, jugador2, jugador3, jugador1);
		Assert.assertEquals("Debe ganar el jugador2", jugador2, ganadorGrande);
		
		ganadorGrande = grande.ganador(jugador4, jugador3, jugador1, jugador2);
		Assert.assertEquals("Debe ganar el jugador1", jugador1, ganadorGrande);
		
	}

}
