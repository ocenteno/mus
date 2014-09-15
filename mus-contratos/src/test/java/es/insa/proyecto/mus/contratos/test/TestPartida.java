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
	Jugador j5 = new Jugador();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	/**
	 * comprueba que el jugador que se ha sentado en una silla está en ella
	 */
	@Test
	public void testSentarSoloUnJugador() {
		p.sentarJugador(j1, 1);
		boolean respuesta = p.empezarPartida();
		assertEquals("La silla uno de la mesa debe contener al jugador j1 ", j1, p.getMesa()[1]);

	}

	/**
	 * Comprueba que no se pueden sentar mas de 4 jugadores en la mesa
	 */
	@Test
	public void testMaximoNumeroJugadores() {
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		p.sentarJugador(j4, 3);
		boolean respuesta = p.sentarJugador(j5, 4);
		assertEquals("No se pueden sentar mas de 4 jugadores en la mesa", false, respuesta);

	}
	
	/**
	 * Comprueba que no empieza la partida con menos de 4 jugadores
	 */
	@Test
	public void testEmpezarPartida() {
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		boolean respuesta = p.empezarPartida();
		assertEquals("No se puede empezar partida con menos de 4 jugadores",
				false, respuesta);
		p.sentarJugador(j4, 3);
		respuesta = p.empezarPartida();
		assertEquals("La partida debería poder empezar ya que hay 4 jugadores",
				true, respuesta);
		System.out.println(p.toString());
		
	}
	
	/**
	 * Comprueba que no se pueden sentar 2 jugadores en la misma silla
	 */
	@Test
	public void testSentarDosJugadoresMismaSilla() {
		p.sentarJugador(j1, 1);
		boolean respuesta = p.sentarJugador(j2, 1);
		assertEquals("No se pueden sentar dos jugadores en la misma silla",
				false, respuesta);
		
	}
	
	/**
	 * Comprueba que se empieza la partida correctamente, parejas definidas y puntos inicializados
	 */
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
	
	/**
	 * Comprueba que suma puntos a cada una de las parejas de forma correcta
	 */
	@Test
	public void testSumarPuntos() {
		p.sentarJugador(j1, 0);
		p.sentarJugador(j2, 1);
		p.sentarJugador(j3, 2);
		p.sentarJugador(j4, 3);
		
		p.empezarPartida();
		p.sumarPuntosPareja1(7);
		p.sumarPuntosPareja2(3);
		int puntos1 = p.sumarPuntosPareja1(5);
		int puntos2 = p.sumarPuntosPareja2(10);

		assertEquals("Los puntos de la pareja1 deben ser 12", 
					12, puntos1);
		assertEquals("Los puntos de la pareja2 deben ser 13", 
					13, puntos2);
		
	}
	
	/**
	 * Comprueba que al pedir que se cambie de mano, cambia la pareja a la que pertenece el jugador que es nueva mano
	 */
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
	
	/**
	 * Comprueba que se suceden las rondas cambiando de mano correctamente
	 */
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
