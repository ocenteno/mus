package es.insa.proyecto.mus.negocio.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;
import es.insa.proyecto.mus.modelo.Partida;
import es.insa.proyecto.mus.negocio.GanadorChica;

public class TestComprobadorChica {

		private static Jugador jugador1;
		private static Jugador jugador2;
		private static Jugador jugador3;
		private static Jugador jugador4;
		private static Jugador jugador5;
		private static Jugador jugador6;
		private static Jugador jugador7;
		
		private static GanadorChica chica;

		/**
		 * Test que comprueba si ha ordenado bien los ganadores de los lances a Chica
		 * teniendo en cuenta su posición en la mesa (mano, ..., postre)
		 */
		@Test
		public void testGanadorCartaContraCarta() {
			Partida p = new Partida();
			p.sentarJugador(jugador1, 0);
			p.sentarJugador(jugador2, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador1, jugador2);
			Assert.assertEquals("Debe ganar el jugador1", jugador1, ganadorChica);
		}
		
		@Test
		public void testGanadorCartaContraGocho() {
			Partida p = new Partida();
			p.sentarJugador(jugador2, 0);
			p.sentarJugador(jugador3, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador2, jugador3);
			Assert.assertEquals("Debe ganar el jugador2", jugador2, ganadorChica);
		}
		
		@Test
		public void testGanadorCartaContraPito() {
			Partida p = new Partida();
			p.sentarJugador(jugador1, 0);
			p.sentarJugador(jugador4, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador1, jugador4);
			Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorChica);
		}
		
		@Test
		public void testGanadorCartaContraPitoDistintos() {
			Partida p = new Partida();
			p.sentarJugador(jugador2, 0);
			p.sentarJugador(jugador5, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador2, jugador5);
			Assert.assertEquals("Debe ganar el jugador5", jugador5, ganadorChica);
		}
		
		@Test
		public void testGanadorPitoContraPitoDistintos() {
			Partida p = new Partida();
			p.sentarJugador(jugador4, 0);
			p.sentarJugador(jugador5, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador4, jugador5);
			Assert.assertEquals("Debe ganar el jugador5", jugador5, ganadorChica);
		}
		
		@Test
		public void testGanadorGochoContraPito() {
			Partida p = new Partida();
			p.sentarJugador(jugador3, 0);
			p.sentarJugador(jugador4, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador3, jugador4);
			Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorChica);
		}
		
		@Test
		public void testGanadorCartaContraPitoYGocho() {
			Partida p = new Partida();
			p.sentarJugador(jugador2, 0);
			p.sentarJugador(jugador6, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador2, jugador6);
			Assert.assertEquals("Debe ganar el jugador6", jugador6, ganadorChica);
		}
		
		@Test
		public void testGanadorPitoContraPitoYGocho() {
			Partida p = new Partida();
			p.sentarJugador(jugador4, 0);
			p.sentarJugador(jugador6, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador4, jugador6);
			Assert.assertEquals("Debe ganar el jugador4", jugador4, ganadorChica);
		}
		@Test
		public void testGanadorPitoYGochoContraPitoYGochoMano() {
			Partida p = new Partida();
			p.sentarJugador(jugador7, 0);
			p.sentarJugador(jugador6, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador7, jugador6);
			Assert.assertEquals("Debe ganar el jugador7 por la mano", jugador7, ganadorChica);
		}
		
		@Test
		public void testGanadorPitoYGochoContraPitoYGochoOtraMano() {
			Partida p = new Partida();
			p.sentarJugador(jugador6, 0);
			p.sentarJugador(jugador7, 1);
			p.setMano(0);
			chica.setPartida(p);
			
			Jugador ganadorChica = chica.ganador(jugador6, jugador7);
			Assert.assertEquals("Debe ganar el jugador6 por la mano", jugador6, ganadorChica);
		}
		
		/**
		 * j4 > j3 = j1 > j2
		 */
		@BeforeClass
		public static void inicializar() {
			jugador1 = new Jugador("Jugador1");
			jugador1.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
			jugador1.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
			jugador1.añadirCarta(new Carta(Palo.BASTOS, 3, 3));
			jugador1.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
			jugador2 = new Jugador("Jugador2");
			jugador2.añadirCarta(new Carta(Palo.OROS, 1, 1));
			jugador2.añadirCarta(new Carta(Palo.OROS, 2, 2));
			jugador2.añadirCarta(new Carta(Palo.OROS, 5, 5));
			jugador2.añadirCarta(new Carta(Palo.OROS, 4, 4));
			jugador3 = new Jugador("Jugador3");
			jugador3.añadirCarta(new Carta(Palo.COPAS, 1, 1));
			jugador3.añadirCarta(new Carta(Palo.COPAS, 2, 2));
			jugador3.añadirCarta(new Gocho(Palo.COPAS, 3, 10));
			jugador3.añadirCarta(new Carta(Palo.COPAS, 4, 4));
			jugador4 = new Jugador("Jugador4");
			jugador4.añadirCarta(new Carta(Palo.OROS, 1, 1));
			jugador4.añadirCarta(new Pito(Palo.OROS, 2, 1));
			jugador4.añadirCarta(new Carta(Palo.OROS, 5, 5));
			jugador4.añadirCarta(new Carta(Palo.OROS, 4, 4));
			jugador5 = new Jugador("Jugador5");
			jugador5.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
			jugador5.añadirCarta(new Pito(Palo.BASTOS, 2, 1));
			jugador5.añadirCarta(new Carta(Palo.BASTOS, 3, 3));
			jugador5.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
			jugador6 = new Jugador("Jugador6");
			jugador6.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
			jugador6.añadirCarta(new Pito(Palo.BASTOS, 2, 1));
			jugador6.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
			jugador6.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
			jugador7 = new Jugador("Jugador7");
			jugador7.añadirCarta(new Carta(Palo.BASTOS, 1, 1));
			jugador7.añadirCarta(new Pito(Palo.BASTOS, 2, 1));
			jugador7.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
			jugador7.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
			
			chica = new GanadorChica();
	}

}
