package es.insa.proyecto.mus.persistencia;

import es.insa.proyecto.mus.contratos.DaoPartida;
import es.insa.proyecto.mus.modelo.Partida;

/**
 * Inserta, actualiza, elimina, busca 
 * y lista todas las partidas.
 * @author Nazaret y Jose Antonio
 *
 */
public class DaoPartidaHibernate extends DaoGenericoHibernate<Partida, Integer>
			implements DaoPartida{

	public DaoPartidaHibernate() {
		super();
	}

	

}
