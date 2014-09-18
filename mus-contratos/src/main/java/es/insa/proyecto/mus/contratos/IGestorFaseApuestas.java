package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.AccionesLance;
import es.insa.proyecto.dominio.cartas.FaseDescartes;
import es.insa.proyecto.dominio.cartas.Jugador;

/**
 * Esta interfaces se encarga de la Gestion de las distintas
 * Fases por las que pasa cada Lance de una mano determinada.
 * @author Cristina y José Antonio
 *
 */

public interface IGestorFaseApuestas {
	
	/**
	 * Recuperamos la fase del juego (Grande, Chicas, Pares etc...)
	 * @return FasesJuego (Es un Enum con las distintas fases)
	 */
	public FaseDescartes getFase();
	
	/**
	 * Recuperamos las posibles acciones (Paso, Envido, Quiero, etc....)
	 * que el jugador puede realizar en cada fase.
	 * @return AccionesLance (Array del Enumerado de acciones del lance)
	 */
	public AccionesLance[] getAcciones();
		
	/**
	 * Controlamos el lance de grande. Esto es, todas y cada una
	 * de las acciones que un jugador puede realizar.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean faseGrande(Jugador j, AccionesLance a, int p);
	
	/**
	 * Controlamos el lance de chica. Esto es, todas y cada una
	 * de las acciones que un jugador puede realizar.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean faseChica(Jugador j, AccionesLance a, int p);
	
	/**
	 * Controlamos el lance de pares. Esto es, todas y cada una
	 * de las acciones que un jugador puede realizar.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean fasePares(Jugador j, AccionesLance a, int p);
	
	/**
	 * Controlamos el lance de juego. Esto es, todas y cada una
	 * de las acciones que un jugador puede realizar.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean faseJuego(Jugador j, AccionesLance a, int p);
	
	/**
	 * Controlamos el lance de punto. Esto es, todas y cada una
	 * de las acciones que un jugador puede realizar.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean fasePunto(Jugador j, AccionesLance a, int p);
	

}
