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
	
	@Override
	public int compareTo(Carta otra){
		return -1;
	}
	
	public int compareTo(Pito otra){
		return 0;
	}
}
