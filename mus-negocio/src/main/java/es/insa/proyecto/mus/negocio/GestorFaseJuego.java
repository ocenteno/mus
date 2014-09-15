package es.insa.proyecto.mus.negocio;

import java.util.Arrays;
import java.util.Collections;
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

	@Override
	public boolean descarte(Jugador j, Carta... cartas) {
		if (pideMus.size() == 4) {
			if (descarteMus.size() < 4) {
				descarteMus.put(j, cartas);
				if (descarteMus.size() == 4) {
					for (Jugador jugador : descarteMus.keySet()) {
						Carta[]descartes = descarteMus.get(jugador);
						crupier.descartarCartas(jugador, descartes);
					}
				}
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean reparte(Jugador j, int numCartas) {
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
