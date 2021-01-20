/**
 * 
 */
package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;
import es.insa.proyecto.dominio.cartas.Juego;
import es.insa.proyecto.dominio.cartas.Pares;
import es.insa.proyecto.mus.negocio.ComprobadorParesJuego;

/**
 * @author insa01 
 * 
 */
public class TestGestorComprobadorJuegoPares {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Se comprueba que tiene juego de 31 puntos y que tiene juego pero no de 31
	 * 
	 */
	@Test
	public void testTieneJuego() {
		Jugador jugador1 = new Jugador("Jugador1");
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Juego juego = miGestor.comprobarJuego(jugador1);
		// 3º aserción
		assertEquals(" Tiene Juego de 31 puntos ", Juego.TREINTAYUNA, juego);

		Jugador jugador3 = new Jugador("Jugador3");
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador3.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		// 2º ejecución
		// 3º aserción
		juego = miGestor.comprobarJuego(jugador3);
		// 3º aserción
		assertEquals(" Juego ",Juego.JUEGO, juego);
		
	}
	
	/**
	 * Se comprueba que no tiene juego 
	 * 
	 */
	@Test
	public void testNoTieneJuego() {

		Jugador jugador2 = new Jugador("Jugador2");
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Juego juego = miGestor.comprobarJuego(jugador2);
		// 3º aserción
		assertEquals(" No tiene Juego, es Punto ",Juego.PUNTO, juego);
		
	}
	
	
	/**
	 * Se comprueba que tiene Duples de Gochos-Pitos
	 */
	@Test
	public void testDuplesGochoyPito() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 2, 2));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Duples", Pares.DUPLES, respuesta);

		
	}
	
	/**
	 * Comprueba que tiene Duples dos parejas de distinto numero 
	 */
	@Test
	public void test2Pares() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		jugador.añadirCarta(new Carta(Palo.OROS, 5, 5));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.ESPADAS, 11, 10));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Duples", Pares.DUPLES, respuesta);

		
	}
	
	/**
	 * Se comprueba que tiene Duples de dos parejas iguales
	 */
	@Test
	public void testDuples() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.ESPADAS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.COPAS, 4, 4));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Duples", Pares.DUPLES, respuesta);

		
	}
	
	/**
	 * Se comprueba que tiene Duples de Gochos
	 */
	@Test
	public void testDuplesGocho() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Gocho(Palo.COPAS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Esperamos que lleguen DUPLES ", Pares.DUPLES, respuesta);

		
	}

	/**
	 * Se comprueba que tiene Pares de carta normal
	 */
	@Test
	public void test1Pareja() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Esperamos reconozca un PAR ", Pares.PAR, respuesta);

	}

	/**
	 * Se comprueba que tiene Pares de Pitos
	 */
	@Test
	public void test1ParejaPito() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.añadirCarta(new Pito(Palo.OROS, 2, 1));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Esperamos reconozca un PAR ", Pares.PAR, respuesta);

	}
	
	/**
	 * Se comprueba que tiene Pares de Gochos
	 */
	@Test
	public void test1ParejaGocho() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Esperamos reconozca un PAR ", Pares.PAR, respuesta);

	}
	
	/**
	 * Se comprueba que no tiene Pares 
	 */
	@Test
	public void testSinParejas() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 2, 2));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción

		assertEquals("Esperamos sin PAR", Pares.NO, respuesta);
	}

	/**
	 * Se comprueba que tiene Medias de Cartas 
	 */
	@Test
	public void testMedias() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.ESPADAS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Debe devolver MEDIAS", Pares.MEDIAS, respuesta);

	}

	/**
	 * Se comprueba que tiene Medias de Gochos 
	 */
	@Test
	public void testMediasGochos() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Gocho(Palo.ESPADAS, 12, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Debe devolver MEDIAS", Pares.MEDIAS, respuesta);

	}

	/**
	 * Se comprueba que tiene Medias de Pitos 
	 */
	@Test
	public void testMediasPitos() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.añadirCarta(new Pito(Palo.OROS, 1, 1));
		jugador.añadirCarta(new Pito(Palo.ESPADAS, 2, 1));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Pares respuesta = miGestor.comprobarPares(jugador);
		// 3º aserción
		Assert.assertEquals("Debe devolver MEDIAS", Pares.MEDIAS, respuesta);

	}
	
	/**
	 * Se comprueba que devuelve las carta que forman una pareja de la mano del jugador
	 */
	@Test
	public void testObtenerEmparejadas() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.COPAS, 2, 2));
		jugador.añadirCarta(new Carta(Palo.ESPADAS, 3, 10));
		// 2º ejecución
		ComprobadorParesJuego miGestor = new ComprobadorParesJuego();
		Carta[] respuesta = miGestor.obtenerEmparejadas(jugador);
		// 3º aserción
		Assert.assertEquals("Debe devolver pareja de 2", 2, respuesta.length);
	}
}


