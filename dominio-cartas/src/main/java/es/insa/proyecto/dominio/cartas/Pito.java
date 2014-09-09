package es.insa.proyecto.dominio.cartas;

/**
 * 
 * @author Herminio Acedo y Jose Antonio Torre
 *
 */
public class Pito extends Carta{
	
	public Pito(Palo palo, int numero, int valor) {
		super(palo, numero, valor);
	}
	

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
	
	
	/**
	 * Compara el Pito contra otra Carta
	 * @param cualquier otra carta
	 * @return -1 porque el Pito es menor que cualquier otra carta
	 */
	@Override
	public int compareTo(Carta otra){
		return -1;
	}
	
	
	/**
	 * Compara el Pito contra otro Pito
	 * @param  cualquier otro Pito
	 * @return 0 porque dos Pitos son siempre iguales 
	 */
	public int compareTo(Pito otra){
		return 0;
	}
}
