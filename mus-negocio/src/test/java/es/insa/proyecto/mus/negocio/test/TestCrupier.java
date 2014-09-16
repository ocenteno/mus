package es.insa.proyecto.mus.negocio.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.dominio.cartas.Mazo;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.mus.contratos.DaoMazo;
import es.insa.proyecto.mus.negocio.Crupier;


/**
 * 
 * @author Jose Antonio Torre y M.Angeles Pascual
 *
 */
public class TestCrupier {
	
	private Crupier crupier;
	
/**
 * Tenemos que trucar los datos que nos daría el DaoMazo ya que no tenemos
 * acceso a Persistencia, por eso se despliegan los métodos y se rellenan
 * solo los que necesitamos, los demás se dejan.	
 * @throws Exception
 */
	@Before
	public void setUp() throws Exception {
		// DATOS TRUCADOS
		DaoMazo daoMazo = new DaoMazo() {
			
			@Override
			public List<Mazo> listarTodos() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void insertar(Mazo c) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void eliminar(Mazo c) {
				// TODO Auto-generated method stub
				
			}
			/**
			 * Devolvemos un mazo inventado, como si hubieramos 
			 * ido a la base de datos
			 * @param id
			 * @return Mazo
			 */
			@Override
			public Mazo buscar(Integer id) {
				return new Mazo("PruebaMazo");
			}
			
			@Override
			public void actualizar(Mazo c) {
				// TODO Auto-generated method stub
				
			}
			/**
			 * Llenamos el mazo con algunas cartas como si las hubieramos
			 * cogido de la base de datos
			 */
			@Override
			public void llenarMazo(Mazo m) {
				m.añadir( new Carta(Palo.BASTOS, 4, 4));
				m.añadir( new Carta(Palo.COPAS, 4, 4));		
				m.añadir( new Carta(Palo.ESPADAS, 4, 4));
			}
		};
		
		crupier = new Crupier();
		crupier.setDaoMazo(daoMazo);
	}

	
	/**
	 * Inicializamos mazo con 3 cartas, repartimos 4, la cuarta es null
	 */
	@Test
	public void testInicializarMazo() {
		crupier.inicializarMazo();
		Jugador j = new Jugador();
		// Si reparto 4 cartas, las 3 primerar son validas
		// la cuarta es null
		crupier.repartirCartas(4, j);
		assertNull("El mazoDeReparto debe tener 3 cartas", j.getMano()[3]);
	}
		
	
	/**
	 * Inicializamos el mazo con 3 cartas, repartimos 1, el jugador
	 * debe tener una carta en la mano
	 */
	@Test
	public void testRepartirConCartas() {
		Jugador j = new Jugador();
		crupier.inicializarMazo();
		crupier.repartirCartas(1, j);
		assertNotNull("el jugador deberia tener una carta", j.getMano()[0]);
	}
	
	
	/**
	 * Inicializamos el mazo con 3 cartas, repartimos 4, la cuarta
	 * carta debe ser null.
	 */
	@Test
	public void testRepartirSinCartas() {
		Jugador j = new Jugador();
		crupier.inicializarMazo();
		crupier.repartirCartas(4, j);
		assertNull("el jugador deberia tener la 4 carta null", j.getMano()[3]);
	}

	
	/**
	 * Inicializamos el mazo con 3 cartas, repartimos 3, nos descartamos
	 * de una carta, en la mano deben quedar 2 cartas.
	 */
	@Test
	public void testDescartarCartas() {
		Jugador j = new Jugador();
		crupier.inicializarMazo();
		crupier.repartirCartas(3, j);
		crupier.descartarCartas(j, j.getMano()[0]);
		assertEquals("La mano debe tener 2 cartas", 2, j.getMano().length);
	}

	
	/**
	 * Inicializamos el mazo con 3 cartas, repartimos 3, nos descartamos de 3,
	 * por lo tanto el mazo de reparto está a cero y el de descartes tiene 3.
	 * Al recoger los descartes, se dan la vuelta a los mazos, si después podemos 
	 * repartir una es que se han cambiado bien.
	 */
	@Test
	public void testRecogerDescartes() {
		Jugador j = new Jugador();
		crupier.inicializarMazo();
		crupier.repartirCartas(3, j);
		crupier.descartarCartas(j, j.getMano()[0]);
		crupier.descartarCartas(j, j.getMano()[0]);
		crupier.descartarCartas(j, j.getMano()[0]);
		crupier.recogerDescartes();
		crupier.repartirCartas(1, j);
		assertNotNull("Tengo cartas en el mazo de reparto", j.getMano()[0]);
	}

}
