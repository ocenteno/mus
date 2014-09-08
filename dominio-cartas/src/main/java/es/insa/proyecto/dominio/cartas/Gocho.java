package es.insa.proyecto.dominio.cartas;
/**
 * 
 * @author insa05
 *
 */
public class Gocho extends Carta{
	/**
	 * Comparar una carta para saber si es Gocho
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Gocho) {
			return true;		
		}
		return false;
	}

}

