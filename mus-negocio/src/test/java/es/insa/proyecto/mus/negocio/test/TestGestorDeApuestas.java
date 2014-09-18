package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.mus.modelo.Lances;
import es.insa.proyecto.mus.negocio.GestorDeApuestas;

public class TestGestorDeApuestas {

	//IGestorDeApuestas igda;
	GestorDeApuestas gda = new GestorDeApuestas();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Se comprueba que si se envida y se quiere el valor de la apuesta de 2
	 */
	@Test
	public void testEnvidoQuiero() {
		
		gda.envido(Lances.GRANDE);
		gda.quiero(Lances.GRANDE);
		int bote = gda.getApuestas(Lances.GRANDE);
		
		assertEquals("La apuesta debe ser 2", 2, bote);
	}

	/**
	 * Se comprueba que si se envida y no se quiere el valor de la apuesta es 0
	 */
	@Test
	public void testEnvidoNoQuiero() {
		
		gda.envido(Lances.CHICA);
		gda.noQuiero(Lances.CHICA);
		int bote = gda.getApuestas(Lances.CHICA);
		
		assertEquals("La apuesta debe ser 0", 0, bote);
	}

	/**
	 * Se comprueba que si se envida y se apuestan 5 y se vuelve a envidar y se quiere el valor de la apuesta es 9 (2+5+2)
	 */	
	@Test
	public void testEnvidoVariasQuiero() {
		
		gda.envido(Lances.JUEGO);
		gda.apostar(5, Lances.JUEGO);
		gda.envido(Lances.JUEGO);
		gda.quiero(Lances.JUEGO);
		int bote = gda.getApuestas(Lances.JUEGO);
		
		assertEquals("La apuesta debe ser 9", 9, bote);
	}

	/**
	 * Se comprueba que si se envida y se apuestan 5 y se vuelve a envidar y no se quiere el valor de la apuesta es 7 (2+5)
	 */	
	@Test
	public void testEnvidoVariasNoQuiero() {
		
		gda.envido(Lances.PARES);
		gda.apostar(5, Lances.PARES);
		gda.envido(Lances.PARES);
		gda.noQuiero(Lances.PARES);
		int bote = gda.getApuestas(Lances.PARES);
		
		assertEquals("La apuesta debe ser 7", 7, bote);
	}
	
	/**
	 * Se comprueba que si se envida y se echa un ordago y se quiere el valor de la apuesta es 40
	 */	
	@Test
	public void testOrdagoQuiero() {
		gda.envido(Lances.JUEGO);
		gda.ordago(Lances.JUEGO);
		gda.quiero(Lances.JUEGO);
		int bote = gda.getApuestas(Lances.JUEGO);
		
		assertEquals("La apuesta debe ser 40", 40, bote);
	}

	/**
	 * Se comprueba que si se echa un ordago y no se quiere el valor de la apuesta es 0
	 */	
	@Test
	public void testOrdagoNoQuiero() {
		gda.ordago(Lances.PUNTO);
		gda.noQuiero(Lances.PUNTO);
		int bote = gda.getApuestas(Lances.PUNTO);
		
		assertEquals("La apuesta debe ser 0", 0, bote);
	}
	
}
