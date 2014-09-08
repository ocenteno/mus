package es.insa.proyecto.mus.contratos;

import java.util.List;

/**
 * Insertar, actualizar, eliminar, buscar y
 * ListarTodos.
 * @author insa06
 *
 * @param <G>
 * @param <K>
 */

public interface DAO<G, K> {
	
	public abstract void insertar(G c);
	
	public abstract void actualizar(G c);
	
	public abstract void eliminar(G c);
	
	public abstract G buscar (K id);
	
	public abstract List<G> listarTodos();
	

}
