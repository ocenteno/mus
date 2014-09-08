package es.insa.proyecto.dominio.cartas;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author insa15
 *
 */
public class Jugador {

	// Se debe sustituir Object por Carta una vez exista la clase
	private String nombre;
	private Set<Object> manoJugador;
	
	public Jugador() {
	}

		public Jugador(String nombre) {
			super();
			this.nombre = nombre;
			this.manoJugador = new HashSet<Object>();
		}
		
		public Jugador(String nombre, Set<Object> manoJugador) {
			super();
			this.nombre = nombre;
			this.manoJugador = manoJugador;
		}
		
		// Se debe sustituir Object por Carta una vez exista la clase
		public void añadirCarta(Object cartaAAñadir){
			this.manoJugador.add(cartaAAñadir);
		}
	
		// Se debe sustituir Object por Carta una vez exista la clase
		public void quitarCarta(Object cartaAQuitar){
			this.manoJugador.remove(cartaAQuitar);
		}
	
		// Se debe sustituir Object por Carta una vez exista la clase
		/**
		 * La mano del jugador la guardamos como un Set pero la devolvemos como un Array de Cartas
		 * @return devuelve un Array de Carta
		 */
		public Object[] getMano(){
			return this.manoJugador.toArray(new Object[manoJugador.size()]);
			
		}
	
		public String getNombre() {
			return nombre;
		}
	
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	
}
