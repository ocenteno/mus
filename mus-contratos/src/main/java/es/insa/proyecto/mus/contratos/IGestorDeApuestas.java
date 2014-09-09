package es.insa.proyecto.mus.contratos;

public interface IGestorDeApuestas {

	
	/**
	 * Este método se llama cuando el jugador acepta la apuesta y añades
	 * tu propia apuesta.
	 * @param recibe las piedras apostadas y el lance, o sea, grandes, chicas... 
	 */
	public abstract void subir(int piedras, String apuesta);

	
	/**
	 * Este método se llama cuando el jugador no quiere la apuesta, si el
	 * bote es cero quiere decir que todos han pasado, por lo que se pone
	 * una piedra en el bote, si no se devuelve lo que tenga el bote sin 
	 * sumarle la última apuesta que no se ha aceptado. 
	 * @param recibe el lance.
	 * @return devuelve el bote que había antes de la última apuesta.
	 */
	public abstract int noQuiero(String apuesta);

	
	/**
	 * Este método acepta la apuesta hecha y suma al bote la última apuesta.
	 * @param recibe el lance.
	 * @return devuelve el bote.
	 */
	public abstract int quiero(String apuesta);

	
	/**
	 * Este método suma 40 a la última apuesta, pero no la suma al bote
	 * hasta que no se acepte en el método "quiero".
	 * @param recibe el lance. 
	 */
	public abstract void ordago(String apuesta);

}