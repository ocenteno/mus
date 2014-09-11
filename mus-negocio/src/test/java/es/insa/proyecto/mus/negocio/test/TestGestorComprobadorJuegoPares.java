/**
 * 
 */
package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;
import es.insa.proyecto.mus.contratos.QueJuego;
import es.insa.proyecto.mus.contratos.QuePares;
import es.insa.proyecto.mus.negocio.GestorComprobadorSiTieneJuegoYPares;

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
	 * Test method for
	 * {@link es.insa.proyecto.mus.negocio.GestorComprobadorSiTieneJuegoYPares#tieneJuego(es.insa.proyecto.dominio.cartas.Jugador)}
	 * .
	 */
	@Test
	public void testTieneJuego() {
		Jugador jugador1 = new Jugador("Jugador1");
		jugador1.a�adirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador1.a�adirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador1.a�adirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador1.a�adirCarta(new Carta(Palo.BASTOS, 1, 1));
		// 2� ejecuci�n
		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QueJuego juego = miGestor.tieneJuego(jugador1);
		// 3� aserci�n
		assertEquals(" Es Treintayuna ", QueJuego.TREINTAYUNA, juego);

		Jugador jugador2 = new Jugador("Jugador2");
		jugador2.a�adirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador2.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador2.a�adirCarta(new Carta(Palo.BASTOS, 5, 5));
		jugador2.a�adirCarta(new Carta(Palo.BASTOS, 7, 7));
		// 2� ejecuci�n
		// 3� aserci�n
		juego = miGestor.tieneJuego(jugador2);
		// 3� aserci�n
		assertEquals(" Es Punto ",QueJuego.PUNTO, juego);
		
		Jugador jugador3 = new Jugador("Jugador3");
		jugador3.a�adirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador3.a�adirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador3.a�adirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador3.a�adirCarta(new Carta(Palo.BASTOS, 7, 7));
		// 2� ejecuci�n
		// 3� aserci�n
		juego = miGestor.tieneJuego(jugador3);
		// 3� aserci�n
		assertEquals(" Juego ",QueJuego.JUEGO, juego);
		
	}

	@Test
	public void testDuplesGochoyPito() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.a�adirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.a�adirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.a�adirCarta(new Pito(Palo.BASTOS, 2, 2));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Duples", QuePares.DUPLES, respuesta);

		
	}
	@Test
	public void test2Pares() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 5, 5));
		jugador.a�adirCarta(new Carta(Palo.OROS, 5, 5));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.a�adirCarta(new Carta(Palo.ESPADAS, 11, 10));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Duples", QuePares.DUPLES, respuesta);

		
	}
	
	@Test
	public void testDuples() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.a�adirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.a�adirCarta(new Carta(Palo.ESPADAS, 4, 4));
		jugador.a�adirCarta(new Carta(Palo.COPAS, 4, 4));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Duples", QuePares.DUPLES, respuesta);

		
	}
	@Test
	public void testDuplesGocho() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.a�adirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.a�adirCarta(new Gocho(Palo.COPAS, 12, 10));
		jugador.a�adirCarta(new Gocho(Palo.BASTOS, 3, 10));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Esperamos que lleguen DUPLES ", QuePares.DUPLES, respuesta);

		
	}


	@Test
	public void test1Pareja() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.a�adirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Esperamos reconozca un PAR ", QuePares.PAR, respuesta);

	}

	@Test
	public void test1ParejaPito() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.a�adirCarta(new Pito(Palo.OROS, 2, 1));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Esperamos reconozca un PAR ", QuePares.PAR, respuesta);

	}
	@Test
	public void test1ParejaGocho() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.a�adirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Esperamos reconozca un PAR ", QuePares.PAR, respuesta);

	}
	@Test
	public void testSinParejas() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador.a�adirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.a�adirCarta(new Pito(Palo.BASTOS, 2, 2));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n

		assertEquals("Esperamos sin PAR", QuePares.NO, respuesta);
	}

	@Test
	public void testMedias() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.a�adirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.a�adirCarta(new Carta(Palo.ESPADAS, 4, 4));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 2, 2));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Debe devolver MEDIAS", QuePares.MEDIAS, respuesta);

	}

	@Test
	public void testMediasGochos() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.a�adirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.a�adirCarta(new Gocho(Palo.ESPADAS, 12, 10));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 5, 5));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Debe devolver MEDIAS", QuePares.MEDIAS, respuesta);

	}

	
	@Test
	public void testMediasPitos() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.a�adirCarta(new Pito(Palo.OROS, 1, 1));
		jugador.a�adirCarta(new Pito(Palo.ESPADAS, 2, 1));
		jugador.a�adirCarta(new Carta(Palo.BASTOS, 5, 5));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Debe devolver MEDIAS", QuePares.MEDIAS, respuesta);

	}
	
	
	@Test
	public void testMediasG() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.a�adirCarta(new Gocho(Palo.BASTOS, 3, 10));
		jugador.a�adirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.a�adirCarta(new Carta(Palo.ESPADAS, 4, 4));
		jugador.a�adirCarta(new Gocho(Palo.BASTOS, 12, 10));
		// 2� ejecuci�n
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3� aserci�n
		Assert.assertEquals("Debe devolver MEDIAS", QuePares.MEDIAS, respuesta);

	}


}


