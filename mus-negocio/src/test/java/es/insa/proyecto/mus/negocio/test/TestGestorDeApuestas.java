package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.mus.contratos.IGestorDeApuestas;
import es.insa.proyecto.mus.negocio.GestorDeApuestas;

public class TestGestorDeApuestas {

	//IGestorDeApuestas igda;
	GestorDeApuestas gda = new GestorDeApuestas();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	@Test
	public void testGrandes() {
		
		gda.subir(4, "Grandes");
		gda.subir(2, "Grandes");
		gda.subir(5, "Grandes");
		gda.noQuiero("Grandes");
		gda.subir(2, "Grandes");
		gda.quiero("Grandes");
		int bote = gda.getApuestas().get("Grandes");
		
		assertEquals("La apuesta a grandes debe ser 13", 13, bote);
	}

	@Test
	public void testChicas() {
		
		gda.subir(4, "Chicas");
		gda.subir(6, "Chicas");
		gda.ordago("Chicas");
		gda.noQuiero("Chicas");
		gda.quiero("Chicas");
		int bote = gda.getApuestas().get("Chicas");
		
		assertEquals("La apuesta a chicas debe ser 50", 50, bote);
	}
	
	@Test
	public void testPares() {
		
		gda.subir(3, "Pares");
		gda.noQuiero("Pares");
		gda.subir(6, "Pares");
		gda.quiero("Pares");
		int bote = gda.getApuestas().get("Pares");
		
		assertEquals("La apuesta a pares debe ser 9", 9, bote);
	}
	
	@Test
	public void testJuego() {
		
		gda.subir(3, "Juego");
		gda.noQuiero("Juego");
		gda.subir(6, "Juego");
		gda.ordago("Juego");
		gda.quiero("Juego");
		int bote = gda.getApuestas().get("Juego");
		
		assertEquals("La apuesta a juego debe ser 49", 49, bote);
	}
	
	@Test
	public void testPunto() {
		
		gda.subir(3, "Punto");
		gda.noQuiero("Punto");
		gda.subir(6, "Punto");
		gda.ordago("Punto");
		gda.noQuiero("Punto");
		gda.quiero("Punto");
		int bote = gda.getApuestas().get("Punto");
		
		assertEquals("La apuesta a punto debe ser 49", 49, bote);
	}
	
}
