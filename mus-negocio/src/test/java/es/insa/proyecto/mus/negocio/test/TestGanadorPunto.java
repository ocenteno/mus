package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.GanadorGrande;
import es.insa.proyecto.mus.negocio.GanadorJuego;
import es.insa.proyecto.mus.negocio.GanadorPares;
import es.insa.proyecto.mus.negocio.GanadorPunto;
import es.insa.proyecto.mus.negocio.GestorLances;

public class TestGanadorPunto {

	private static GanadorPunto gp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gp = new GanadorPunto();
		gp.setComprobadorJuego(new ComprobadorParesJuego());
		gp.setGestorLances(new GestorLances());
		
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
		
		int resultado = gp.compare(j1, j2);
		// Tiene que ir delante el que tiene mayor jugada (como es el j1, debería devolver -1)
		assertTrue(resultado < 0);
	}
/**
 *  ambos jugadores tienen punto gana el jugador con la puntuacion más alta
 * 
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
		
		Jugador resultado = gp.ganador(j1, j2);
		
		assertEquals(j2, resultado);
	}
	/**
	 * Un de ellos tiene juego, es un error verificar punto por eso se devuelve null
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
		
		Jugador resultado = gp.ganador(j1, j2);
		
		assertEquals(null, resultado);
	}
	/**
	 * Ambos tiene punto con los mismos puntos por lo que gana el mas
	 * cercano a la mano 
	 */
	@Test
	public void testGanadorPuntoPuntoMano() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 4, 4));
		j1.añadirCarta(new Carta(Palo.COPAS, 1, 1));
		j1.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j1.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		
		j2.añadirCarta(new Carta(Palo.OROS, 4, 4));
		j2.añadirCarta(new Carta(Palo.COPAS, 1, 1));
		j2.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j2.añadirCarta(new Carta(Palo.OROS, 5, 5));
		
		
		Jugador resultado = gp.ganador(j2, j1);
		
		assertEquals(j2, resultado);
	}
	
}
