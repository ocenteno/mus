package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.GanadorGrande;
import es.insa.proyecto.mus.negocio.GanadorPares;
import es.insa.proyecto.mus.negocio.GestorLances;

public class TestGanadorPares {

	private static GanadorPares gp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gp = new GanadorPares();
		gp.setComprobadorPares(new ComprobadorParesJuego());
		GanadorGrande gg = new GanadorGrande();
		gg.setGestorLances(new GestorLances());
		gp.setComparadorGrande(gg);
	}

	@Test
	public void testCompare() {
		Jugador j1=new Jugador("A");
		Jugador j2=new Jugador("B");
		
		j1.añadirCarta(new Carta(Palo.OROS, 6, 6));
		j1.añadirCarta(new Carta(Palo.COPAS, 6, 6));
		j1.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j1.añadirCarta(new Carta(Palo.OROS, 1, 1));
		
		j2.añadirCarta(new Carta(Palo.OROS, 1, 1));
		j2.añadirCarta(new Carta(Palo.COPAS, 3, 3));
		j2.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j2.añadirCarta(new Carta(Palo.OROS, 4, 4));
		
		int resultado = gp.compare(j1, j2);
		// Tiene que ir delante el que tiene mayor jugada (como es el j1, debería devolver -1)
		assertTrue(resultado < 0);
	}
	
	@Test
	public void testGanador() {
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

}
