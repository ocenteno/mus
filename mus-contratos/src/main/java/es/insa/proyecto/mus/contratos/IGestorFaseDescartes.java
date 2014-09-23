package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.FaseDescartes;
import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * Esta interface se encarga de controlar si hay o
 * no mus, de controlar el descarte y de controlar 
 * el reparto de cartas.
 * @author Jose y Cristina
 * 
 */
public interface IGestorFaseDescartes {
	
	/**
	 * Devuelve el jugador al que le toca jugar respecto
	 * a su posición en la mesa de juego.
	 * @return posición
	 */
	public int getTurnoJuego();

	/**
	 * Devuelve la fase de juego en la que se encuentra la mano
	 * @return Fase del Juego (MUS, DESCARTE, REPARTO, GRANDE)
	 */
	public FaseDescartes faseJuego();
	
	/**
	 * Controla si se puede pedir mus o no.
	 * @param j
	 * @return boolean
	 */
	public boolean pedirMus(Jugador j);
	
	/**
	 * Controla si se ha cortado el mus o no para determinar
	 * si se puede iniciar el descarte.
	 * @param j
	 * @return boolean
	 */
	public boolean cortarMus(Jugador j);
	
	/**
	 * Controla los descartes (nº de cartas y cuales) del jugador
	 * 
	 * @param j
	 * @param cartas
	 * @return boolean
	 */
	public boolean pedirDescarte(Jugador j, Carta... cartas);

	/**
	 * Una vez que todos han solicitado su descarte utiliza la
	 * interface de descartes para que lo haga efectivo.

	 * @return
	 */
	public boolean ejecutarDescartar();
	
	/**
	 * Se encarga de controlar el reparto de cargas teniendo en cuenta los
	 * descartes realizados.
	 * @param j
	 * @return El número de cartas recibidas o -1 en caso de error
	 */
	public int reparte(Jugador j);

	/**
	 * Inicializa los contadores del gestor
	 */
	public void inicializar();
	
}
