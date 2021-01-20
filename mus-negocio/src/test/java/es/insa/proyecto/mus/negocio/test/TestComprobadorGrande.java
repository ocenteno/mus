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
import es.insa.proyecto.mus.negocio.GanadorGrande;

public class TestComprobadorGrande {

	private static Jugador j1ReyCuatroTresDos;
	private static Jugador j2ReyCincoCuatroTres;
	private static Jugador j3ReyCuatroTresPito;
	private static Jugador j4ReyReyCincoCuatro;
	private static Jugador j5ReyReyCuatroDos;
	private static Jugador j6ReyReyCincoCuatro;
	private static Jugador j7ReyReyCincoCuatro;
	private static GanadorGrande grande;

	/**
	 * Test que comprueba si ha ordenado bien los ganadores de los lances a Grande
	 * teniendo en cuenta su posición en la mesa (mano, ..., postre)
	 */
	@Test
	public void testGanadorCartaContraCarta() {
		Partida p = new Partida();
		p.sentarJugador(j1ReyCuatroTresDos, 0);
		p.sentarJugador(j2ReyCincoCuatroTres, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j1ReyCuatroTresDos, j2ReyCincoCuatroTres);
		Assert.assertEquals("Debe ganar el jugador2", j2ReyCincoCuatroTres, ganadorGrande);
	}
	@Test
	public void testGanadorCartaContraPito() {
		Partida p = new Partida();
		p.sentarJugador(j2ReyCincoCuatroTres, 0);
		p.sentarJugador(j3ReyCuatroTresPito, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j2ReyCincoCuatroTres, j3ReyCuatroTresPito);
		Assert.assertEquals("Debe ganar el jugador2", j2ReyCincoCuatroTres, ganadorGrande);
	}
	@Test
	public void testGanadorCartaContraGocho() {
		Partida p = new Partida();
		p.sentarJugador(j1ReyCuatroTresDos, 0);
		p.sentarJugador(j4ReyReyCincoCuatro, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j1ReyCuatroTresDos, j4ReyReyCincoCuatro);
		Assert.assertEquals("Debe ganar el jugador4", j4ReyReyCincoCuatro, ganadorGrande);
	}
	@Test
	public void testGanadorCartaContraGochoIguales() {
		Partida p = new Partida();
		p.sentarJugador(j2ReyCincoCuatroTres, 0);
		p.sentarJugador(j5ReyReyCuatroDos, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j2ReyCincoCuatroTres, j5ReyReyCuatroDos);
		Assert.assertEquals("Debe ganar el jugador5", j5ReyReyCuatroDos, ganadorGrande);
	}
	@Test
	public void testGanadorGochoContraGocho() {
		Partida p = new Partida();
		p.sentarJugador(j4ReyReyCincoCuatro, 0);
		p.sentarJugador(j5ReyReyCuatroDos, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j4ReyReyCincoCuatro, j5ReyReyCuatroDos);
		Assert.assertEquals("Debe ganar el jugador4", j4ReyReyCincoCuatro, ganadorGrande);
	}
	@Test
	public void testGanadorPitoContraGocho() {
		Partida p = new Partida();
		p.sentarJugador(j3ReyCuatroTresPito, 0);
		p.sentarJugador(j4ReyReyCincoCuatro, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j3ReyCuatroTresPito, j4ReyReyCincoCuatro);
		Assert.assertEquals("Debe ganar el jugador4", j4ReyReyCincoCuatro, ganadorGrande);
	}
	@Test
	public void testGanadorCartaContraGochoDistintos() {
		Partida p = new Partida();
		p.sentarJugador(j2ReyCincoCuatroTres, 0);
		p.sentarJugador(j6ReyReyCincoCuatro, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j2ReyCincoCuatroTres, j6ReyReyCincoCuatro);
		Assert.assertEquals("Debe ganar el jugador6", j6ReyReyCincoCuatro, ganadorGrande);
	}
	@Test
	public void testGanadorGochoContraGochoIguales() {
		Partida p = new Partida();
		p.sentarJugador(j4ReyReyCincoCuatro, 0);
		p.sentarJugador(j6ReyReyCincoCuatro, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j4ReyReyCincoCuatro, j6ReyReyCincoCuatro);
		Assert.assertEquals("Debe ganar el jugador4 por la mano", j4ReyReyCincoCuatro, ganadorGrande);
	}
	@Test
	public void testGanadorGochoContraGochoMano() {
		Partida p = new Partida();
		p.sentarJugador(j6ReyReyCincoCuatro, 0);
		p.sentarJugador(j7ReyReyCincoCuatro, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j6ReyReyCincoCuatro, j7ReyReyCincoCuatro);
		Assert.assertEquals("Debe ganar el jugador6", j6ReyReyCincoCuatro, ganadorGrande);
	}
	@Test
	public void testGanadorGochoContraGochoOtraMano() {
		Partida p = new Partida();
		p.sentarJugador(j7ReyReyCincoCuatro, 0);
		p.sentarJugador(j6ReyReyCincoCuatro, 1);
		p.setMano(0);
		grande.setPartida(p);
		
		Jugador ganadorGrande = grande.ganador(j7ReyReyCincoCuatro, j6ReyReyCincoCuatro);
		Assert.assertEquals("Debe ganar el jugador7", j7ReyReyCincoCuatro, ganadorGrande);
	}
	@BeforeClass
	/**
	 * j4 > j3 = j1 > j2
	 */
	public static void inicializar() {
		j1ReyCuatroTresDos = new Jugador("Jugador1");
		j1ReyCuatroTresDos.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j1ReyCuatroTresDos.añadirCarta(new Carta(Palo.BASTOS, 3, 3));
		j1ReyCuatroTresDos.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		j1ReyCuatroTresDos.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		j2ReyCincoCuatroTres = new Jugador("Jugador2");
		j2ReyCincoCuatroTres.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j2ReyCincoCuatroTres.añadirCarta(new Carta(Palo.OROS, 3, 3));
		j2ReyCincoCuatroTres.añadirCarta(new Carta(Palo.OROS, 5, 5));
		j2ReyCincoCuatroTres.añadirCarta(new Carta(Palo.OROS, 4, 4));
		j3ReyCuatroTresPito = new Jugador("Jugador3");
		j3ReyCuatroTresPito.añadirCarta(new Carta(Palo.COPAS, 12, 10));
		j3ReyCuatroTresPito.añadirCarta(new Carta(Palo.COPAS, 3, 3));
		j3ReyCuatroTresPito.añadirCarta(new Pito(Palo.COPAS, 2, 1));
		j3ReyCuatroTresPito.añadirCarta(new Carta(Palo.COPAS, 4, 4));
		j4ReyReyCincoCuatro = new Jugador("Jugador4");
		j4ReyReyCincoCuatro.añadirCarta(new Carta(Palo.OROS, 12, 10));
		j4ReyReyCincoCuatro.añadirCarta(new Gocho(Palo.OROS, 3, 10));
		j4ReyReyCincoCuatro.añadirCarta(new Carta(Palo.OROS, 5, 5));
		j4ReyReyCincoCuatro.añadirCarta(new Carta(Palo.OROS, 4, 4));
		j5ReyReyCuatroDos = new Jugador("Jugador5");
		j5ReyReyCuatroDos.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j5ReyReyCuatroDos.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		j5ReyReyCuatroDos.añadirCarta(new Carta(Palo.BASTOS, 2, 2));
		j5ReyReyCuatroDos.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		j6ReyReyCincoCuatro = new Jugador("Jugador6");
		j6ReyReyCincoCuatro.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j6ReyReyCincoCuatro.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		j6ReyReyCincoCuatro.añadirCarta(new Carta(Palo.BASTOS, 5, 5));
		j6ReyReyCincoCuatro.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		j7ReyReyCincoCuatro = new Jugador("Jugador7");
		j7ReyReyCincoCuatro.añadirCarta(new Carta(Palo.BASTOS, 12, 10));
		j7ReyReyCincoCuatro.añadirCarta(new Gocho(Palo.BASTOS, 3, 10));
		j7ReyReyCincoCuatro.añadirCarta(new Carta(Palo.BASTOS, 5, 4));
		j7ReyReyCincoCuatro.añadirCarta(new Carta(Palo.BASTOS, 4, 4));
		
		grande = new GanadorGrande();
	}

}
