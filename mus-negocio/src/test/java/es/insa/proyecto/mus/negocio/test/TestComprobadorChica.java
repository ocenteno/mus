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
import es.insa.proyecto.mus.negocio.ComprobadorChica;

public class TestComprobadorChica {

		private static Jugador jugador1;
		private static Jugador jugador2;
		private static Jugador jugador3;
		private static Jugador jugador4;
		private static Jugador jugador5;
		private static Jugador jugador6;
		private static Jugador jugador7;
		
		private static ComprobadorChica chica;

		/**
		 * Test que comprueba si ha ordenado bien los ganadores de los lances a Chica
		 * teniendo en cuenta su posici�n en la mesa (mano, ..., postre)
		 */
		@Test
		public void testGanadorA() {
			Jugador ganadorChica = chica.ganador(jugador1, jugador2);
			Assert.assertEquals("Debe ganar el jugador1", jugador1, ganadorChica);
		}
		@Test
		public void testGanadorB() {
			Jugador ganadorChica = chica.ganador(jugador2, jugador3);
			Assert.assertEquals("Debe ganar el jugador2", jugador2, ganadorChica);
		}
		@Test
		public void testGanadorC() {
			Jugador ganadorChica = chica.ganador(jugador1, jugador4);
			Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorChica);
		}
		@Test
		public void testGanadorD() {
			Jugador ganadorChica = chica.ganador(jugador2, jugador5);
			Assert.assertEquals("Debe ganar el jugador5", jugador5, ganadorChica);
		}
		@Test
		public void testGanadorE() {
			Jugador ganadorChica = chica.ganador(jugador4, jugador5);
			Assert.assertEquals("Debe ganar el jugador5", jugador5, ganadorChica);
		}
		@Test
		public void testGanadorF() {
			Jugador ganadorChica = chica.ganador(jugador3, jugador4);
			Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorChica);
		}
		@Test
		public void testGanadorG() {
			Jugador ganadorChica = chica.ganador(jugador2, jugador6);
			Assert.assertEquals("Debe ganar el jugador6", jugador6, ganadorChica);
		}
		@Test
		public void testGanadorH() {
			Jugador ganadorChica = chica.ganador(jugador4, jugador6);
			Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorChica);
		}
		@Test
		public void testGanadorI() {
			Jugador ganadorChica = chica.ganador(jugador7, jugador6);
			Assert.assertEquals("Debe ganar el jugador7", jugador7, ganadorChica);
		}
		@Test
		public void testGanadorJ() {
			Jugador ganadorChica = chica.ganador(jugador6, jugador7);
			Assert.assertEquals("Debe ganar el jugador6", jugador6, ganadorChica);
		}
		@BeforeClass
		/**
		 * j4 > j3 = j1 > j2
		 */
		public static void inicializar() {
			jugador1 = new Jugador("Jugador1");
			jugador1.a�adirCarta(new Carta(Palo.BASTOS, 1, 1));
			jugador1.a�adirCarta(new Carta(Palo.BASTOS, 2, 2));
			jugador1.a�adirCarta(new Carta(Palo.BASTOS, 3, 3));
			jugador1.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
			jugador2 = new Jugador("Jugador2");
			jugador2.a�adirCarta(new Carta(Palo.OROS, 1, 1));
			jugador2.a�adirCarta(new Carta(Palo.OROS, 2, 2));
			jugador2.a�adirCarta(new Carta(Palo.OROS, 5, 5));
			jugador2.a�adirCarta(new Carta(Palo.OROS, 4, 4));
			jugador3 = new Jugador("Jugador3");
			jugador3.a�adirCarta(new Carta(Palo.COPAS, 1, 1));
			jugador3.a�adirCarta(new Carta(Palo.COPAS, 2, 2));
			jugador3.a�adirCarta(new Gocho(Palo.COPAS, 3, 10));
			jugador3.a�adirCarta(new Carta(Palo.COPAS, 4, 4));
			jugador4 = new Jugador("Jugador4");
			jugador4.a�adirCarta(new Carta(Palo.OROS, 1, 1));
			jugador4.a�adirCarta(new Pito(Palo.OROS, 2, 1));
			jugador4.a�adirCarta(new Carta(Palo.OROS, 5, 5));
			jugador4.a�adirCarta(new Carta(Palo.OROS, 4, 4));
			jugador5 = new Jugador("Jugador5");
			jugador5.a�adirCarta(new Carta(Palo.BASTOS, 1, 1));
			jugador5.a�adirCarta(new Pito(Palo.BASTOS, 2, 1));
			jugador5.a�adirCarta(new Carta(Palo.BASTOS, 3, 3));
			jugador5.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
			jugador6 = new Jugador("Jugador6");
			jugador6.a�adirCarta(new Carta(Palo.BASTOS, 1, 1));
			jugador6.a�adirCarta(new Pito(Palo.BASTOS, 2, 1));
			jugador6.a�adirCarta(new Gocho(Palo.BASTOS, 3, 10));
			jugador6.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
			jugador7 = new Jugador("Jugador7");
			jugador7.a�adirCarta(new Carta(Palo.BASTOS, 1, 1));
			jugador7.a�adirCarta(new Pito(Palo.BASTOS, 2, 1));
			jugador7.a�adirCarta(new Gocho(Palo.BASTOS, 3, 10));
			jugador7.a�adirCarta(new Carta(Palo.BASTOS, 4, 4));
			
			chica = new ComprobadorChica();
			chica.setGestorLances(new IGestorLances() {
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
