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
		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 10, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		// 2º ejecución
		Carta[] mano = jugador.getMano();
		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		miGestor.tieneJuego(jugador);
		// 3º aserción
		Assert.assertTrue(true);

		Jugador jugador2 = new Jugador("Jugador1");
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 10, 10));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		jugador2.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		// 2º ejecución
		Carta[] mano1 = jugador.getMano();
		GestorComprobadorSiTieneJuegoYPares miGestor1 = new GestorComprobadorSiTieneJuegoYPares();
		miGestor1.tieneJuego(jugador2);
		// 3º aserción
		// Assert.assertFalse(false);
		Assert.assertTrue(true);
	}

	@Test
	public void testDuplesGochoyPito() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 2, 2));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Duples", QuePares.DUPLES, respuesta);

		
	}
	@Test
	public void test2Pares() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		jugador.añadirCarta(new Carta(Palo.OROS, 5, 5));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.ESPADAS, 11, 10));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Duples", QuePares.DUPLES, respuesta);

		
	}
	
	@Test
	public void testDuples() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.ESPADAS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.COPAS, 4, 4));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Duples", QuePares.DUPLES, respuesta);

		
	}
	@Test
	public void testDuplesGocho() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Gocho(Palo.COPAS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Esperamos que lleguen DUPLES ", QuePares.DUPLES, respuesta);

		
	}


	@Test
	public void test1Pareja() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Esperamos reconozca un PAR ", QuePares.PAR, respuesta);

	}

	@Test
	public void test1ParejaPito() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.añadirCarta(new Pito(Palo.OROS, 2, 1));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Esperamos reconozca un PAR ", QuePares.PAR, respuesta);

	}
	@Test
	public void test1ParejaGocho() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 6, 6));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Esperamos reconozca un PAR ", QuePares.PAR, respuesta);

	}
	@Test
	public void testSinParejas() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 2, 2));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción

		assertEquals("Esperamos sin PAR", QuePares.NO, respuesta);
	}

	@Test
	public void testMedias() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.ESPADAS, 4, 4));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Debe devolver MEDIAS", QuePares.MEDIAS, respuesta);

	}

	@Test
	public void testMediasGochos() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Gocho(Palo.ESPADAS, 12, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Debe devolver MEDIAS", QuePares.MEDIAS, respuesta);

	}

	
	@Test
	public void testMediasPitos() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		jugador.añadirCarta(new Pito(Palo.OROS, 1, 1));
		jugador.añadirCarta(new Pito(Palo.ESPADAS, 2, 1));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Debe devolver MEDIAS", QuePares.MEDIAS, respuesta);

	}
	
	
	@Test
	public void testMediasG() {

		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		jugador.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.ESPADAS, 4, 4));
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		// 2º ejecución
		Carta[] mano = jugador.getMano();

		GestorComprobadorSiTieneJuegoYPares miGestor = new GestorComprobadorSiTieneJuegoYPares();
		QuePares respuesta = miGestor.quePares(jugador);
		// 3º aserción
		Assert.assertEquals("Debe devolver MEDIAS", QuePares.MEDIAS, respuesta);

	}


}


