package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.FaseDescartes;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.modelo.Partida;
import es.insa.proyecto.mus.negocio.GestorFaseJuego;

public class TestGestorFaseJuego {
	
	private static Jugador j1;
	private static Jugador j2;
	private static Jugador j3;
	private static Jugador j4;
	private static Partida p;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p = new Partida();
		j1 = new Jugador("Jugador1");
		j1.añadirCarta(new Carta(Palo.BASTOS, 10, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		j2 = new Jugador("Jugador2");
		j2.añadirCarta(new Carta(Palo.ESPADAS, 10, 10));
		j2.añadirCarta(new Carta(Palo.ESPADAS, 11, 10));
		j2.añadirCarta(new Carta(Palo.ESPADAS, 12, 10));
		j2.añadirCarta(new Carta(Palo.ESPADAS, 7, 7));
		j3 = new Jugador("Jugador3");
		j3.añadirCarta(new Carta(Palo.OROS, 10, 10));
		j3.añadirCarta(new Carta(Palo.OROS, 11, 10));
		j3.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j3.añadirCarta(new Carta(Palo.OROS, 7, 7));
		j4 = new Jugador("Jugador4");
		j4.añadirCarta(new Carta(Palo.COPAS, 10, 10));
		j4.añadirCarta(new Carta(Palo.COPAS, 11, 10));
		j4.añadirCarta(new Carta(Palo.COPAS, 12, 10));
		j4.añadirCarta(new Carta(Palo.COPAS, 7, 7));
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		p.sentarJugador(j4, 3);
		p.empezarPartida();
		
	}

	@Test
	public void testPedirMus() {
		Jugador j1 = new Jugador("Jugador1");
		GestorFaseJuego miGestor = new GestorFaseJuego();
		boolean resultado = miGestor.pedirMus(j1);
		Jugador j2 = new Jugador("Jugador2");
		resultado = miGestor.pedirMus(j2);
		Jugador j3 = new Jugador("Jugador3");
		resultado = miGestor.pedirMus(j3);
		Jugador j4 = new Jugador("Jugador4");
		resultado = miGestor.pedirMus(j4);
		Jugador j5 = new Jugador("Jugador5");
		resultado = miGestor.pedirMus(j5);
		Assert.assertFalse("No permite mus", resultado);
	}

	@Test
	public void testPedirMusSiCortado() {
		Jugador j1 = new Jugador("Jugador1");
		GestorFaseJuego miGestor = new GestorFaseJuego();
		miGestor.pedirMus(j1);
		Jugador j2 = new Jugador("Jugador2");
		miGestor.cortarMus(j2);
		Jugador j3 = new Jugador("Jugador3");
		boolean resultado = miGestor.pedirMus(j3);
		Assert.assertFalse("Permite mus", resultado);
	}
	
	@Test
	public void testPedirMusDespuesDeCortar() {
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
	public void testCortarMus() {
		Jugador j1 = new Jugador("Jugador1");
		GestorFaseJuego miGestor = new GestorFaseJuego();
		boolean resultado = miGestor.cortarMus(j1);
		Assert.assertTrue("Permite cortar mus", resultado);
	}
	
	@Test
	public void testTurnoJuego() {
		GestorFaseJuego miGestor = new GestorFaseJuego();
		miGestor.setPartida(p);
		
		int mano = p.getMano();
		int resultado = miGestor.turnoJuego();
		Assert.assertEquals("La primera vez el turno tiene que ser igual a la mano ",mano, resultado);
		
		Jugador[] jugadores = p.getMesa();
		miGestor.pedirMus(jugadores[mano]);
	
		resultado = miGestor.turnoJuego();
		Assert.assertEquals("Le toca jugar a la posicion: ",(mano + 1)%4, resultado);
		
		resultado = miGestor.turnoJuego();
		Assert.assertEquals("Le toca jugar a la posicion: ",(mano + 1)%4, resultado);
		
		miGestor.pedirMus(jugadores[resultado]);
		resultado = miGestor.turnoJuego();
		miGestor.pedirMus(jugadores[resultado]);
		resultado = miGestor.turnoJuego();
		Assert.assertEquals("Le toca jugar a la posicion: ",(mano + 3)%4, resultado);
	}
	
	@Test
	public void testFaseDescarte() {
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
		FaseDescartes resultado = miGestor.faseJuego();
		Assert.assertEquals("Fase :", FaseDescartes.DESCARTE, resultado);
	}
	
	@Test
	public void testFaseMus() {
		//PRUEBA PARA MUS
		Jugador j5 = new Jugador("Jugador5");
		GestorFaseJuego miGestor2 = new GestorFaseJuego();
		miGestor2.pedirMus(j5);
		Jugador j6 = new Jugador("Jugador6");
		miGestor2.pedirMus(j6);
		FaseDescartes resultado2 = miGestor2.faseJuego();
		Assert.assertEquals("Fase :", FaseDescartes.MUS, resultado2);
	}
		
	@Test
	public void testFaseGrande() {
		//PRUEBA PARA GRANDE
		Jugador j7 = new Jugador("Jugador7");
		GestorFaseJuego miGestor3 = new GestorFaseJuego();
		miGestor3.pedirMus(j7);
		Jugador j8 = new Jugador("Jugador8");
		miGestor3.pedirMus(j8);
		Jugador j9 = new Jugador("Jugador9");
		miGestor3.cortarMus(j9);
		FaseDescartes resultado3 = miGestor3.faseJuego();
		Assert.assertEquals("Fase :", FaseDescartes.GRANDE, resultado3);
	}
	
	@Test
	public void testpedirDescarteConCartas() {
		//La mano incluye las cartas de las que se descarta
		GestorFaseJuego miGestor = new GestorFaseJuego();
		miGestor.pedirMus(j1);
		miGestor.pedirMus(j2);
		miGestor.pedirMus(j3);
		miGestor.pedirMus(j4);
		Carta carta1 = new Carta(Palo.BASTOS, 10, 10);
		Carta carta2 = new Carta(Palo.BASTOS, 7, 7);
		boolean resultado = miGestor.pedirDescarte(j1, carta1, carta2);
		Assert.assertTrue("Si Descarte", resultado);
	}
	
	@Test
	public void testpedirDescarteSinCartas() {
		//La mano NO incluye las cartas de las que se descarta
		GestorFaseJuego miGestor = new GestorFaseJuego();
		miGestor.pedirMus(j1);
		miGestor.pedirMus(j2);
		miGestor.pedirMus(j3);
		miGestor.pedirMus(j4);
		Carta carta1 = new Carta(Palo.OROS, 10, 10);
		Carta carta2 = new Carta(Palo.BASTOS, 7, 7);
		boolean resultado = miGestor.pedirDescarte(j1, carta1, carta2);
		Assert.assertFalse("No Descarte", resultado);
	}
	
	@Test
	public void testpedirDescarteYNoTodosMus() {
		//No se puede descartar si los 4 jugadores no se han dado mus
		GestorFaseJuego miGestor = new GestorFaseJuego();
		miGestor.pedirMus(j1);
		miGestor.pedirMus(j2);
		miGestor.pedirMus(j3);
		Carta carta1 = new Carta(Palo.BASTOS, 10, 10);
		Carta carta2 = new Carta(Palo.BASTOS, 7, 7);
		boolean resultado = miGestor.pedirDescarte(j1, carta1, carta2);
		Assert.assertFalse("No Descarte", resultado);
	}
	
	@Test
	public void testEjecutarDescarteSiNoTodos() {
		//Solicitar ejecución descartes sin que todos se hayan descartado
		GestorFaseJuego miGestor = new GestorFaseJuego();
		miGestor.pedirMus(j1);
		miGestor.pedirMus(j2);
		miGestor.pedirMus(j3);
		miGestor.pedirMus(j4);
		Carta carta1 = new Carta(Palo.BASTOS, 10, 10);
		Carta carta2 = new Carta(Palo.BASTOS, 7, 7);
		miGestor.pedirDescarte(j1, carta1, carta2);
		boolean resultado = miGestor.ejecutarDescartar();
		Assert.assertFalse("No Descarte", resultado);
	}
}
