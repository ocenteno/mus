package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.mus.modelo.Lances;

public interface IGestorDeApuestas {

	
	/**
	 * Este método se llama cuando el jugador acepta la apuesta y añade
	 * su propia apuesta.
	 * @param piedras las piedras apostadas
	 * @param apuesta el lance, o sea, grandes, chicas... 
	 */
	public abstract void apostar(int piedras, Lances apuesta);

	
	/**
	 * Este método se llama cuando el jugador no quiere la apuesta, si el
	 * bote es cero quiere decir que todos han pasado, por lo que se pone
	 * una piedra en el bote, si no se devuelve lo que tenga el bote sin 
	 * sumarle la última apuesta que no se ha aceptado. 
	 * @param apuesta recibe el lance.
	 * @return devuelve el bote que había antes de la última apuesta.
	 */
	public abstract int noQuiero(Lances apuesta);

	
	/**
	 * Este método acepta la apuesta hecha y suma al bote la última apuesta.
	 * @param apuesta recibe el lance.
	 * @return devuelve el bote.
	 */
	public abstract int quiero(Lances apuesta);

	
	/**
	 * Este método suma 40 a la última apuesta, pero no la suma al bote
	 * hasta que no se acepte en el método "quiero".
	 * @param apuesta recibe el lance. 
	 */
	public abstract void ordago(Lances apuesta);

	
	/**
	 * Este método se llama cuando el jugador acepta la apuesta y apuesta
	 * 2 piedras.
	 * @param apuesta recibe el lance, o sea, grandes, chicas... 
	 */
	public abstract void envido(Lances apuesta);

	
	/**
	 * Este método devuelve el bote del lance que entra por parámetro
	 * @param apuesta el lance del que se quiere el bote apostado
	 * @return el bote de ese lance 
	 */
	public abstract int getApuestas(Lances apuesta);


	


	


	


	

}