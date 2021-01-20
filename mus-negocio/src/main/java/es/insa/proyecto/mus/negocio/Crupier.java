package es.insa.proyecto.mus.negocio;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Mazo;
import es.insa.proyecto.mus.contratos.DaoMazo;
import es.insa.proyecto.mus.contratos.ICrupier;


/**
 * Gestor que implementa la interface que se encarga de las
 * operaciones del mazo de reparto y del mazo de
 * descartes. 
 * @author José Antonio y Cristina
 *
 */
public class Crupier implements ICrupier{
	
	
	private DaoMazo daoMazo;
	private Mazo mazoDeReparto;
	private Mazo mazoDeDescartes;

	public void setDaoMazo(DaoMazo daoMazo) {
		this.daoMazo = daoMazo;
	}

	@Override
	public void barajar() {
		mazoDeReparto.barajar();	
	}

	@Override
	public void inicializarMazo() {
		mazoDeReparto = daoMazo.buscar(1);
		daoMazo.llenarMazo(mazoDeReparto);
		mazoDeDescartes = new Mazo();
		
	}

	@Override
	public void repartirCartas(int numCartas, Jugador j) {
		for (int i=0; i<numCartas ; i++) {
			Carta carta = mazoDeReparto.sacarCarta();
			if (carta == null){
				recogerDescartes();
				carta = mazoDeReparto.sacarCarta();
			}
			j.añadirCarta(carta);
		}
	}

	@Override
	public void descartarCartas(Jugador j, Carta... cartasADescartar) {
		for (Carta c : cartasADescartar) {
			j.quitarCarta(c);
			mazoDeDescartes.añadir(c);
		}
		
		
	}

	@Override
	public void recogerDescartes() {
		Mazo temporal = mazoDeReparto;
		mazoDeReparto = mazoDeDescartes;
		mazoDeDescartes = temporal;
		barajar();
		
	}


}
