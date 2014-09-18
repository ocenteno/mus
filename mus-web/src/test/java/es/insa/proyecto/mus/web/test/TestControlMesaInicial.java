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

@SuppressWarnings("deprecation")
public class TestControlMesaInicial {
	
	private static ControladorMesaInicial cmi;
	private static Model m;
	private static HttpSession sesion;

	@BeforeClass
	@SuppressWarnings({"rawtypes", "resource"})
	public static void iniciar(){
		BeanFactory bf = new FileSystemXmlApplicationContext(
				"WebContent/WEB-INF/applicationContext.xml",
				"WebContent/WEB-INF/central-servlet.xml");
		cmi = bf.getBean(ControladorMesaInicial.class);
		m = new BindingAwareModelMap();
		sesion = new HttpSession() {
			
			@Override
			public void setMaxInactiveInterval(int arg0) {
				
			}
			
			@Override
			public void setAttribute(String arg0, Object arg1) {
				
			}
			
			@Override
			public void removeValue(String arg0) {
				
			}
			
			@Override
			public void removeAttribute(String arg0) {
			}
			
			@Override
			public void putValue(String arg0, Object arg1) {
			}
			
			@Override
			public boolean isNew() {
				return false;
			}
			
			@Override
			public void invalidate() {
			}
			
			@Override
			public String[] getValueNames() {
				return null;
			}
			
			@Override
			public Object getValue(String arg0) {
				return null;
			}
			
			@Override
			public HttpSessionContext getSessionContext() {
				return null;
			}
			
			@Override
			public ServletContext getServletContext() {
				return null;
			}
			
			@Override
			public int getMaxInactiveInterval() {
				return 0;
			}
			
			@Override
			public long getLastAccessedTime() {
				return 0;
			}
			
			@Override
			public String getId() {
				return null;
			}
			
			@Override
			public long getCreationTime() {
				return 0;
			}
			
			@Override
			public Enumeration getAttributeNames() {
				return null;
			}
			
			@Override
			public Object getAttribute(String arg0) {
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
