package es.insa.proyecto.mus.modelo;

import java.util.Arrays;
import java.util.Random;
import java.util.RandomAccess;


/**
 * La clase Partida contiene información sobre la mesa y las parejas que la forman y 
 * controla quien es mano en cada ronda.
 * @author Eugenia Blanco y M.Angeles Pascual
 *
 */
public class Partida {

	private Object[] mesa;
	private Pareja pareja1;
	private Pareja pareja2;
	private int mano;
	
	public Partida(){
		this.mesa = new Object[4];
	}
	
	public Object[] getMesa() {
		return mesa;
	}

	public void setMesa(Object[] mesa) {
		this.mesa = mesa;
	}

	public Pareja getPareja1() {
		return pareja1;
	}

	public void setPareja1(Pareja pareja1) {
		this.pareja1 = pareja1;
	}

	public Pareja getPareja2() {
		return pareja2;
	}

	public void setPareja2(Pareja pareja2) {
		this.pareja2 = pareja2;
	}

	public int getMano() {
		return mano;
	}

	public void setMano(int mano) {
		this.mano = mano;
	}

	
	// ZONA DE MÉTODOS
	
	/**
	 * Resetea las piedras ganadas de las dos parejas
	 */
	public void nuevoJuego(){
		pareja1.nuevoJuego();
		pareja2.nuevoJuego();
		
	}
	
	/**
	 * Podemos empezar partida si tenemos los cuatro jugadores 
	 * sentados a la mesa, esto implica crear las parejas, asignar quien 
	 * es mano y inicializar los puntos.
	 * @return false si hay alguna silla vacia, o sea el array 
	 * 			de mesa tiene celdas vacias.
	 * 			y true si podemos empezar la partida.
	 */
	public boolean empezarPartida(){
		boolean empezar = true;
		for (int i = 0; i < mesa.length; i++) {
			if(mesa[i] == null){
				empezar = false;
			}
		}
		if (empezar){
			// Creamos las parejas
			pareja1 = new Pareja(mesa[0], mesa[2]);
			pareja2 = new Pareja(mesa[1], mesa[3]);

			// Asignamos la mano aleatoriamente
			Random r = new Random();
			mano = r.nextInt(4);
			 
			// Inicializamos los puntos de cada pareja
			pareja1.nuevoJuego();
			pareja2.nuevoJuego();
		}
		return empezar;
	}
	
	
	/**
	 * Despues de cada juego la mano avanza
	 * @return la posición del array mesa, donde está sentada la mano
	 */
	public int cambiarMano(){
		if (mano >= 3){
			mano = 0;
		}else {
			mano +=1;
		}
		return mano;
		
	}
	
	
	/**
	 * Este método va colocando los jugadores en la mesa
	 * @param jugador el que se quiere sentar en esta partida
	 * @param silla, hay cuatro sillas, de la 0 a la 3
	 * @return true si se ha podido sentar en esa posición,
	 * 			false si la silla estaba ocupada
	 */
	public boolean sentarJugador (Object jugador, int silla){
		if(mesa[silla] == null){
			mesa[silla] = jugador;
			return true;
		}
		return false;
	}

	
	
	
	@Override
	public String toString() {
		return "Partida [pareja1=" + pareja1 + ", pareja2=" + pareja2
				+ ", mano=" + mano + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mano;
		result = prime * result + Arrays.hashCode(mesa);
		result = prime * result + ((pareja1 == null) ? 0 : pareja1.hashCode());
		result = prime * result + ((pareja2 == null) ? 0 : pareja2.hashCode());
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
		Partida other = (Partida) obj;
		if (mano != other.mano)
			return false;
		if (!Arrays.equals(mesa, other.mesa))
			return false;
		if (pareja1 == null) {
			if (other.pareja1 != null)
				return false;
		} else if (!pareja1.equals(other.pareja1))
			return false;
		if (pareja2 == null) {
			if (other.pareja2 != null)
				return false;
		} else if (!pareja2.equals(other.pareja2))
			return false;
		return true;
	}

	
	
	
	
}
