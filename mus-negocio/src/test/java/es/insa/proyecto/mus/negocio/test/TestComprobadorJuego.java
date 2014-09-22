package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;
import es.insa.proyecto.mus.modelo.Partida;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.GanadorGrande;
import es.insa.proyecto.mus.negocio.GanadorJuego;

public class TestComprobadorJuego {

	private static GanadorJuego gj;
	private static Jugador j1Juego31;
	private static Jugador j2Punto20;
	private static Jugador j3Punto16;
	private static Jugador j4Juego32;
	private static Jugador j5Juego32;
	private static Jugador j6Juego40;

	@BeforeClass
	public static void inicializar() {
		j1Juego31 = new Jugador("Jugador1");
		j1Juego31.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j1Juego31.añadirCarta(new Carta(Palo.COPAS, 12, 10));
		j1Juego31.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1Juego31.añadirCarta(new Carta(Palo.OROS, 1, 1));
		j2Punto20 = new Jugador("Jugador2");
		j2Punto20.añadirCarta(new Carta(Palo.OROS, 1, 1));
		j2Punto20.añadirCarta(new Gocho(Palo.COPAS, 3, 3));
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
		j5Juego32 = new Jugador("Jugador5");
		j5Juego32.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j5Juego32.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		j5Juego32.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		j5Juego32.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		j6Juego40 = new Jugador("Jugador6");
		j6Juego40.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j6Juego40.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		j6Juego40.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		j6Juego40.añadirCarta(new Carta(Palo.BASTOS, 10, 10));

		gj = new GanadorJuego();
		gj.setComprobadorJuego(new ComprobadorParesJuego());
	}

	@Test
	public void testCompare31YPunto() {
		Partida p = new Partida();
		p.sentarJugador(j1Juego31, 0);
		p.sentarJugador(j2Punto20, 1);
		p.setMano(0);
		gj.setPartida(p);

		Jugador ganador = gj.ganador(j1Juego31, j2Punto20);
		
		assertEquals("Debe ganar jugador1", ganador, j1Juego31);
	}

	/**
	 * Se verifica si hay solo punto en ambos jugadores por lo que debe devolver
	 * null en lugar de un ganador
	 */
	@Test
	public void testGanadorSoloPunto() {
		Partida p = new Partida();
		p.sentarJugador(j2Punto20, 0);
		p.sentarJugador(j3Punto16, 1);
		p.setMano(0);
		gj.setPartida(p);

		Jugador ganador = gj.ganador(j2Punto20, j3Punto16);
		
		assertNull("Debe devolver null porque ninguno tiene juego", ganador);
	}

	/**
	 * Un de ellos tiene punto y ganara el que tiene juego
	 */
	@Test
	public void testGanadorPuntoJuego() {
		Partida p = new Partida();
		p.sentarJugador(j3Punto16, 0);
		p.sentarJugador(j4Juego32, 1);
		p.setMano(0);
		gj.setPartida(p);

		Jugador ganador = gj.ganador(j3Punto16, j4Juego32);
		
		assertEquals("Debe ganar jugador4", ganador, j4Juego32);
	}

	/**
	 * Ambos tiene juego con los mismos puntos por lo que gana el mas cercano a
	 * la mano
	 */
	@Test
	public void testGanadorJuegoJuegoMano() {
		Partida p = new Partida();
		p.sentarJugador(j4Juego32, 0);
		p.sentarJugador(j5Juego32, 1);
		p.setMano(0);
		gj.setPartida(p);

		Jugador ganador = gj.ganador(j4Juego32, j5Juego32);
		
		assertEquals("Debe ganar jugador4 por la mano", ganador, j4Juego32);
	}

	/**
	 * Uno con juego y otro con 31 ganando este ultimo
	 */
	@Test
	public void testGanadorJuego31() {
		Partida p = new Partida();
		p.sentarJugador(j4Juego32, 0);
		p.sentarJugador(j1Juego31, 1);
		p.setMano(0);
		gj.setPartida(p);

		Jugador ganador = gj.ganador(j4Juego32, j1Juego31);
		
		assertEquals("Debe ganar jugador1", ganador, j1Juego31);
	}

	/**
	 * Uno con juego de 32 y otro con 40 ganando el que tiene mas alto(32)
	 */
	@Test
	public void testGanador3240() {
		Partida p = new Partida();
		p.sentarJugador(j6Juego40, 0);
		p.sentarJugador(j4Juego32, 1);
		p.setMano(0);
		gj.setPartida(p);

		Jugador ganador = gj.ganador(j6Juego40, j4Juego32);
		
		assertEquals("Debe ganar jugador4", ganador, j4Juego32);
	}
}
