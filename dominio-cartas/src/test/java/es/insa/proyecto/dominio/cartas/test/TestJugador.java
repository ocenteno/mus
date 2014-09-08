package es.insa.proyecto.dominio.cartas.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Jugador;

public class TestJugador {
	private String nombre;
	
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
		Object carta = new Object();
		Jugador jugador = new Jugador("Jugador1");
		// 2º ejecución
		jugador.añadirCarta(carta);
		// 3º aserción
		Assert.assertNotEquals("Ej jugador debe tener una mano", 0, jugador.getMano().length);
	}

	@Test
	public void testQuitarCarta() {
		// 1º preparación
		Object carta = new Object();
		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(carta);
		// 2º ejecución
		jugador.quitarCarta(carta);
		// 3º aserción
		Assert.assertEquals("Ej jugador debe tener una mano vacía", 0, jugador.getMano().length);
	}

	@Test
	public void testConsultarMano() {
		// 1º preparación
		Object carta1 = new Object();
		Object carta2 = new Object();
		Object carta3 = new Object();
		Object carta4 = new Object();
		Jugador jugador = new Jugador("Jugador1");
		jugador.añadirCarta(carta1);
		jugador.añadirCarta(carta2);
		jugador.añadirCarta(carta3);
		jugador.añadirCarta(carta4);
		// 2º ejecución
		jugador.getMano();
		// 3º aserción
		Assert.assertEquals("Ej jugador debe tener una mano con 4 cartas", 4, jugador.getMano().length);
	}

	

}
