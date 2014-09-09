package es.insa.proyecto.dominio.cartas.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Mazo;
import es.insa.proyecto.dominio.cartas.Palo;



public class TestMazo {
	Mazo miMazo = new Mazo();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	
	}

	
	

	

	@Test
	public void testAñadirCarta() {
	
		miMazo.setNombre("cartas de mus de 4 reyes");
		miMazo.setId(1); 
		Carta c = new Carta();
		c.setNumero(1);
		c.setPalo(Palo.OROS);
		c.setValor(1);
		miMazo.añadir(c);
		assertNotNull(c);
		
	}

	@Test
	public void testSacarCarta() {
		
		miMazo.setNombre("cartas de mus de 4 reyes");
		miMazo.setId(1); 
		Carta c = new Carta();
		c.setNumero(1);
		c.setPalo(Palo.OROS);
		c.setValor(1);
		miMazo.añadir(c);
		assertNotNull(c);
		Carta e = miMazo.sacarCarta();
		assertNotNull(e);
		Carta d = miMazo.sacarCarta();
		assertNull("La segunda carta debería ser NULL" , d );
		
		
		
	}
	
}
