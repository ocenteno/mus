package es.insa.proyecto.mus.negocio;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.contratos.IGestorDeApuestas;
import es.insa.proyecto.mus.contratos.IGestorTanteoParcial;
import es.insa.proyecto.mus.modelo.Lances;
import es.insa.proyecto.mus.modelo.Pareja;
import es.insa.proyecto.mus.modelo.Partida;

public class GestorTanteoParcial implements IGestorTanteoParcial{
	
	private GanadorGrande ganadorGrande;
	private GanadorChica ganadorChica;
	private GanadorPares ganadorPares;
	private GanadorJuego ganadorJuego;
	private GanadorPunto ganadorPunto;
	private Partida partida;
	private IGestorDeApuestas gestorApuestas;

	public GestorTanteoParcial() {
	}

	@Override
	public void sacarPiedras(int turno, int piedras) {
		sumarPiedras(partida.getMesa()[turno], piedras);
	}
	
	@Override
	public Jugador sacarPiedrasLance(Lances lance) {
		Jugador jugador = null;
		switch (lance) {
			case GRANDE:
				jugador = ganadorGrande.ganador(partida.getMesa());
				break;
			case CHICA:
				jugador = ganadorChica.ganador(partida.getMesa());
				break;
			case PARES:
				jugador = ganadorPares.ganador(partida.getMesa());
				break;
			case JUEGO:
				jugador = ganadorJuego.ganador(partida.getMesa());
				break;
			case PUNTO:
				jugador = ganadorPunto.ganador(partida.getMesa());
				break;
			default:
				break;
		}
		int boteLance = gestorApuestas.getApuestas(lance);
		sumarPiedras(jugador, boteLance);
		
		return (boteLance == 0) ? null : jugador;
		
	}

	private void sumarPiedras(Jugador jugador, int piedras) {
		if (partida.getPareja1().comprobarPertenece(jugador)){
			partida.sumarPuntosPareja1(piedras);
		}else{
			partida.sumarPuntosPareja2(piedras);
		}
	}

	public void setGanadorGrande(GanadorGrande ganadorGrande) {
		this.ganadorGrande = ganadorGrande;
	}

	public void setGanadorChica(GanadorChica ganadorChica) {
		this.ganadorChica = ganadorChica;
	}

	public void setGanadorPares(GanadorPares ganadorPares) {
		this.ganadorPares = ganadorPares;
	}

	public void setGanadorJuego(GanadorJuego ganadorJuego) {
		this.ganadorJuego = ganadorJuego;
	}

	public void setGanadorPunto(GanadorPunto ganadorPunto) {
		this.ganadorPunto = ganadorPunto;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public void setGestorApuestas(IGestorDeApuestas gestorApuestas) {
		this.gestorApuestas = gestorApuestas;
	}
	
}
