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
	    // 4º REPARAR
		dmh.eliminar(m);
	}
	@Test
	public void testInsertarMazoConCartas(){
		Carta c1 = new Carta(Palo.BASTOS, 1, 1);
		Carta c2 = new Carta(Palo.COPAS, 10, 10);
		Carta c3 = new Carta(Palo.OROS, 7, 7);
		
		Mazo m = new Mazo("barajaEspañola2");
		m.añadir(c1,c2,c3);
		 
	    // Mazo mazoBBDD = dmh.buscar(m.getId());	
		dmh.insertar(m);
		// 3º VERIFICAR
		Assert.assertNotEquals("Tienen que ser distintos",0, m.getCantidadDeCartas());
	    // 4º REPARAR
		dmh.eliminar(m);
	
	}
	
	@Test
	public void testLlenarMazo(){	
		// PREPARAR
		Carta c1 = new Carta(Palo.BASTOS, 1, 1);
		Carta c2 = new Carta(Palo.COPAS, 10, 10);
		Carta c3 = new Carta(Palo.OROS, 7, 7);
		Mazo m = new Mazo("barajaEspañola2");
		m.añadir(c1,c2,c3);
		dmh.insertar(m);
		
		// insertamos lista de cartas del mazo
		dmh.llenarMazo(m);
		
		// 3º VERIFICAR
		Assert.assertNotEquals("La lista no debe ser vacia",0, m.getCantidadDeCartas());
		
		System.out.println(m.getNombre());
		System.out.println(m.getCantidadDeCartas());
	    // 4º REPARAR
		dmh.eliminar(m);		
	}
	
	/**
	 * Se inserta un mazo, se modifica su nombre y se comprueba 
	 * que se cambiado el nombre en la BD
	 */
	@Test
	public void testActualizar() {
		// 1º PREPARAR	
		Mazo m = new Mazo("barajaEspañola");	
		dmh.insertar(m);
		m.setNombre("barajaFrancesa");
		// 2º TEST
		dmh.actualizar(m);
		// 3º VERIFICAR (ASERCIÓN)
		Mazo  enDB = dmh.buscar(m.getId());
		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertEquals(
			"Debería devolver un mazo con nombre barajaFrancesa", m, enDB);
		// 4º REPARAR
		dmh.eliminar(m);
	}
	
	/**
	 * Se inserta un mazo, se guarda su id, se elimina de la DB y se comprueba que 
	 * no se recupera el id guardado antes de eliminarlo, sino que se recupera null de la DB
	 */
	@Test
	public void testEliminar() {
		// 1º PREPARAR	
		Mazo m = new Mazo("barajaEspañola");	
		dmh.insertar(m);
		// 2º TEST
		Mazo guardado = dmh.buscar(m.getId());
		dmh.eliminar(m);
		Mazo enDB = dmh.buscar(m.getId());
		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertNotEquals(
			"Debería devolver null ya que el mazo se ha borrado", guardado, enDB);
	}
	
	/**
	 * Se insertan tres mazos y se comprueba que el tamaño de lo grabado en DB es 3
	 * de la DB	con su número modificado
	 */
	@Test
	public void testListarTodos() {
		// 1º PREPARAR	
		// 1º PREPARAR	
		Mazo m1 = new Mazo("barajaEspañola");	
		Mazo m2 = new Mazo("barajaFrancesa");
		Mazo m3 = new Mazo("barajaEspañola");
		dmh.insertar(m1);
		dmh.insertar(m2);
		dmh.insertar(m3);
	
		// 2º TEST
		int longitudLista = dmh.listarTodos().size(); 

		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertEquals("Debería devolver una longitud de 3", 3, longitudLista);
		// 4º REPARAR
		dmh.eliminar(m1);
		dmh.eliminar(m2);
		dmh.eliminar(m3);
	}

	/**
	 * Se busca un Mazo en BD
	 */
	@Test
	public void testBuscar() {
		// 2º TEST
		Mazo m = dmh.buscar(1); 

		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertNotNull("Debería devolver una longitud de 3", m);
	}
}
