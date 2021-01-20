package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.AccionesLance;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.modelo.Lances;

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
	public Lances getFase();
	
	/**
	 * Recuperamos las posibles acciones (Paso, Envido, Quiero, etc....)
	 * que el jugador puede realizar en cada fase.
	 * @return AccionesLance (Array del Enumerado de acciones del lance)
	 */
	public AccionesLance[] getAcciones();
		
	/**
	 * Intenta ejecutar una acción en fase de GRANDE.
	 * @param jugador jugador que tiene el turno
	 * @param accion Enumerado con la accion realizada por dicho jugador
	 * @param piedras piedras apostadas
	 * @return boolean true si se ha podido realizar, false en caso contrario
	 */
	public boolean faseGrande(Jugador jugador, AccionesLance accion, int piedras);
	
	/**
	 * Intenta ejecutar una acción en fase de CHICA.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean faseChica(Jugador j, AccionesLance a, int p);
	
	/**
	 * Intenta ejecutar una acción en fase de PARES.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean fasePares(Jugador j, AccionesLance a, int p);
	
	/**
	 * Intenta ejecutar una acción en fase de JUEGO.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean faseJuego(Jugador j, AccionesLance a, int p);
	
	/**
	 * Intenta ejecutar una acción en fase de PUNTO.
	 * @param j (jugador que tiene el turno)
	 * @param a (Enumerado con la accion realizada por dicho jugador)
	 * @param p (piedras apostadas)
	 * @return boolean (true si se ha podido realizar)
	 */
	public boolean fasePunto(Jugador j, AccionesLance a, int p);

	/**
	 * Intenta ejecutar una acción que incluye una apuesta en una fase dada
	 * @param jugador jugador que intenta ejecutar la acción
	 * @param accion Accion realizada por dicho jugador
	 * @param piedras piedras apostadas
	 * @return boolean true si se ha podido realizar, false en caso contrario
	 */
	public boolean ejecutar(Jugador jugador, Lances fase, AccionesLance accion, int piedras);
	
	/**
	 * Intenta ejecutar una acción en una fase dada
	 * @param jugador jugador que intenta ejecutar la acción
	 * @param accion Accion realizada por dicho jugador
	 * @return boolean true si se ha podido realizar, false en caso contrario
	 */
	public boolean ejecutar(Jugador jugador, Lances fase, AccionesLance accion);
	
	/**
	 * Recupera la posición de la mesa del jugador al que le toca hablar.
	 * @return Índice del jugador que actualmente tiene el turno
	 */
	int getTurno();
	

}
