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
import es.insa.proyecto.dominio.cartas.FaseDescartes;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.ICrupier;
import es.insa.proyecto.mus.contratos.IGestorFaseDescartes;
import es.insa.proyecto.mus.modelo.Partida;

/**
 * Esta interface se encarga de controlar si hay o
 * no mus, de controlar el descarte y de controlar 
 * el reparto de cartas.
 * 
 * @author Jose y Cristina
 *
 */
public class GestorFaseJuego implements IGestorFaseDescartes{
	
	private Set<Jugador> pideMus;
	private int cortaMus;
	private Map<Jugador, Carta[]> descarteMus;
	private Partida partida;
	private int turno;
	private int mano;
	private ICrupier crupier;

	
	
	public GestorFaseJuego() {
		pideMus = new HashSet<Jugador>();
		cortaMus = 0;
		descarteMus = new HashMap<Jugador, Carta[]>();
	}

	@Override
	public boolean pedirMus(Jugador j) {
		
		if (pideMus.size()< 4 && cortaMus==0) {
			pideMus.add(j); 
			actualizarTurnoJuego();
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
			actualizarTurnoJuego();
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
					actualizarTurnoJuego();
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
			actualizarTurnoJuego();
		}
		return false;
	}


	public void actualizarTurnoJuego() {
		turno = (turno+1)%4;
	}

	@Override
	public int turnoJuego() {
		return turno;
	}

	@Override
	public FaseDescartes faseJuego() {
		if (pideMus.size() == 4) {
			return FaseDescartes.DESCARTE;
		} else {
			if (cortaMus == 0) {
				return FaseDescartes.MUS;
			} else {
				return FaseDescartes.GRANDE;
			}
		}
	}

	public void setPartida(Partida partidaActual) {
		this.partida = partidaActual;
		this.mano = partida.getMano();
		this.turno = mano;
	}
	
	public void setCrupier(ICrupier crupier) {
		this.crupier = crupier;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
		recuperarMano();
	}

}
