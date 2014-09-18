package es.insa.proyecto.mus.web.test;


import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import es.insa.proyecto.mus.web.controladores.ControladorMesaInicial;

public class TestControlMesaInicial {
	
	private static ControladorMesaInicial cmi;
	private static Model m;
	private static HttpSession sesion;

	@BeforeClass
	public static void iniciar(){
		@SuppressWarnings("resource")
		BeanFactory bf = new FileSystemXmlApplicationContext(
				"WebContent/WEB-INF/applicationContext.xml",
				"WebContent/WEB-INF/central-servlet.xml");
		cmi = bf.getBean(ControladorMesaInicial.class);
		m = new BindingAwareModelMap();
		sesion = new HttpSession() {
			
			@Override
			public void setMaxInactiveInterval(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeValue(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeAttribute(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putValue(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isNew() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void invalidate() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String[] getValueNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public HttpSessionContext getSessionContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServletContext getServletContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getMaxInactiveInterval() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getLastAccessedTime() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getCreationTime() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Enumeration getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getAttribute(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	@Test
	public void testRefrescar(){
		cmi.sentarJugador("J1", 0, m, sesion);
		cmi.sentarJugador("J2", 1, m, sesion);
		cmi.sentarJugador("J3", 2, m, sesion);
		cmi.sentarJugador("J4", 3, m, sesion);
		Assert.assertTrue(true);
	}
}
