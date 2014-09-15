package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.FasesJuego;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.GestorConteo;
import es.insa.proyecto.mus.negocio.GestorFaseJuego;

public class TestGestorFaseJuego {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testPedirMus() {
		Jugador j1 = new Jugador("Jugador1");
		GestorFaseJuego miGestor = new GestorFaseJuego();
		boolean resultado = miGestor.pedirMus(j1);
		Assert.assertTrue("Permite mus", resultado);
		Jugador j2 = new Jugador("Jugador2");
		resultado = miGestor.pedirMus(j2);
		Assert.assertTrue("Permite mus", resultado);
		Jugador j3 = new Jugador("Jugador3");
		resultado = miGestor.pedirMus(j3);
		Assert.assertTrue("Permite mus", resultado);
		Jugador j4 = new Jugador("Jugador4");
		resultado = miGestor.pedirMus(j4);
		Assert.assertTrue("Permite mus", resultado);
		Jugador j5 = new Jugador("Jugador5");
		resultado = miGestor.pedirMus(j5);
		Assert.assertFalse("No permite mus", resultado);
	}

	@Test
	public void testCortaMus() {
		Jugador j1 = new Jugador("Jugador1");
		Jugador j2 = new Jugador("Jugador2");
		Jugador j3 = new Jugador("Jugador3");
		GestorFaseJuego miGestor = new GestorFaseJuego();
		miGestor.pedirMus(j1);
		boolean resultado = miGestor.cortarMus(j2);
		Assert.assertTrue("Permite cortar mus", resultado);
		resultado = miGestor.cortarMus(j3);
		Assert.assertFalse("No permite cortar mus", resultado);	
	}
	
	@Test
	public void testTurnoJuego() {
		GestorFaseJuego miGestor = new GestorFaseJuego();
		int resultado = miGestor.turnoJuego();
		Assert.assertEquals("Le toca jugar a la posicion: ",0, resultado);
		resultado = miGestor.turnoJuego();
		Assert.assertEquals("Le toca jugar a la posicion: ",1, resultado);
		resultado = miGestor.turnoJuego();
		Assert.assertEquals("Le toca jugar a la posicion: ",2, resultado);
		resultado = miGestor.turnoJuego();
		Assert.assertEquals("Le toca jugar a la posicion: ",3, resultado);
		resultado = miGestor.turnoJuego();
		System.out.println(resultado);
		Assert.assertEquals("Le toca jugar a la posicion: ",0, resultado);	
	}
	
	@Test
	public void testFasesJuego() {
		//PRUEBA PARA DESCARTE
		Jugador j1 = new Jugador("Jugador1");
		GestorFaseJuego miGestor = new GestorFaseJuego();
		miGestor.pedirMus(j1);
		Jugador j2 = new Jugador("Jugador2");
		miGestor.pedirMus(j2);
		Jugador j3 = new Jugador("Jugador3");
		miGestor.pedirMus(j3);
		Jugador j4 = new Jugador("Jugador4");
		miGestor.pedirMus(j4);
		FasesJuego resultado = miGestor.faseJuego();
		Assert.assertEquals("Fase :", FasesJuego.DESCARTE, resultado);
		
		//PRUEBA PARA MUS
		Jugador j5 = new Jugador("Jugador5");
		GestorFaseJuego miGestor2 = new GestorFaseJuego();
		miGestor2.pedirMus(j5);
		Jugador j6 = new Jugador("Jugador6");
		miGestor2.pedirMus(j6);
		FasesJuego resultado2 = miGestor2.faseJuego();
		Assert.assertEquals("Fase :", FasesJuego.MUS, resultado2);
		
		//PRUEBA PARA GRANDE
		Jugador j7 = new Jugador("Jugador7");
		GestorFaseJuego miGestor3 = new GestorFaseJuego();
		miGestor3.pedirMus(j7);
		Jugador j8 = new Jugador("Jugador8");
		miGestor3.pedirMus(j8);
		Jugador j9 = new Jugador("Jugador9");
		miGestor3.cortarMus(j9);
		FasesJuego resultado3 = miGestor3.faseJuego();
		Assert.assertEquals("Fase :", FasesJuego.GRANDE, resultado3);
	}
	
	@Test
	public void testDescarte() {
		
	}
}
