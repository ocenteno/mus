package es.insa.proyecto.dominio.cartas.test;

import org.junit.Assert;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;

public class TestJugador {
	boolean errorMano = false;
	
	@Test
	public void testCrearJugador() {
		// 1º test
		Jugador jugador = new Jugador("Jugador1");
		// 3º aserción
		Assert.assertNotNull("El nombre no puede estar vacio", jugador.getNombre());
		Assert.assertEquals("Ej jugador debe tener una mano vacía", 0, jugador.getMano().length);

	}

	@Test
	public void testAñadirCarta() {
		// 1º preparación
		Carta carta = new Carta();
		Jugador jugador = new Jugador("Jugador1");
		// 2º ejecución
		jugador.añadirCarta(carta);
		// 3º aserción
		Assert.assertNotEquals("El jugador debe tener una mano", 0, jugador.getMano().length);
		// 4º Comprobar que no exista ya
		errorMano = jugador.añadirCarta(carta);
		// 5º Aserción
		Assert.assertTrue("La carta ya existe o tengo ya 4 cartas ", errorMano);
	}

	@Test
	public void testQuitarCarta() {
		// 1º preparación
		Carta carta = new Carta();
		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(carta);
		// 2º ejecución
		jugador.quitarCarta(carta);
		// 3º aserción
		Assert.assertEquals("El jugador debe tener una mano vacía", 0, jugador.getMano().length);
		// 4º comprobar que no se puede quitar una que no tenemos
		errorMano = jugador.quitarCarta(carta);
		// 5º aserción
		Assert.assertTrue("La carta sigue existiendo y no debería", errorMano);
	}

	@Test
	public void testConsultarMano() {
		// 1º preparación
		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 10, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 11, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		// 2º ejecución
		Carta[] mano = jugador.getMano();
		// 3º aserción
		Assert.assertEquals("El jugador debe tener una mano con 4 cartas", 4, mano.length);
	}

	

}
