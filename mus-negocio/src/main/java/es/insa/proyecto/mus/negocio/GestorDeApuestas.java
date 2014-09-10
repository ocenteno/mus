package es.insa.proyecto.mus.negocio;

import java.util.HashMap;
import java.util.Map;

import es.insa.proyecto.mus.contratos.IGestorDeApuestas;
import es.insa.proyecto.mus.modelo.Lances;

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
	private Map<Enum, Integer> apuestas;
	
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
		apuestas = new HashMap<Enum, Integer>();
		apuestas.put(Lances.GRANDE,0);
		apuestas.put(Lances.CHICA,0);
		apuestas.put(Lances.PARES,0);
		apuestas.put(Lances.JUEGO,0);
		apuestas.put(Lances.PUNTO,0);
	}

	
	
	public Map<Enum, Integer> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Map<Enum, Integer> apuestas) {
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
	public void apostar(int piedras, Enum apuesta){
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
	public int noQuiero(Enum apuesta){
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
	public int quiero(Enum apuesta){
		bote = apuestas.get(apuesta);
		bote = bote + ultimaApuesta;
		ultimaApuesta = 0;
		if (bote > 40) {
			bote = 40;
		}
		apuestas.put(apuesta, bote);
		return bote;
	}
	
	/**
	 * Este m�todo se llama cuando el jugador acepta la apuesta y apuesta
	 * 2 piedras.
	 * @param recibe el lance, o sea, grandes, chicas... 
	 */
	@Override
	public void envido(Enum apuesta){
		bote = apuestas.get(apuesta);
		bote = bote + ultimaApuesta;
		apuestas.put(apuesta, bote);
		ultimaApuesta = 2;
	}
	
	
	/**
	 * Este m�todo suma 40 a la �ltima apuesta, pero no la suma al bote
	 * hasta que no se acepte en el m�todo "quiero".
	 * @param recibe el lance. 
	 */
	@Override
	public void ordago(Enum apuesta){
		bote = apuestas.get(apuesta);
		bote = bote + ultimaApuesta;
		apuestas.put(apuesta, bote);
		ultimaApuesta = 40;
	}
	
}
