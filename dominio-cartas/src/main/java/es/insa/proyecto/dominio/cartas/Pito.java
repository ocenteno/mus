package es.insa.proyecto.dominio.cartas;

/**
 * 
 * @author Herminio Acedo y Jose Antonio Torre
 *
 */
public class Pito extends Carta {
	
	
	
	public Pito() {
	}


	public Pito(Palo palo, int numero, int valor) {
		super(palo, numero, valor);
	}
	

	/**
	 * Compara un Pito con otro Pito y con otra Carta, si esta
	 * carta es un 1 son iguales.
	 * @param o objeto a comparar
	 * @return true si son iguales
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pito){
			return true;
		}
		else if (o instanceof Carta){
			Carta otra = (Carta) o;
			return otra != null && otra.getNumero() == 1;
		}
		return false;
	}
	   
	
	/**
	 * Compara el Pito contra otra Carta
	 * @param otra cualquier otra carta
	 * @return -1 porque el Pito es menor que cualquier otra carta
	 * 			0 si la otra carta es un 1 (son iguales).
	 */
	@Override
	public int compareTo(Carta otra){
		if (otra instanceof Pito){
			return compareTo((Pito) otra);
		}
		if (otra.getNumero() == 1){
			return 0;
		}
		return -1;
	}
	
	
	/**
	 * Compara el Pito contra otro Pito
	 * @param otra cualquier otro Pito
	 * @return 0 porque dos Pitos son siempre iguales 
	 */
	public int compareTo(Pito otra){
		return 0;
	}
}
