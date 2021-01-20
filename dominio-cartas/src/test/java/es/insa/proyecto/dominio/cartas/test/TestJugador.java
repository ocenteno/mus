package es.insa.proyecto.dominio.cartas.test;

import org.junit.Assert;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;

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
		Carta carta1 = new Carta();
		Jugador jugador = new Jugador("Jugador1");
		// 2º ejecución
		jugador.añadirCarta(carta1);
		// 3º aserción
		Assert.assertNotEquals("El jugador debe tener una mano", 0, jugador.getMano().length);
		Carta carta2 = new Carta();
		Carta carta3 = new Carta();
		Carta carta4 = new Carta();
		Carta carta5 = new Carta();
		jugador.añadirCarta(carta2);
		jugador.añadirCarta(carta3);
		jugador.añadirCarta(carta4);
		errorMano = jugador.añadirCarta(carta5);
		// 5º Aserción
		Assert.assertTrue("Ya tengo 4 cartas ", errorMano);
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

	@Test
	public void testOrdenarManoPito() {
		// 1º preparación
		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Pito(Palo.BASTOS, 2, 2));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		// 2º ejecución
		jugador.ordenarMano();
		// 3º aserción
		Carta[] mano = jugador.getMano();
		
		Assert.assertEquals("La primera - 2", 2, mano[0].getNumero());
		Assert.assertEquals("La segunda - 1", 1, mano[1].getNumero());
		Assert.assertEquals("La tercera - 3", 3, mano[2].getNumero());
		Assert.assertEquals("La cuarta - 7", 7,  mano[3].getNumero());
		
	}

	@Test
	public void testOrdenarManoGocho() {
		// 1º preparación
		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		// 2º ejecución
		jugador.ordenarMano();
		// 3º aserción
		Carta[] mano = jugador.getMano();
		
		Assert.assertEquals("La primera - 1", 1, mano[0].getNumero());
		Assert.assertEquals("La segunda - 7", 7, mano[1].getNumero());
		Assert.assertEquals("La tercera - 12", 12, mano[2].getNumero());
		Assert.assertEquals("La cuarta - 3", 3,  mano[3].getNumero());
		
	}
	

	@Test
	public void testOrdenarMano() {
		// 1º preparación
		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 3, 10));
		jugador.añadirCarta(new Carta(Palo.BASTOS, 7, 7));
		jugador.añadirCarta(new Pito(Palo.BASTOS, 1, 1));
		// 2º ejecución
		jugador.ordenarMano();
		// 3º aserción
		Carta[] mano = jugador.getMano();
		
		Assert.assertEquals("La primera - 1", 1, mano[0].getNumero());
		Assert.assertEquals("La segunda - 3", 3, mano[1].getNumero());
		Assert.assertEquals("La tercera - 7", 7, mano[2].getNumero());
		Assert.assertEquals("La cuarta - 12", 12,  mano[3].getNumero());
		
	}
}
