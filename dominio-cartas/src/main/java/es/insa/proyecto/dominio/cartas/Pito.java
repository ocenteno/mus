package es.insa.proyecto.dominio.cartas;

/**
 * 
 * @author insa05
 *
 */
public class Pito extends Carta{
/**
 * Comparar una carta para saber si es un Pito	
 */
	@Override
	public boolean equals(Object o) {

		if (o instanceof Pito){
			return true;
		}
		return false;
	}
}
