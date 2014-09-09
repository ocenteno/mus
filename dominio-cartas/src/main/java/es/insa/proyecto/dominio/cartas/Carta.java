package es.insa.proyecto.dominio.cartas;

/**
 * 
 * @author insa05
 *
 */
public class Carta implements Comparable<Carta>{

    private int id;
    /**
     *  Se definen valores del enum de la clase Palo  
     */
	private Palo palo;
	private int numero;	
	private int valor;
	
	/**
	 * Constructor vacío
	 */
	public Carta() {
	
	}
	
	/**
	 * Constructor con parámetros
	 * @param palo
	 * @param numero
	 * @param valor
	 */
	public Carta(Palo palo, int numero, int valor) {
		super();
		this.palo = palo;
		this.numero = numero;
		this.valor = valor;
	}

	public Palo getPalo() {
		return palo;
	}

	public void setPalo(Palo palo) {
		this.palo = palo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	
	
	@Override
	public String toString() {
		return "Carta [id=" + id + ", palo=" + palo + ", numero=" + numero
				+ ", valor=" + valor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Carta){
			Carta otra = (Carta) obj;
			return otra != null && this.numero == otra.numero;
		}
		return false;
	}

	
	@Override
	public int compareTo(Carta otra) {
		return Integer.compare(this.numero, otra.numero);
	}
	
	
	
	
	
	

	
	

}
