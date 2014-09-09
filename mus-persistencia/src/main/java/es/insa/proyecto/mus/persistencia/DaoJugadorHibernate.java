package es.insa.proyecto.mus.persistencia;

import java.util.List;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.DaoJugador;

/**
 * Inserta, actualiza, elimina, busca 
 * y lista todos los jugadores.
 * @author Nazaret y Jose Antonio
 *
 */
public class DaoJugadorHibernate  extends DaoGenericoHibernate<Jugador, Integer>
	implements DaoJugador{

	public DaoJugadorHibernate() {
		super();
	}

	

	

}
