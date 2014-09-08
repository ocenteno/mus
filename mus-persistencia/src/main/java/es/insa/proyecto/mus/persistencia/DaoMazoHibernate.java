package es.insa.proyecto.mus.persistencia;

import java.util.List;

import org.hibernate.Session;

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

	public DaoMazoHibernate() {
	}

	@Override
	public void insertar(Mazo c) {
		
	}

	@Override
	public void actualizar(Mazo c) {
		
	}

	@Override
	public void eliminar(Mazo c) {
		
	}

	@Override
	public Mazo buscar(Integer id) {
		return null;
	}

	@Override
	public List<Mazo> listarTodos() {
		return null;
	}
	
	/**
	 * Llena el mazo con las cartas. 
	 *
	 */
	public Mazo llenarMazo(Integer id){
				
		return null;
		
	}

}
