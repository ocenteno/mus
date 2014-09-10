package es.insa.proyecto.mus.modelo;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGestorLances;

public class Grande extends Lance{
	
	private IGestorLances gestorLances;

	public Grande() {
		
	}

	@Override
	public Jugador ganador(Jugador... jugadores) {
		// jugador1, jugador2....
		
		// Ordenar los jugadores a partirt de la Mano
		Jugador[] ordenJugadores = gestorLances.ordenJugadoresSegunMano(jugadores);
		// Para cada jugador
		for (Jugador jugador : ordenJugadores) {
			// Ordenar las cartas de la mano de cada uno de los jugadores de entrada
			jugador.ordenarMano(jugador);
		}
		
		// Guardar cada jugador con su mano
		boolean ganadorTemporal = quienTieneCartaMasAlta(jugadores[0].getMano(), jugadores[1].getMano());
		
		// GANA EL JUGADOR 1
		if (ganadorTemporal) {
			// COMPARA 1 CON 3
			ganadorTemporal = quienTieneCartaMasAlta(jugadores[0].getMano(),
					jugadores[2].getMano());
			// GANA EL JUGADOR 1
			if (ganadorTemporal) {
				// COMPARA 1 CON 4
				ganadorTemporal = quienTieneCartaMasAlta(
						jugadores[0].getMano(), jugadores[3].getMano());
				if (ganadorTemporal) {
					return jugadores[0];
				} else
					return jugadores[3];
				// GANA EL JUGADOR 3
			} else {
				// COMPARA 3 CON 4
				ganadorTemporal = quienTieneCartaMasAlta(
						jugadores[2].getMano(), jugadores[3].getMano());
				// GANA EL JUGADOR 3
				if (ganadorTemporal) {
					return jugadores[2];
					// GANA EL JUGADOR 4
				} else
					return jugadores[3];
			}
			// GANA EL JUGADOR 2
		} else {
			// COMPARA 2 CON 3
			ganadorTemporal = quienTieneCartaMasAlta(jugadores[1].getMano(),
					jugadores[2].getMano());
			// GANA EL JUGADOR 2
			if (ganadorTemporal) {
				// COMPARA 2 CON 4
				ganadorTemporal = quienTieneCartaMasAlta(
						jugadores[1].getMano(), jugadores[3].getMano());
				// GANA EL JUGADOR 2
				if (ganadorTemporal) {
					return jugadores[1];
				} else {
					// COMPARA 1 CON 3
					ganadorTemporal = quienTieneCartaMasAlta(
							jugadores[2].getMano(), jugadores[3].getMano());
					// GANA EL JUGADOR 3
					if (ganadorTemporal) {
						return jugadores[2];
						// GANA EL JUGADOR 4
					} else
						return jugadores[3];
				}
				// GANA EL JUGADOR 3
			} else {
				// COMPARA 3 CON 4
				ganadorTemporal = quienTieneCartaMasAlta(
						jugadores[2].getMano(), jugadores[3].getMano());
				// GANA EL JUGADOR 3
				if (ganadorTemporal) {
					return jugadores[2];
					// GANA EL JUGADOR 4
				} else
					return jugadores[3];
			}
		}
	}
	
	private boolean quienTieneCartaMasAlta(Carta[] mano1, Carta[] mano2) {
			   
        for (int i = 0; i < 4; i++) {

            Carta cartaJugadorUno = mano1[i];
            Carta cartaJugadorDos = mano2[i];

            if (cartaJugadorUno.getNumero() > cartaJugadorDos.getNumero()) {
                return true;
            } else if (cartaJugadorUno.getNumero() < cartaJugadorDos.getNumero()) {
                return false;
            }
        }
        return true;
	}

	/**
	 * @param gestorLances the gestorLances to set
	 */
	public void setGestorLances(IGestorLances gestorLances) {
		this.gestorLances = gestorLances;
	}
	

}
