package es.insa.proyecto.dominio.cartas;

/**
 * 
 * @author Herminio Acedo y Jose Antonio Torre
 *
 */
public class Carta implements Comparable<Carta> {

	
	/**
	 * identificador de la carta
	 */
    private int id;
    
	/**
     *  Enum donde se definen los palos (OROS, COPAS, ESPADAS, BASTOS)  
     */
	private Palo palo;
	
	/**
	 * número de la carta
	 */
	private int numero;	
	
	/**
	 * valor de la carta
	 */
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
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Carta [id=" + id + ", palo=" + palo + ", numero=" + numero
				+ ", valor=" + valor + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Carta){
			Carta otra = (Carta) obj;
			return otra != null && this.numero == otra.numero;
		}else if (obj instanceof Pito){
			Pito otra = (Pito) obj;
			return otra != null && this.numero == 1;
		}else if (obj instanceof Gocho){
			Gocho otra = (Gocho) obj;
			return otra != null && this.numero == 12;
		}
		return false;
	}

	/**
	 * Se sobreescribe el método compareTo para que si comparamos
	 * nuestra Carta con otra Carta,
	 * se comparan por número, siendo menor la que tiene menor número
	 * @param otro es la Carta con el que queremos comparar nuestra Carta
	 * @return 0 si son iguales
	 * 		  -1 si es menor
	 * 		   1 si es mayor
	 */
	@Override
	public int compareTo(Carta otra) {
		if (otra instanceof Gocho){
			return compareTo((Gocho) otra);
		}
		if (otra instanceof Pito){
			return compareTo((Pito) otra);
		}
		return Integer.compare(this.numero, otra.numero);
	}	
	
	/**
	 * Si comparamos nuestra Carta con un Gocho, 
	 * 	- si nuestra carta es un 12 será igual que el Gocho
	 * 	- para cualquier otra Carta, nuestra carta será menor que el Gocho
	 * @param otro es el Gocho con el que queremos comparar nuestra Carta
	 * @return 0 si son iguales
	 * 		  -1 en cualquier otro caso
	 */
	public int compareTo(Gocho otra){
		if (this.numero == 12){
			return 0;
		}
		return -1;
	}
		
	/**
	 * Si comparamos nuestra Carta con un Pito,
	 * 	- si nuestra Carta es un 1 será igual que el Pito
	 * 	- para cualquier otra Carta, nuestra carta será mayor que el Pito
	 * @param otro es el Pito con el que queremos comparar nuestra Carta
	 * @return 0 si son iguales
	 * 		   1 en cualquier otro caso
	 */
	public int compareTo (Pito otra){
		if (this.numero == 1){
			return 0;
		}
		return 1;
	}
	
	

}
