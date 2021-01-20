package es.insa.proyecto.mus.persistencia;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Mazo;
import es.insa.proyecto.mus.contratos.DaoMazo;

/**
 * Inserta, actualiza, elimina, busca 
 * y lista todos los mazos.
 * @author Nazaret y Jose Antonio
 *
 */
public class DaoMazoHibernate
	 extends DaoGenericoHibernate<Mazo, Integer>
  implements DaoMazo{
	
	/**
	 * Constructor que carga un fichero de configuración de hibernate pasado por parámetro
	 * @param fichero Ubicación del fichero de configuración de hibernate
	 */
	public DaoMazoHibernate(String fichero) {
		super(fichero);
	}
	
	/**
	 * Constructor que carga el fichero de configuración por defecto de hibernate
	 * en el directorio cfg/hibernate.cfg.xml
	 */
	public DaoMazoHibernate() {
		super();
	}
	/**
	 * Llenar Mazo de cartas
	 * dado un mazo m, va a las Cartas y recoge todas las que pertenezcan a ese mazo
	 * y las inserta en el mazo	
	 * @param m
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void llenarMazo(Mazo m){
		
		
		Session sesion = sf.getCurrentSession();
		sesion.beginTransaction();
		
		String hql="SELECT m.listaDeCartasDelMazo FROM Mazo m WHERE m.id=" + m.getId();
		Query query2 = sesion.createQuery(hql);
		// ejecutamos la consulta
		// como nos da una lista de lista solo queremos una
		List<Carta> resul = (List<Carta>) query2.list();
		
		// Metemos la lista en el Mazo
		m.añadir(resul);
		sf.getCurrentSession().getTransaction().commit();
		
	}
	
	
	
	

}
