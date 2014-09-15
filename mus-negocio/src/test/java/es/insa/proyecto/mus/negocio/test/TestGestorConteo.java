package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;
import es.insa.proyecto.mus.contratos.IComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;
import es.insa.proyecto.mus.negocio.GestorConteo;


/**
 * Verificamos que las piedras otorgadas por jugada`
 * son las correctas.
 * @author Cristina y José Antonio
 *
 */
public class TestGestorConteo {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	/**
	 * Se comprueba que que se cuenta una piedra por Pares
	 */
	@Test
	public void testContarPares() {
		Jugador jugador1 = new Jugador("Jugador1");
		jugador1.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador1.añadirCarta(new Pito(Palo.OROS, 2, 1));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		// 2º ejecución
		GestorConteo miGestor = new GestorConteo();
		ComprobadorParesJuego comprobador = new ComprobadorParesJuego();
		miGestor.setComprobadorParesJuego(comprobador);
		int piedras = miGestor.contarPiedrasPorPares(jugador1);
		// 3º aserción
		assertEquals(" Son pares ", 1, piedras);
	}
	
	/**
	 * Se comprueba que que se cuenta dos piedras por Medias
	 */
	@Test
	public void testContarMedias() {	
		Jugador jugador2 = new Jugador("Jugador2");
		jugador2.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador2.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		jugador2.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador2.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		// 2º ejecución
		GestorConteo miGestor = new GestorConteo();
		ComprobadorParesJuego comprobador = new ComprobadorParesJuego();
		miGestor.setComprobadorParesJuego(comprobador);
		int piedras = miGestor.contarPiedrasPorPares(jugador2);
		// 3º aserción
		assertEquals(" Son medias ", 2, piedras);
	}
	
	/**
	 * Se comprueba que que se cuenta tres piedras por Duples
	 */
	@Test
	public void testContarDuples() {	
		Jugador jugador3 = new Jugador("Jugador3");
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 8, 8));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 8, 8));
		// 2º ejecución
		GestorConteo miGestor = new GestorConteo();
		ComprobadorParesJuego comprobador = new ComprobadorParesJuego();
		miGestor.setComprobadorParesJuego(comprobador);
		int piedras = miGestor.contarPiedrasPorPares(jugador3);
		// 3º aserción
		assertEquals(" Son duples ", 3, piedras);
		
		Jugador jugador4 = new Jugador("Jugador4");
		jugador4.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		jugador4.añadirCarta(new Pito(Palo.BASTOS, 2, 1));
		jugador4.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador4.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		// 2º ejecución
		piedras = miGestor.contarPiedrasPorPares(jugador4);
		// 3º aserción
		assertEquals(" Son duples ", 3, piedras);
	}

	/**
	 * Se comprueba que que se cuentan tres piedras por Juego de 31
	 */
	@Test
	public void testContarJuego31() {
		Jugador jugador1 = new Jugador("Jugador1");
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
		jugador1.añadirCarta(new Carta(Palo.OROS, 12, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		// 2º ejecución
		GestorConteo miGestor = new GestorConteo();
		miGestor.setComprobadorParesJuego(new ComprobadorParesJuego());
		int piedras = miGestor.contarPiedrasPorJuego(jugador1);
		// 3º aserción
		assertEquals(" Treintayuna ", 3, piedras);
	}
	
	/**
	 * Se comprueba que que se cuentan dos piedras por Juego sin ser 31
	 */
	@Test
	public void testContarJuego() {	
		Jugador jugador2 = new Jugador("Jugador2");
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		jugador2.añadirCarta(new Carta(Palo.OROS, 12, 10));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		// 2º ejecución
		GestorConteo miGestor = new GestorConteo();
		miGestor.setComprobadorParesJuego(new ComprobadorParesJuego());
		int piedras = miGestor.contarPiedrasPorJuego(jugador2);
		// 3º aserción
		assertEquals(" Juego ", 2, piedras);
	}
	/**
	 * Se comprueba que no se cuentan piedras sin tener Juego
	 */
	@Test
	public void testContarJuegoNo() {	
		Jugador jugador3 = new Jugador("Jugador3");
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		jugador3.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		// 2º ejecución
		GestorConteo miGestor = new GestorConteo();
		miGestor.setComprobadorParesJuego(new ComprobadorParesJuego());
		int piedras = miGestor.contarPiedrasPorJuego(jugador3);
		// 3º aserción
		assertEquals(" Sin Juego ", 0, piedras);
	}

}
