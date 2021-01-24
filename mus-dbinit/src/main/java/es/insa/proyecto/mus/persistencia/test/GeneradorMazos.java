package es.insa.proyecto.mus.persistencia.test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Mazo;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;
import es.insa.proyecto.mus.persistencia.DaoMazoHibernate;

public class GeneradorMazos {

	public Mazo crearBaraja() {
		Mazo mazo = new Mazo("BarajaMus");
		
		for (Palo palo : Palo.values()) {
			añadirPalo(mazo, palo);
		}

		return mazo;
	}

	private void añadirPalo(Mazo mazo, Palo palo) {
		Carta uno = new Pito(palo, 1, 1);
		Carta dos = new Pito(palo, 2, 1);
		Carta tres = new Gocho(palo, 3, 10);
		Carta cuatro = new Carta(palo, 4, 4);
		Carta cinco = new Carta(palo, 5, 5);
		Carta seis = new Carta(palo, 6, 6);
		Carta siete = new Carta(palo, 7, 7);
		Carta sota = new Carta(palo, 10, 10);
		Carta caballo = new Carta(palo, 11, 10);
		Carta rey = new Gocho(palo, 12, 10);
		
		mazo.añadir(uno,dos,tres,cuatro,cinco,
				seis,siete,sota,caballo,rey); 
	}

	public void guardarMazo(Mazo mazo) {
		DaoMazoHibernate daoMazo = new DaoMazoHibernate();
//		DaoMazoHibernate daoMazo = new DaoMazoHibernate("cfg/mariadb-hibernate.cfg.xml");

		daoMazo.abrirSesion();
		daoMazo.insertar(mazo);
		daoMazo.cerrarSesion();		
	}

	public void guardarNuevoMazo() {
		Mazo mazo = crearBaraja();
		guardarMazo(mazo);
	}
	
}
