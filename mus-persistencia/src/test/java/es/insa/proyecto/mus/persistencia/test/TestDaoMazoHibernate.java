package es.insa.proyecto.mus.persistencia.test;


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
		// 1º PREPARAR
		
		Mazo m = new Mazo("barajaEspañola");	
		// 2º TEST
		dmh.insertar(m);
		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertNotEquals(
				"Debería devolver un ID distinto de cero", 0, m.getId());
	}
	@Test
	public void testInsertarMazoConCartas(){
		Carta c1 = new Carta(Palo.BASTOS, 1, 1);
		Carta c2 = new Carta(Palo.COPAS, 10, 10);
		Carta c3 = new Carta(Palo.OROS, 7, 7);
		
		Mazo m = new Mazo("barajaEspañola2");
		m.añadir(c1,c2,c3);
		 
		//Mazo mazoBBDD = dmh.buscar(m.getId());
		
		
		dmh.insertar(m);
		// 3º VERIFICAR
		Assert.assertNotEquals("Tienen que ser distintos",0, m.getCantidadDeCartas());
		
		
	}
	@Test
	public void testLlenarMazo(){	
		// PREPARAR
			
		Mazo m = new Mazo("barajaEspañola2");
		m.setId(3);
		//m.añadir(c1,c2,c3);
		
		// insertamos lista de cartas del mazo
		dmh.llenarMazo(m);
		
		// 3º VERIFICAR
		Assert.assertNotEquals("La lista no debe ser vacia",0, m.getCantidadDeCartas());
		
		System.out.println(m.getNombre());
		System.out.println(m.getCantidadDeCartas());
		
		
	}
	

}
