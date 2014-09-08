package es.insa.proyecto.mus.persistencia;

import java.util.List;

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
	}

	@Override
	public void insertar(Carta c) {
		
	}

	@Override
	public void actualizar(Carta c) {
		
	}

	@Override
	public void eliminar(Carta c) {
		
	}

	@Override
	public Carta buscar(Integer id) {
		return null;
	}

	@Override
	public List<Carta> listarTodos() {
		return null;
	}

}
