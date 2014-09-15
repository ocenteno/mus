package es.insa.proyecto.mus.persistencia;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;




import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;

import es.insa.proyecto.mus.contratos.DAO;

public abstract class DaoGenericoHibernate<G, K extends Serializable> 
				implements DAO<G, K> {
	
	
	protected static SessionFactory sf;
	private Class<G> claseG;
	
	
	@Before
	public void abrirSesion(){
		sf.openSession();
	}
	@After
	public void cerrarSesion(){
		sf.getCurrentSession().disconnect();
		sf.getCurrentSession().close();
	}
			
	public void setSessionFactory(SessionFactory sesFact){
			sf = sesFact;
	}

	public DaoGenericoHibernate() {
		
		if (sf == null) {
			Configuration cfg = new Configuration()
					.configure("cfg/hibernate.cfg.xml");
			ServiceRegistry sr = 
					new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			sf = cfg.buildSessionFactory(sr);
		}
		
		ParameterizedType claseGenerica = (ParameterizedType)
				this.getClass().getGenericSuperclass();
					
		
		Type[] params = claseGenerica.getActualTypeArguments();
		
		claseG = (Class<G>) params[0];
	}
	
	@Override
	public void insertar(G k) {
		sf.getCurrentSession().beginTransaction();
		sf.getCurrentSession().save(k);
		sf.getCurrentSession().getTransaction().commit();				
		
	}

	@Override
	public void actualizar(G k) {
		sf.getCurrentSession().beginTransaction();
		sf.getCurrentSession().update(k);
		sf.getCurrentSession().getTransaction().commit();
		
	}

	@Override
	public void eliminar(G k) {
		sf.getCurrentSession().beginTransaction();
		try {
			sf.getCurrentSession().delete(k);
		} catch (Exception e) {
		}
		sf.getCurrentSession().getTransaction().commit();
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public G buscar(K id) {
		sf.getCurrentSession().beginTransaction();
		Object resultado = sf.getCurrentSession().get(claseG, id);
		sf.getCurrentSession().getTransaction().commit();
		return (G) resultado;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<G> listarTodos() {
		sf.getCurrentSession().beginTransaction();
		List<G> lista =(List<G>) sf.getCurrentSession()
						.createCriteria(claseG).list();
		sf.getCurrentSession().getTransaction().commit();
		return lista;

	}

}
