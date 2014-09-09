package es.insa.proyecto.mus.persistencia;

import java.util.List;

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
	
	private DaoMazo daoMazo;

	public DaoMazoHibernate() {
		super();
	}

		
	/**
	 * Añade una Carta al mazo
	 * @param m
	 * @param c
	 * @return
	 */
	public Mazo añadirCartaMazo(Mazo m,Carta c){
		
		m.añadir(c);
		// para que se guarde el mazo en la BBDD
		daoMazo.actualizar(m);
		return m;		
		
	}
	/**
	 * Añade una Lista de Cartas en el Mazo
	 * @param m
	 * @param lista
	 * @return
	 */
	public Mazo añadirListaCartasMazo(Mazo m, List<Carta> lista){
		
		m.añadir(lista);
		// para que se guarde el mazo en la BBDD
		daoMazo.actualizar(m);
		
		
		return m;
	}

}
