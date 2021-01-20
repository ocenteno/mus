package es.insa.proyecto.dominio.cartas.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Mazo;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;

public class TestMazo {
	Mazo miMazo = new Mazo();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}
	
	/**
	 * Test para comprobar que se crea el mazo correctamente con un nombre y una lista de cartas vacía
	 */
	@Test
	public void testCrearMazo() {
		// 1º test
		Mazo mazo = new Mazo("Nombre Mazo");
		// 3º aserción
		Assert.assertNotNull("El nombre no puede estar vacio", mazo.getNombre());
		Assert.assertEquals("Ej mazo debe tener una lista de cartas vacía", 0, mazo.getCantidadDeCartas());

	}

	/**
	 * Test para comprobar que se puede añadir una carta al mazo
	 */
	@Test
	public void testAñadirCarta() {

		miMazo.setNombre("cartas de mus de 4 reyes");
		miMazo.setId(1);
		Carta c = new Carta();
		c.setNumero(1);
		c.setPalo(Palo.OROS);
		c.setValor(1);
		miMazo.añadir(c);
		assertEquals("Debe haber una carta en el mazo",1, miMazo.getCantidadDeCartas());

	}

	/**
	 * Test para comprobar que se puede añadir una lista de cartas al mazo
	 */
	@Test
	public void testAñadirListaCarta() {

		miMazo.setNombre("cartas de mus de 4 reyes");
	//	miMazo.setId(1);
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
		
		miMazo.añadir(lista);
		
		assertEquals("Debe haber 7 cartas en el mazo",7, miMazo.getCantidadDeCartas());

	}
	
	/**
	 * Test para comprobar que se puede añadir un array de cartas al mazo
	 */
	@Test
	public void testAñadirArrayCarta() {

		miMazo.setNombre("cartas de mus de 4 reyes");
		Carta[] array = new Carta[5];
		Carta c2 = new Carta(Palo.BASTOS, 5, 5);
		Carta c3 = new Carta(Palo.BASTOS, 11, 10);
		Gocho g1 = new Gocho(Palo.BASTOS, 3, 10);
		Carta g2 = new Carta(Palo.BASTOS, 12, 10);
		Pito p1 = new Pito(Palo.BASTOS, 1, 1);
		array[0] = c2;
		array[1] = c3;
		array[2] = g1;
		array[3] = g2;
		array[4] = p1;
			
		miMazo.añadir(array);
		
		assertEquals("Debe haber 5 cartas en el mazo",5, miMazo.getCantidadDeCartas());

	}
	/**
	 * Test para comprobar que se puede sacar una carta al mazo y que se controla el fin del mazo devolviendo null
	 */
	@Test
	public void testSacarCarta() {

		miMazo.setNombre("cartas de mus de 4 reyes");
		Carta c = new Carta();
		c.setNumero(1);
		c.setPalo(Palo.OROS);
		c.setValor(1);
		miMazo.añadir(c);
		miMazo.sacarCarta();
		assertEquals("No debe haber una carta en el mazo",0, miMazo.getCantidadDeCartas());
		Carta d = miMazo.sacarCarta();
		assertNull("Debería devolver NULL ya que no hay cartas en el mazo", d);

	}

	/**
	 * Test para comprobar que se puede añadir una lista de cartas al mazo
	 */
	@Test
	public void testGetCantidadDeCartas() {

		miMazo.setNombre("cartas de mus de 4 reyes");
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
		
		miMazo.añadir(lista);
		
		int numCartas = miMazo.getCantidadDeCartas();
		
		assertEquals("Debe haber 7 cartas en el mazo",7, numCartas);

	}

	/**
	 * Test para comprobar que se puede añadir una lista de cartas al mazo
	 */
	@Test
	public void testGetListaDeCartasDelMazo() {

		miMazo.setNombre("cartas de mus de 4 reyes");
		Carta c1 = new Carta(Palo.BASTOS, 4, 4);
		Carta c2 = new Carta(Palo.BASTOS, 5, 5);
		Carta c3 = new Carta(Palo.BASTOS, 11, 10);
		Carta c4 = new Carta(Palo.BASTOS, 12, 10);
				
		ArrayList <Carta> lista = new ArrayList <>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		lista.add(c4);
		
		miMazo.añadir(lista);
				
		assertEquals("La lista de cartas obtenida debe tener 4 cartas", 4, miMazo.getCantidadDeCartas());

	}
}
