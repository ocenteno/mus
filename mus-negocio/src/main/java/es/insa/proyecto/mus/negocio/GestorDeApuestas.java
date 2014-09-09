package es.insa.proyecto.mus.negocio;

import java.util.HashMap;
import java.util.Map;

import es.insa.proyecto.mus.contratos.IGestorDeApuestas;

/**
 * 
 * @author Eugenia Blanco y M.Angeles Pascual
 *
 */
public class GestorDeApuestas implements IGestorDeApuestas {
	
	
	/**
	 * Se utiliza un Map para que cada lance tenga su propio bote, para acceder al bote de Chicas
	 * por ejemplo haremos "bote = apuestas.get(apuesta)" siendo apuesta "Chica".
	 */
	private Map<String, Integer> apuestas;
	
	/**
	 * La �ltima apuesta se guarda por si no se acepta, se sumar�
	 * al bote cuando entre en el m�todo "quiero".
	 */
	private int ultimaApuesta;
	
	/**
	 * En el bote se van acumulando las ganancias de cada lance.
	 */
	private int bote;
	
	
	/**
	 * En el constructor se inicializan las claves del mapa de apuestas.
	 */
	public GestorDeApuestas() {
		apuestas = new HashMap<String, Integer>();
		apuestas.put("Grandes",0);
		apuestas.put("Chicas",0);
		apuestas.put("Pares",0);
		apuestas.put("Juego",0);
		apuestas.put("Punto",0);
	}

	
	
	public Map<String, Integer> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Map<String, Integer> apuestas) {
		this.apuestas = apuestas;
	}

	public int getUltimaApuesta() {
		return ultimaApuesta;
	}

	public void setUltimaApuesta(int ultimaApuesta) {
		this.ultimaApuesta = ultimaApuesta;
	}

	public int getBote() {
		return bote;
	}

	public void setBote(int bote) {
		this.bote = bote;
	}


	
	// ZONA DE M�TODOS

	/**
	 * Este m�todo se llama cuando el jugador acepta la apuesta y a�ades
	 * tu propia apuesta.
	 * @param recibe las piedras apostadas y el lance, o sea, grandes, chicas... 
	 */
	@Override
	public void subir(int piedras, String apuesta){
		bote = apuestas.get(apuesta);
		bote = bote + ultimaApuesta;
		apuestas.put(apuesta, bote);
		ultimaApuesta = piedras;
		
	}
	
	
	/**
	 * Este m�todo se llama cuando el jugador no quiere la apuesta, si el
	 * bote es cero quiere decir que todos han pasado, por lo que se pone
	 * una piedra en el bote, si no se devuelve lo que tenga el bote sin 
	 * sumarle la �ltima apuesta que no se ha aceptado. 
	 * @param recibe el lance.
	 * @return devuelve el bote que hab�a antes de la �ltima apuesta.
	 */
	@Override
	public int noQuiero(String apuesta){
		bote = apuestas.get(apuesta);
		if (bote == 0){
			return 1;
		}else{
			return bote;
		}
	}
	
	
	/**
	 * Este m�todo acepta la apuesta hecha y suma al bote la �ltima apuesta.
	 * @param recibe el lance.
	 * @return devuelve el bote.
	 */
	@Override
	public int quiero(String apuesta){
		bote = apuestas.get(apuesta);
		bote = bote + ultimaApuesta;
		ultimaApuesta = 0;
		apuestas.put(apuesta, bote);
		return bote;
	}
	
	
	/**
	 * Este m�todo suma 40 a la �ltima apuesta, pero no la suma al bote
	 * hasta que no se acepte en el m�todo "quiero".
	 * @param recibe el lance. 
	 */
	@Override
	public void ordago(String apuesta){
		bote = apuestas.get(apuesta);
		bote = bote + ultimaApuesta;
		apuestas.put(apuesta, bote);
		ultimaApuesta = 40;
	}
	
}
