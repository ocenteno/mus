package es.insa.proyecto.mus.contratos.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.modelo.Partida;

public class TestPartida {
	
	Partida p = new Partida();
	Jugador j1 = new Jugador();
	Jugador j2 = new Jugador();
	Jugador j3 = new Jugador();
	Jugador j4 = new Jugador();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	public void testSentarSoloUnJugador() {
		p.sentarJugador(j1, 1);
		boolean respuesta = p.empezarPartida();
		assertEquals("No se puede empezar partida con un solo jugador",
				false, respuesta);
		
	}
	
	@Test
	public void testSentarDosJugadoresMismaSilla() {
		p.sentarJugador(j1, 1);
		boolean respuesta = p.sentarJugador(j2, 1);
		assertEquals("No se pueden sentar dos jugadores en la misma silla",
				false, respuesta);
		
	}
	
	@Test
	public void testComprobarParejasYPuntos() {
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		p.sentarJugador(j4, 3);
		
		p.empezarPartida();

		assertNotNull("La pareja1 no pueden ser nula", p.getPareja1());
		assertNotNull("La pareja2 no pueden ser nula", p.getPareja2());
		
		assertEquals("Los puntos de la pareja1 deben ser cero", 
					0, p.getPareja1().getPiedrasGanadas());
		assertEquals("Los puntos de la pareja2 deben ser cero", 
					0, p.getPareja2().getPiedrasGanadas());
		
	}
	
	@Test
	public void testSumarPuntos() {
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		p.sentarJugador(j4, 3);
		
		p.empezarPartida();
		p.getPareja1().sumarPuntos(7);
		p.getPareja2().sumarPuntos(3);
		p.getPareja1().sumarPuntos(5);
		p.getPareja2().sumarPuntos(10);

		assertEquals("Los puntos de la pareja1 deben ser 12", 
					12, p.getPareja1().getPiedrasGanadas());
		assertEquals("Los puntos de la pareja2 deben ser 13", 
					13, p.getPareja2().getPiedrasGanadas());
		
	}
	
	@Test
	public void testCambiarMano() {
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		p.sentarJugador(j4, 3);
		
		p.empezarPartida();
		int manoInicial = p.getMano();
		Jugador jugadorAntes = p.getMesa()[manoInicial];
		boolean resultadoAntes = p.getPareja1().
					comprobarPertenece(jugadorAntes);
			
		int manoCambiada = p.cambiarMano();
		Jugador jugadorDespues = p.getMesa()[manoCambiada];
		boolean resultadoDespues = p.getPareja1().
					comprobarPertenece(jugadorDespues);

		assertNotEquals("El jugador mano debe haber cambiado", 
				manoInicial, manoCambiada);
		assertNotEquals("La mano tiene que haber cambiado de pareja", 
				resultadoAntes, resultadoDespues);
		
	}
	
	@Test
	public void testCambiarManoCuatroVeces() {
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		p.sentarJugador(j4, 3);
		
		p.empezarPartida();
		int manoInicial = p.getMano();
		Jugador jugadorAntes = p.getMesa()[manoInicial];
		boolean resultadoAntes = p.getPareja1().
					comprobarPertenece(jugadorAntes);
			
		p.cambiarMano();
		p.cambiarMano();
		p.cambiarMano();
		int manoCambiada = p.cambiarMano();
		
		Jugador jugadorDespues = p.getMesa()[manoCambiada];
		boolean resultadoDespues = p.getPareja1().
					comprobarPertenece(jugadorDespues);
		
		assertEquals("El jugador mano debe ser el mismo", 
				manoInicial, manoCambiada);
		assertEquals("La mano no tiene que haber cambiado de pareja", 
				resultadoAntes, resultadoDespues);
		
	}
}
