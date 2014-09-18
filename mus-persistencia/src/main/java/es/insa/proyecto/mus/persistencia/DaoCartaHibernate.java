package es.insa.proyecto.mus.persistencia;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.mus.contratos.DaoCarta;

/**
 * Inserta, actualiza, elimina, busca 
 * y listaTodas las cartas.
 * @author Nazaret y Jose Antonio
 *
 */
public class DaoCartaHibernate 
	extends DaoGenericoHibernate<Carta, Integer>
	implements DaoCarta {

	public DaoCartaHibernate() {
		super();
	}

	

}
