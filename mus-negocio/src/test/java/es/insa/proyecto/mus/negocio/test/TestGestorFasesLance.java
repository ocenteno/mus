package es.insa.proyecto.mus.negocio.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.AccionesLance;
import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.modelo.Lances;
import es.insa.proyecto.mus.modelo.Partida;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.GestorDeApuestas;
import es.insa.proyecto.mus.negocio.GestorFasesLance;
import es.insa.proyecto.mus.negocio.GestorTanteoParcial;

/**
 * Pruebas de los métodos de la Interfaces IGestorFasesLances
 * @author Cristina y José Antonio
 *
 */
public class TestGestorFasesLance {
	
	private static Jugador j1;
	private static Jugador j2;
	private static Jugador j3;
	private static Jugador j4;
	private static GestorFasesLance miGestor;
	private static Partida p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		j1 = new Jugador("Jugador1");
		j2 = new Jugador("Jugador2");
		j3 = new Jugador("Jugador3");
		j4 = new Jugador("Jugador4");
		j1.añadirCarta(new Carta(Palo.BASTOS, 10, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		//
		j2.añadirCarta(new Carta(Palo.ESPADAS, 10, 10));
		j2.añadirCarta(new Carta(Palo.ESPADAS, 11, 10));
		j2.añadirCarta(new Carta(Palo.ESPADAS, 12, 10));
		j2.añadirCarta(new Carta(Palo.ESPADAS, 7, 7));
		//
		j3.añadirCarta(new Carta(Palo.OROS, 10, 10));
		j3.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j3.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j3.añadirCarta(new Carta(Palo.OROS, 7, 7));
		//
		j4.añadirCarta(new Carta(Palo.COPAS, 10, 10));
		j4.añadirCarta(new Carta(Palo.COPAS, 11, 10));
		j4.añadirCarta(new Carta(Palo.COPAS, 12, 10));
		j4.añadirCarta(new Carta(Palo.COPAS, 7, 7));
	}

	@Before
	public void inicializar(){
		p = new Partida();
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		p.sentarJugador(j4, 3);
		p.empezarPartida();
		miGestor = new GestorFasesLance();
		miGestor.setPartida(p);
		miGestor.setParesJuegos(new ComprobadorParesJuego());
		GestorTanteoParcial gtp = new GestorTanteoParcial();
		gtp.setPartida(p);
		miGestor.setGestorTanteo(gtp);
		miGestor.setGestorApuestas(new GestorDeApuestas());
	}
	
	/**
	 * Recupera a quién le toca jugar a partir de una partida
	 * iniciada.
	 */
	@Test
	public void testTurnoJuego() {
		
	}

	/**
	 * Devuelve el lance de inicio de partida que va a ser GRANDE
	 */
	@Test
	public void testGetFaseInicio() {
		Lances lance = miGestor.getFase();
		Assert.assertEquals("Toca hablar de ", Lances.GRANDE, lance);
	}
	
	/**
	 * Devuelve las acciones que puede realizar el jugador
	 */
	@Test
	public void testGetAccionesInicio() {
		AccionesLance[] acciones  = miGestor.getAcciones();
		Assert.assertNotEquals("Acciones no puede estar vacío. ", 0, acciones.length);
	}
	
	/**
	 * Testeamos que al pasar los cuatro a grande, devuelve chica.
	 */
	@Test
	public void testFaseGrande() {
		miGestor.faseGrande(j1, AccionesLance.PASO, 0);
		miGestor.faseGrande(j2, AccionesLance.PASO, 0);
		miGestor.faseGrande(j3, AccionesLance.PASO, 0);
		miGestor.faseGrande(j4, AccionesLance.PASO, 0);
		Lances lance = miGestor.getFase();
		Assert.assertEquals("El siguiente lance es :",Lances.CHICA, lance);
	}
	
	@Test
	public void testFasePares() {
		// Cambio de lance: De Grande a Chica		
		miGestor.faseGrande(j1, AccionesLance.PASO, 0);
		miGestor.faseGrande(j2, AccionesLance.PASO, 0);
		miGestor.faseGrande(j3, AccionesLance.PASO, 0);
		miGestor.faseGrande(j4, AccionesLance.PASO, 0);
		// Cambio de lance: De Chica a Pares
		miGestor.faseChica(j1, AccionesLance.PASO, 0);
		miGestor.faseChica(j2, AccionesLance.PASO, 0);
		miGestor.faseChica(j3, AccionesLance.PASO, 0);
		miGestor.faseChica(j4, AccionesLance.PASO, 0);
		Lances lance = miGestor.getFase();
		Assert.assertEquals("El siguiente lance es :",
				Lances.JUEGO, lance);
	}
	
	@Test
	public void testEnvidoNoQuiero() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.NOQUIERO, 0);
		turno = miGestor.getTurno();
		Assert.assertEquals("El turno es del jugador :",
				(mano+3)%4, turno);
	}
	
	@Test
	public void testEnvidoQuiero() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.QUIERO, 0);
		Lances lance = miGestor.getFase();
		Assert.assertEquals("El siguiente lance es :",
				Lances.CHICA, lance);
	}
	
	@Test
	public void testEnvidoNoQuieroNoQuiero() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.NOQUIERO, 0);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.NOQUIERO, 0);
		Lances lance = miGestor.getFase();
		Assert.assertEquals("El siguiente lance es :",
				Lances.CHICA, lance);
	}
	
	@Test
	public void testEnvidoNoQuieroQuiero() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.NOQUIERO, 0);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.QUIERO, 0);
		Lances lance = miGestor.getFase();
		Assert.assertEquals("El siguiente lance es :",
				Lances.CHICA, lance);
	}
	
	@Test
	public void testPasoPasoPasoEnvido() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.PASO, 0);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.PASO, 0);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.PASO, 0);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		turno = miGestor.getTurno();
		Assert.assertEquals("El turno es del jugador :",
				mano, turno);
	}
	
	@Test
	public void testEnvidoNoQuieroSubo() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.NOQUIERO, 0);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.APUESTA, 2);
		turno = miGestor.getTurno();
		Assert.assertEquals("El turno es del jugador :",
				mano, turno);
	}
	
	@Test
	public void testPasoEnvidoSuboNoQuiero() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.PASO, 0);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.APUESTA, 5);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.NOQUIERO, 0);
		turno = miGestor.getTurno();
		Assert.assertEquals("El turno es del jugador :",
				((mano+1)%4), turno);
	}
	
	@Test
	public void testTurnoEnvidoSuboQuiero() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.APUESTA, 5);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.QUIERO, 0);
		turno = miGestor.getTurno();
		Assert.assertEquals("El turno es del jugador :",
				mano, turno);
	}
	
	@Test
	public void testFaseEnvidoSuboQuiero() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		int mano = turno;
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.APUESTA, 5);
		turno = miGestor.getTurno();
		miGestor.faseGrande(jugadores[turno], AccionesLance.QUIERO, 0);
		Lances lance = miGestor.getFase();
		Assert.assertEquals("El siguiente lance es :",
				Lances.CHICA, lance);
	}
	
	@Test
	public void testMismoPasoEnvido() {
		int turno = miGestor.getTurno();
		Jugador[] jugadores = p.getMesa();
		miGestor.faseGrande(jugadores[turno], AccionesLance.ENVIDO, 2);
		boolean resultado = miGestor.faseGrande(jugadores[turno], AccionesLance.PASO, 0);
		Assert.assertEquals("No se permite PASO al jugador que ya envidó",false, resultado);
	
	}
}
