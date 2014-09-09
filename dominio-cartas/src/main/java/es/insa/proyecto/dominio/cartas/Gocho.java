package es.insa.proyecto.dominio.cartas;
/**
 * 
 * @author insa05
 *
 */
public class Gocho extends Carta{
	
	public Gocho(Palo palo, int numero, int valor) {
		super(palo, numero, valor);
	}
	
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
	
	/**
	 * Compara el Gocho contra otra Carta
	 * @param otra, cualquier otra carta
	 * @return 1 porque el Gocho es mayor que cualquier otra carta
	 */
	@Override
	public int compareTo(Carta otra) {
		return 1;
	}
	
	
	/**
	 * Compara el Gocho contra otro Gocho
	 * @param otra, cualquier otro Gocho
	 * @return 0 porque dos Gochos son iguales 
	 */
	public int compareTo(Gocho otra){
		return 0;
	}
	
}

