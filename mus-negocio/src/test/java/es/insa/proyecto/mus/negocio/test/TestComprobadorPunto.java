package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.modelo.Partida;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.GanadorPunto;

public class TestComprobadorPunto {

	private static GanadorPunto gp;
	private static Jugador j1Punto20;
	private static Jugador j2Punto20;
	private static Jugador j3Punto16;
	private static Jugador j4Juego32;
	
	@BeforeClass
	public static void inicializar() {
		j1Punto20 = new Jugador("Jugador1");
		j1Punto20.añadirCarta(new Carta(Palo.OROS, 5, 5));
		j1Punto20.añadirCarta(new Carta(Palo.COPAS, 5, 5));
		j1Punto20.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j1Punto20.añadirCarta(new Carta(Palo.ESPADAS, 5, 5));
		j2Punto20 = new Jugador("Jugador2");
		j2Punto20.añadirCarta(new Carta(Palo.OROS, 1, 1));
		j2Punto20.añadirCarta(new Gocho(Palo.COPAS, 3, 10));
		j2Punto20.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j2Punto20.añadirCarta(new Carta(Palo.OROS, 4, 4));
		j3Punto16 = new Jugador("Jugador3");
		j3Punto16.añadirCarta(new Carta(Palo.OROS, 1, 1));
		j3Punto16.añadirCarta(new Carta(Palo.COPAS, 5, 5));
		j3Punto16.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j3Punto16.añadirCarta(new Carta(Palo.OROS, 5, 5));
		j4Juego32 = new Jugador("Jugador4");
		j4Juego32.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j4Juego32.añadirCarta(new Carta(Palo.COPAS, 12, 10));
		j4Juego32.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j4Juego32.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		gp = new GanadorPunto();
		gp.setComprobadorJuego(new ComprobadorParesJuego());

	}

	/**
	 * ambos jugadores tienen punto gana el jugador con la puntuacion más alta
	 * 
	 */
	@Test
	public void testGanadorPuntoMásAlto() {
		Partida p = new Partida();
		p.sentarJugador(j3Punto16, 0);
		p.sentarJugador(j2Punto20, 1);
		p.setMano(0);
		gp.setPartida(p);

		Jugador ganador = gp.ganador(j3Punto16, j2Punto20);
		
		assertEquals("Debe ganar jugador2", ganador, j2Punto20);
	}

	/**
	 * Un de ellos tiene juego, es un error verificar punto por eso se devuelve
	 * null
	 */
	@Test
	public void testGanadorPuntoJuego() {
		Partida p = new Partida();
		p.sentarJugador(j4Juego32, 0);
		p.sentarJugador(j3Punto16, 1);
		p.setMano(0);
		gp.setPartida(p);

		Jugador ganador = gp.ganador(j4Juego32, j3Punto16);
		
		assertNull("Debe devolver null porque uno tiene juego", ganador);
	}

	/**
	 * Ambos tiene punto con los mismos puntos por lo que gana el mas cercano a
	 * la mano
	 */
	@Test
	public void testGanadorPuntoPuntoMano() {
		Partida p = new Partida();
		p.sentarJugador(j1Punto20, 0);
		p.sentarJugador(j2Punto20, 1);
		p.setMano(0);
		gp.setPartida(p);

		Jugador ganador = gp.ganador(j1Punto20, j2Punto20);
		
		assertEquals("Debe ganar jugador1 por la mano", ganador, j1Punto20);
	}

}
