package es.insa.proyecto.mus.negocio;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JComboBox.KeySelectionManager;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.FasesJuego;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.ICrupier;
import es.insa.proyecto.mus.contratos.IGestorFaseJuego;
import es.insa.proyecto.mus.modelo.Partida;

/**
 * Esta interface se encarga de controlar si hay o
 * no mus, de controlar el descarte y de controlar 
 * el reparto de cartas.
 * 
 * @author Jose y Cristina
 *
 */
public class GestorFaseJuego implements IGestorFaseJuego{
	
	private Set<Jugador> pideMus;
	private int cortaMus;
	private Map<Jugador, Carta[]> descarteMus;
	private Partida partida;
	private int siguiente;
	private int mano;
	private int manoJuego;
	private ICrupier crupier;

	
	
	public GestorFaseJuego() {
		partida = new Partida();
		pideMus = new HashSet<Jugador>();
		cortaMus = 0;
		descarteMus = new HashMap<Jugador, Carta[]>();
		recuperarMano();
	}

	@Override
	public boolean pedirMus(Jugador j) {
		
		if (pideMus.size()< 4 && cortaMus==0) {
			pideMus.add(j); 
			return true;
		}
		return false;
	}

	@Override
	public boolean cortarMus(Jugador j) {
		if (pideMus.size()==4) {
			return false;
		}else {if (cortaMus==0) {
			cortaMus = +1;
			return true;
			}
		}
		return false;
	}

	/**
	 * Pide descartar un conjunto de cartas de la mano de un jugador
	 * @param j Jugador del que descartar
	 * @param cartas Cartas a descartar de la mano de ese jugador
	 * @return true si se ha podido descartar, false en caso contrario
	 */
	@Override
	public boolean pedirDescarte(Jugador j, Carta... cartas) {
		// Sólo se puede descartar si han pedido mus los 4
		if (pideMus.size() == 4) {
			// Sólo permitimos descartar las cartas si las tiene en la mano
			if(j.tieneEnMano(cartas)){
				// Añadimos al descarte sólo si no se ha intentado descartar antes
				if(descarteMus.containsKey(j)){
					return false;
				}else{
					descarteMus.put(j, cartas);
					return true;
				}
			}
			return false;
		}
		// Si no han pedido mus todos, no se permite
		return false;
	}
	
	@Override
	public boolean ejecutarDescartar() {
		// Sólo se puede ejecutar el descarte si los 4 han pedido descartes
		if (descarteMus.size() == 4) {
			for (Jugador jugador : descarteMus.keySet()) {
				Carta[]descartes = descarteMus.get(jugador);
				crupier.descartarCartas(jugador, descartes);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean reparte(Jugador j) {
		//Por cada jugador se recupera (get) sus cartas (de las que se ha descartado) 
		for (Jugador jugador : descarteMus.keySet()) {
			Carta[] numDescartes = descarteMus.get(jugador);
			crupier.repartirCartas(numDescartes.length, jugador);
		}
		return false;
	}

	public void recuperarMano() {
		manoJuego = partida.getMano();
		mano = manoJuego;
		siguiente = manoJuego;
		
	}

	@Override
	public int turnoJuego() {
	
		mano = siguiente;
		if (mano == 3){
		siguiente = 0;
		}else {
			siguiente += 1;
		}
		return mano;
	}

	@Override
	public FasesJuego faseJuego() {
		if (pideMus.size() == 4) {
			return FasesJuego.DESCARTE;
		} else {
			if (cortaMus == 0) {
				return FasesJuego.MUS;
			} else {
				return FasesJuego.GRANDE;
			}
		}
	}

	public ICrupier getCrupier() {
		return crupier;
	}

	public void setCrupier(ICrupier crupier) {
		this.crupier = crupier;
	}

}
