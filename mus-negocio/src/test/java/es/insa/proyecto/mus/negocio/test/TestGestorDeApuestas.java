package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.mus.contratos.IGestorDeApuestas;
import es.insa.proyecto.mus.modelo.Lances;
import es.insa.proyecto.mus.negocio.GestorDeApuestas;

public class TestGestorDeApuestas {

	//IGestorDeApuestas igda;
	GestorDeApuestas gda = new GestorDeApuestas();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	@Test
	public void testEnvidoQuiero() {
		
		gda.envido(Lances.GRANDE);
		gda.quiero(Lances.GRANDE);
		int bote = gda.getApuestas().get(Lances.GRANDE);
		
		assertEquals("La apuesta debe ser 2", 2, bote);
	}

	@Test
	public void testEnvidoNoQuiero() {
		
		gda.envido(Lances.CHICA);
		gda.noQuiero(Lances.CHICA);
		int bote = gda.getApuestas().get(Lances.CHICA);
		
		assertEquals("La apuesta debe ser 0", 0, bote);
	}

	
	@Test
	public void testEnvidoVariasQuiero() {
		
		gda.envido(Lances.JUEGO);
		gda.apostar(5, Lances.JUEGO);
		gda.envido(Lances.JUEGO);
		gda.quiero(Lances.JUEGO);
		int bote = gda.getApuestas().get(Lances.JUEGO);
		
		assertEquals("La apuesta debe ser 9", 9, bote);
	}
	
	@Test
	public void testEnvidoVariasNoQuiero() {
		
		gda.envido(Lances.PARES);
		gda.apostar(5, Lances.PARES);
		gda.envido(Lances.PARES);
		gda.noQuiero(Lances.PARES);
		int bote = gda.getApuestas().get(Lances.PARES);
		
		assertEquals("La apuesta debe ser 7", 7, bote);
	}
	
	@Test
	public void testOrdagoQuiero() {
		gda.envido(Lances.JUEGO);
		gda.ordago(Lances.JUEGO);
		gda.quiero(Lances.JUEGO);
		int bote = gda.getApuestas().get(Lances.JUEGO);
		
		assertEquals("La apuesta debe ser 40", 40, bote);
	}
	
	@Test
	public void testOrdagoNoQuiero() {
//		gda.envido(Lances.PUNTO);
		gda.ordago(Lances.PUNTO);
		gda.noQuiero(Lances.PUNTO);
		int bote = gda.getApuestas().get(Lances.PUNTO);
		
//		assertEquals("La apuesta debe ser 2", 2, bote);
		assertEquals("La apuesta debe ser 0", 0, bote);
	}
	
}
