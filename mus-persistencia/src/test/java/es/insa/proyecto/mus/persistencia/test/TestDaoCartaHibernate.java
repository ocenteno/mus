package es.insa.proyecto.mus.persistencia.test;



import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.persistencia.DaoCartaHibernate;


public class TestDaoCartaHibernate {

	private static  DaoCartaHibernate dch;
	
	@BeforeClass
	public static void inicializar() {
		dch = new DaoCartaHibernate();
		}
	
	@Test
	public void testInsertar() {
		// 1� PREPARAR
		
		Carta c = new Carta(Palo.COPAS,12,10);	
		// 2� TEST
		dch.insertar(c);
		// 3� VERIFICAR (ASERCI�N)
		Assert.assertNotEquals(
				"Deber�a devolver un ID distinto de cero", 0, c.getId());
	}
	//public void actualizar(G k) {
	//public void eliminar(G k)
	//public G buscar(K id)
	//public List<G> listarTodos()
	
	
	

}
