package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Mazo;


public interface DaoMazo extends DAO<Mazo, Integer>{

	public void llenarMazo(Mazo m);
}
