package es.insa.proyecto.dominio.cartas;
/**
 * 
 * @author Herminio Acedo y Jose Antonio Torre
 *
 */
public class Gocho extends Carta{
	
	
	public Gocho() {
	}
	
	
	public Gocho(Palo palo, int numero, int valor) {
		super(palo, numero, valor);
	}
	
	/**
	 * Compara una Gocho con otro Gocho y con otra Carta, si esta
	 * carta es un 12 son iguales.
	 * @param o objeto a comparar
	 * @return true si son iguales
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Gocho) {
			return true;		
		}
		else if (o instanceof Carta){
			Carta otra = (Carta) o;
			return otra != null && otra.getNumero() == 12;
		}
		return false;
	}
	 
	
	/**
	 * Compara el Gocho contra otra Carta
	 * @param otra, cualquier otra carta
	 * @return 1 porque el Gocho es mayor que cualquier otra carta
	 * 			0 si la otra carta es un 12 (son iguales)
	 */
	@Override
	public int compareTo(Carta otra) {
		if (otra instanceof Gocho){
			return compareTo((Gocho) otra);
		}
		if (otra.getNumero() == 12){
			return 0;
		}
		
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

