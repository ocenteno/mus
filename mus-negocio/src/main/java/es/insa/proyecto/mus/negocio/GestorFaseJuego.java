package es.insa.proyecto.mus.negocio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	public int reparte(Jugador j) {
		//Por cada jugador se recupera (get) sus cartas (de las que se ha descartado) 
		for (Jugador jugador : descarteMus.keySet()) {
			Carta[] numDescartes = descarteMus.get(jugador);
			if (numDescartes != null && numDescartes.length > 0) {
				crupier.repartirCartas(numDescartes.length, jugador);
				actualizarTurnoJuego();
				return numDescartes.length;
			}
		}
		return -1;
	}
	
	@Override
	public void inicializar(){
		this.mano = partida.getMano();
		this.turno = mano;
		this.crupier.inicializarMazo();
		this.crupier.barajar();
		// Reparto de 4 cartas a cada jugador de una en una
		repartirDeUnaEnUna();
	}

	public void actualizarTurnoJuego() {
		turno = (turno+1)%4;
	}

	@Override
	public int getTurnoJuego() {
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

	private void repartirDeUnaEnUna() {
		Jugador[] jugadores = this.partida.getMesa();
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++) {
				this.crupier.repartirCartas(1, jugadores[(mano + i) % 4]);
			}
		}
	}

	public void setPartida(Partida partidaActual) {
		this.partida = partidaActual;
		this.turno = partidaActual.getMano();
	}
	
	public void setCrupier(ICrupier crupier) {
		this.crupier = crupier;
	}

}
