package es.insa.proyecto.mus.persistencia.test;


import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.mus.persistencia.DaoGenerico;

public class TestGenerico {

	private static  DaoGenerico dao;
	
	@BeforeClass
	public static void inicializar() {
	//pendiente	dao = new DaoGenerico();
		}
	
	@Test
	public void testInsertar() {
		// 1� PREPARAR
	//pendiente	Carta c = new Carta(12,"COPAS",10);
				
		// 2� TEST
		
		// pendiente dao.save(c);
		// 3� VERIFICAR (ASERCI�N)
		//pendiente Assert.assertNotEquals(
		//pendiente		"Deber�a devolver un ID distinto de cero", 0, c.getId());
	}
	

}
