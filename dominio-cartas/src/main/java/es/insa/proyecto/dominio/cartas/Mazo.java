package es.insa.proyecto.dominio.cartas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Mazo de cartas de mus de la baraja española
 * 
 * @author insa05
 * 
 */
public class Mazo {

	private int id;
	private String nombre;
	private List<Carta> listaDeCartasDelMazo;

	/**
	 * Constructor vacío de la clase Mazo
	 */
	public Mazo() {
       listaDeCartasDelMazo = new LinkedList<Carta>();
	}

	/**
	 * Constructor con parámetros
	 * @param nombre
	 */
	public Mazo(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Cambiamos el orden de la lista de cartas del mazo
	 */
	public void barajar() {

		Collections.shuffle(listaDeCartasDelMazo);
	}

	/**
	 * Devuelve la carta de arriba del mazo
	 * 
	 * @return la carta que hemos sacado del mazo
	 * 
	 */
	public Carta sacarCarta() {
		if (listaDeCartasDelMazo.size()> 0){
		// 1º queremos devolver la primera carta de la lista
		// y quitarla de la lista
		Carta cartaADevolver = listaDeCartasDelMazo.remove(0);
		// 2º devolverla
		return cartaADevolver;
		}else {
			return null;
		}
	}
	

	/**
	 * Añadimos una carta al mazo
	 * 
	 * @param c
	 */
	public void añadir(Carta c) {

		// 1º queremos añadir una carta de la listaDeCartas
		listaDeCartasDelMazo.add(c);
	}

	/**
	 * Cargamos el mazo desde una lista de cartas
	 * 
	 * @param l
	 */
	public void añadir(List<Carta> l) {

		// 1º queremos añadir una carta de la listaDeCartas
		listaDeCartasDelMazo.addAll(l);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime
				* result
				+ ((listaDeCartasDelMazo == null) ? 0 : listaDeCartasDelMazo
						.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Mazo other = (Mazo) obj;
		if (id != other.id)
			return false;
		if (listaDeCartasDelMazo == null) {
			if (other.listaDeCartasDelMazo != null)
				return false;
		} else if (!listaDeCartasDelMazo.equals(other.listaDeCartasDelMazo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mazo [id=" + id + ", nombre=" + nombre
				+ ", listaDeCartasDelMazo=" + listaDeCartasDelMazo + "]";
	}

}
