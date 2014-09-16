package es.insa.proyecto.mus.negocio.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;
import es.insa.proyecto.mus.contratos.IGestorLances;
import es.insa.proyecto.mus.negocio.GanadorGrande;

public class TestComprobadorGrande {

	private static Jugador jugador1;
	private static Jugador jugador2;
	private static Jugador jugador3;
	private static Jugador jugador4;
	private static Jugador jugador5;
	private static Jugador jugador6;
	private static Jugador jugador7;
	private static GanadorGrande grande;

	/**
	 * Test que comprueba si ha ordenado bien los ganadores de los lances a Grande
	 * teniendo en cuenta su posición en la mesa (mano, ..., postre)
	 */
	@Test
	public void testGanadorCartaContraCarta() {
		Jugador ganadorGrande = grande.ganador(jugador1, jugador2);
		Assert.assertEquals("Debe ganar el jugador2", jugador2, ganadorGrande);
	}
	@Test
	public void testGanadorCartaContraPito() {
		Jugador ganadorGrande = grande.ganador(jugador2, jugador3);
		Assert.assertEquals("Debe ganar el jugador2", jugador2, ganadorGrande);
	}
	@Test
	public void testGanadorCartaContraGocho() {
		Jugador ganadorGrande = grande.ganador(jugador1, jugador4);
		Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorGrande);
	}
	@Test
	public void testGanadorCartaContraGochoIguales() {
		Jugador ganadorGrande = grande.ganador(jugador2, jugador5);
		Assert.assertEquals("Debe ganar el jugador5", jugador5, ganadorGrande);
	}
	@Test
	public void testGanadorGochoContraGocho() {
		Jugador ganadorGrande = grande.ganador(jugador4, jugador5);
		Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorGrande);
	}
	@Test
	public void testGanadorPitoContraGocho() {
		Jugador ganadorGrande = grande.ganador(jugador3, jugador4);
		Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorGrande);
	}
	@Test
	public void testGanadorCartaContraGochoDistintos() {
		Jugador ganadorGrande = grande.ganador(jugador2, jugador6);
		Assert.assertEquals("Debe ganar el jugador6", jugador6, ganadorGrande);
	}
	@Test
	public void testGanadorGochoContraGochoIguales() {
		Jugador ganadorGrande = grande.ganador(jugador4, jugador6);
		Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorGrande);
	}
	@Test
	public void testGanadorGochoContraGochoMano() {
		Jugador ganadorGrande = grande.ganador(jugador6, jugador7);
		Assert.assertEquals("Debe ganar el jugador6", jugador6, ganadorGrande);
	}
	@Test
	public void testGanadorGochoContraGochoOtraMano() {
		Jugador ganadorGrande = grande.ganador(jugador7, jugador6);
		Assert.assertEquals("Debe ganar el jugador7", jugador7, ganadorGrande);
	}
	@BeforeClass
	/**
	 * j4 > j3 = j1 > j2
	 */
	public static void inicializar() {
		jugador1 = new Jugador("Jugador1");
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 3, 3));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		jugador1.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador2 = new Jugador("Jugador2");
		jugador2.añadirCarta(new Carta(Palo.OROS, 12, 10));
		jugador2.añadirCarta(new Carta(Palo.OROS, 3, 3));
		jugador2.añadirCarta(new Carta(Palo.OROS, 5, 5));
		jugador2.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador3 = new Jugador("Jugador3");
		jugador3.añadirCarta(new Carta(Palo.COPAS, 12, 10));
		jugador3.añadirCarta(new Carta(Palo.COPAS, 3, 3));
		jugador3.añadirCarta(new Pito(Palo.COPAS, 2, 1));
		jugador3.añadirCarta(new Carta(Palo.COPAS, 4, 4));
		jugador4 = new Jugador("Jugador4");
		jugador4.añadirCarta(new Carta(Palo.OROS, 12, 10));
		jugador4.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		jugador4.añadirCarta(new Carta(Palo.OROS, 5, 5));
		jugador4.añadirCarta(new Carta(Palo.OROS, 4, 4));
		jugador5 = new Jugador("Jugador5");
		jugador5.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador5.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		jugador5.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		jugador5.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador6 = new Jugador("Jugador6");
		jugador6.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador6.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		jugador6.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		jugador6.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		jugador7 = new Jugador("Jugador7");
		jugador7.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		jugador7.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		jugador7.añadirCarta(new Carta(Palo.BASTOS, 5, 4));
		jugador7.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		
		grande = new GanadorGrande();
		grande.setGestorLances(new IGestorLances() {
			@Override
			public Jugador[] ordenJugadoresSegunMano(Jugador... jugadores) {
				return jugadores;
			}
			@Override
			public Jugador ganadorLance(Jugador... jugadores) {
				return null;
			}
		});
	}

}
