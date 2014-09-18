package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.GanadorJuego;
import es.insa.proyecto.mus.negocio.GestorLances;

public class TestGanadorJuego {

	private static GanadorJuego gj;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gj = new GanadorJuego();
		gj.setComprobadorJuego(new ComprobadorParesJuego());
		gj.setGestorLances(new GestorLances());
		
	//	gg.setGestorLances(new GestorLances());
	}
/**
 * comprueba el funcionamiento del metodo compare
 */
	@Test
	public void testCompare31YPunto() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j1.añadirCarta(new Carta(Palo.COPAS, 12, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1.añadirCarta(new Carta(Palo.OROS, 1, 1));
		
		j2.añadirCarta(new Carta(Palo.OROS, 1, 1));
		j2.añadirCarta(new Carta(Palo.COPAS, 3, 3));
		j2.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j2.añadirCarta(new Carta(Palo.OROS, 4, 4));
		
		int resultado = gj.compare(j1, j2);
		// Tiene que ir delante el que tiene mayor jugada (como es el j1, debería devolver -1)
		assertTrue(resultado < 0);
	}
/**
 * Se verifica si hay solo punto en ambos jugadores
 * por lo que debe devolver null en lugar de un ganador	
 */
	@Test
	public void testGanadorSoloPunto() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 4, 4));
		j1.añadirCarta(new Carta(Palo.COPAS, 1, 1));
		j1.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j1.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		j2.añadirCarta(new Carta(Palo.OROS, 1, 1));
		j2.añadirCarta(new Carta(Palo.COPAS, 5, 5));
		j2.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j2.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		Jugador resultado = gj.ganador(j1, j2);
		
		assertEquals(null, resultado);
	}
	/**
	 * Un de ellos tiene punto y ganara el que tiene juego
	 */
	@Test
	public void testGanadorPuntoJuego() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 4, 4));
		j1.añadirCarta(new Carta(Palo.COPAS, 1, 1));
		j1.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j1.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		j2.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j2.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j2.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j2.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		Jugador resultado = gj.ganador(j1, j2);
		
		assertEquals(j2, resultado);
	}
	/**
	 * Ambos tiene juego con los mismos puntos por lo que gana el mas
	 * cercano a la mano 
	 */
	@Test
	public void testGanadorJuegoJuegoMano() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j1.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j1.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		j2.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j2.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j2.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j2.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		Jugador resultado = gj.ganador(j2, j1);
		
		assertEquals(j2, resultado);
	}
	/**
	 * Uno con juego y otro con 31 ganando este ultimo
	 */
	@Test
	public void testGanadorJuego31() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j1.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1.añadirCarta(new Carta(Palo.OROS, 1, 1));
		
		j2.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j2.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j2.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j2.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		Jugador resultado = gj.ganador(j2, j1);
		
		assertEquals(j1, resultado);
	}
	/**
	 * Los dos con 31 ganando el mas cercano a la mano
	 */
	@Test
	public void testGanador3131Mano() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j1.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1.añadirCarta(new Carta(Palo.OROS, 1, 1));
		
		j2.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j2.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j2.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j2.añadirCarta(new Carta(Palo.OROS, 1, 1));
		
		Jugador resultado = gj.ganador(j2, j1);
		
		assertEquals(j2, resultado);
	}
	/**
	 * Uno con juego de 32  y otro con 40 ganando el que tiene mas alto(32)
	 */
	@Test
	public void testGanador3240() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j1.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1.añadirCarta(new Carta(Palo.OROS, 2, 2));
		
		j2.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j2.añadirCarta(new Carta(Palo.COPAS,12, 10));
		j2.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		j2.añadirCarta(new Carta(Palo.OROS, 10, 10));
		
		Jugador resultado = gj.ganador(j2, j1);
		
		assertEquals(j1, resultado);
	}
}
