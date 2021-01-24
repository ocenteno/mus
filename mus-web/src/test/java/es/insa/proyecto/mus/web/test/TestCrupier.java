package es.insa.proyecto.mus.web.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import es.insa.proyecto.mus.contratos.ICrupier;
import es.insa.proyecto.mus.persistencia.test.GeneradorMazos;

public class TestCrupier {
	
	private static ICrupier c;

	@BeforeClass
	public static void iniciar(){
		String applicationContext = "WebContent/WEB-INF/testContext.xml";
		new GeneradorMazos(applicationContext).guardarNuevoMazo();
		@SuppressWarnings("resource")
		BeanFactory bf = new FileSystemXmlApplicationContext(applicationContext);
		c = bf.getBean(ICrupier.class); 
		new GeneradorMazos().crearBaraja();
	}
	
	@Test
	public void testBarajar() {
		// TODO: fail("Not yet implemented");
	}

	@Test
	public void testInicializarMazo() {
		c.inicializarMazo();
		assertTrue(true);
	}

	@Test
	public void testRepartirCartas() {
		// TODO: fail("Not yet implemented");
	}

	@Test
	public void testDescartarCartas() {
		// TODO: fail("Not yet implemented");
	}

	@Test
	public void testRecogerDescartes() {
		// TODO: fail("Not yet implemented");
	}

}
