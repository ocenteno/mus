package es.insa.proyecto.dominio.cartas;

public class Carta {

    private int id;
	private Palo palo;
	private int numero;	
	private int valor;
	
	public Carta() {
	
	}	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		result = prime * result + ((palo == null) ? 0 : palo.hashCode());
		result = prime * result + valor;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		if (numero != other.numero)
			return false;
		if (palo != other.palo)
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}

	
	

}
