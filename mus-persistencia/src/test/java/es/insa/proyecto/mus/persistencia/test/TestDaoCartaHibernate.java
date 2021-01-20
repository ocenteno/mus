package es.insa.proyecto.mus.persistencia.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.persistencia.DaoCartaHibernate;

public class TestDaoCartaHibernate {

	private static DaoCartaHibernate dch;

	@BeforeClass
	public static void inicializar() {
		dch = new DaoCartaHibernate();
	}

	/**
	 * Se inserta una carta y se comprueba que se recupera su id de la DB
	 */
	@Test
	public void testInsertar() {
		// 1º PREPARAR

		Carta c = new Carta(Palo.COPAS, 12, 10);
		// 2º TEST
		dch.insertar(c);
		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertNotEquals("Debería devolver un ID distinto de cero", 0,
				c.getId());
		// 4º REPARAR
		dch.eliminar(c);
	}

	/**
	 * Se inserta una carta, se modifica su número y se comprueba que se
	 * recupera de la DB con su número modificado
	 */
	@Test
	public void testActualizar() {
		// 1º PREPARAR
		Carta c = new Carta(Palo.COPAS, 12, 10);
		dch.insertar(c);
		c.setNumero(1);
		// 2º TEST
		dch.actualizar(c);
		Carta enDB = dch.buscar(c.getId());
		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertEquals(
				"Debería devolver una carta de número distinto a 12", c, enDB);
		// 4º REPARAR
		dch.eliminar(c);
	}

	/**
	 * Se inserta una carta, se guarda su id, se elimina de la DB y se comprueba
	 * que no se recupera el id guardado antes de eliminarla, sino que se
	 * recupera null de la DB
	 */
	@Test
	public void testEliminar() {
		// 1º PREPARAR
		Carta c = new Carta(Palo.COPAS, 12, 10);
		dch.insertar(c);
		// 2º TEST
		Carta guardado = dch.buscar(c.getId());
		dch.eliminar(c);
		Carta enDB = dch.buscar(c.getId());
		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertNotEquals(
				"Debería devolver null ya que la carta se ha borrado",
				guardado, enDB);
	}

	/**
	 * Se insertan tres cartas y se comprueba que el tamaño de lo grabado en DB
	 * es 3 de la DB con su número modificado
	 */
	@Test
	public void testListarTodos() {
		// 1º PREPARAR
		int longitudAntes = dch.listarTodos().size();
		Carta c1 = new Carta(Palo.COPAS, 6, 10);
		dch.insertar(c1);
		Carta c2 = new Carta(Palo.BASTOS, 12, 10);
		dch.insertar(c2);
		Carta c3 = new Carta(Palo.ESPADAS, 10, 10);
		dch.insertar(c3);

		// 2º TEST
		int longitudDespues = dch.listarTodos().size();

		// 3º VERIFICAR (ASERCIÓN)
		Assert.assertEquals("Debería devolver una longitud de 3 cartas nuevas", 3,
				longitudDespues-longitudAntes);
		// 4º REPARAR
		dch.eliminar(c1);
		dch.eliminar(c2);
		dch.eliminar(c3);
	}

}
