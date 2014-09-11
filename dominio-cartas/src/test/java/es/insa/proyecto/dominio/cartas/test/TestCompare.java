package es.insa.proyecto.dominio.cartas.test;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;

public class TestCompare {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		
		Carta c1 = new Carta(Palo.BASTOS, 4, 4);
		Carta c2 = new Carta(Palo.BASTOS, 5, 5);
		Carta c3 = new Carta(Palo.BASTOS, 11, 10);
		Gocho g1 = new Gocho(Palo.BASTOS, 3, 10);
		Carta g2 = new Carta(Palo.BASTOS, 12, 10);
		Pito p1 = new Pito(Palo.BASTOS, 1, 1);
		Pito p2 = new Pito(Palo.BASTOS, 2, 1);
				
		ArrayList <Carta> lista = new ArrayList <>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		lista.add(g1);
		lista.add(g2);
		lista.add(p1);
		lista.add(p2);
		
		Collections.sort(lista);
		
		assertEquals("La primera - PITO", p1, lista.get(0));
		assertEquals("La segunda - PITO", p2, lista.get(1));
		assertEquals("La tercera - 4 BASTOS", c1, lista.get(2));
		assertEquals("La cuarta - 5 BASTOS", c2, lista.get(3));
		assertEquals("La quinta - 11 BASTOS", c3, lista.get(4));
		assertEquals("La sexta - GOCHO", g1, lista.get(5));
		assertEquals("La séptima - GOCHO", g2, lista.get(6));
		
		for (int i = 0; i <=6; i++) {
			System.out.println("Lista - " + i + " = " + lista.get(i).toString());
		}
		
		
	}
	
	

}
