package es.insa.proyecto.mus.persistencia.test;


import java.util.LinkedList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;





import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Mazo;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.persistencia.DaoMazoHibernate;

public class TestDaoMazoHibernate {

	private static  DaoMazoHibernate dmh;
	
	
	@BeforeClass
	public static void inicializar() {
		dmh = new DaoMazoHibernate();
		}
	
	@Test
	public void testInsertarMazoVacio() {
		// 1� PREPARAR
		
		Mazo m = new Mazo("barajaEspa�ola");	
		// 2� TEST
		dmh.insertar(m);
		// 3� VERIFICAR (ASERCI�N)
		Assert.assertNotEquals(
				"Deber�a devolver un ID distinto de cero", 0, m.getId());
	}
	@Test
	public void testInsertarMazoConCartas(){
		Carta c1 = new Carta(Palo.BASTOS, 1, 1);
		Carta c2 = new Carta(Palo.COPAS, 10, 10);
		Carta c3 = new Carta(Palo.OROS, 7, 7);
		
		Mazo m = new Mazo("barajaEspa�ola2");
		m.a�adir(c1,c2,c3);
		
		//Mazo mazoBBDD = dmh.buscar(m.getId());
		
		
		dmh.insertar(m);
		// 3� VERIFICAR
		Assert.assertNotEquals("Tienen que ser distintos",0, m.getCantidadDeCartas());
		
		
	}
	@Test
	public void testLlenarMazo(){	
		// PREPARAR
			
		Mazo m = new Mazo("barajaEspa�ola2");
		m.setId(3);
		//m.a�adir(c1,c2,c3);
		
		// insertamos lista de cartas del mazo
		dmh.llenarMazo(m);
		
		// 3� VERIFICAR
		Assert.assertNotEquals("La lista no debe ser vacia",0, m.getCantidadDeCartas());
		
		System.out.println(m.getNombre());
		System.out.println(m.getCantidadDeCartas());
		
		
	}
	

}
