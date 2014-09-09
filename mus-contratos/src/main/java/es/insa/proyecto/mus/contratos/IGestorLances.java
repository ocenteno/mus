package es.insa.proyecto.mus.contratos;

import es.insa.proyecto.dominio.cartas.Jugador;

public interface IGestorLances {

	Jugador ganadorLanceGrande(Jugador... jugadores);
}
