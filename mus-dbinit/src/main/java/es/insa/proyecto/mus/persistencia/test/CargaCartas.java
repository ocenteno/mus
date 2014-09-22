package es.insa.proyecto.mus.persistencia.test;

import es.insa.proyecto.dominio.cartas.Carta;
import es.insa.proyecto.dominio.cartas.Gocho;
import es.insa.proyecto.dominio.cartas.Mazo;
import es.insa.proyecto.dominio.cartas.Palo;
import es.insa.proyecto.dominio.cartas.Pito;
import es.insa.proyecto.mus.persistencia.DaoMazoHibernate;

public class CargaCartas {

	public static void main(String[] args) {
		System.out.println("Inicializando BD");
		Carta unoOros = new Pito(Palo.OROS, 1, 1);
		Carta dosOros = new Pito(Palo.OROS, 2, 1);
		Carta tresOros = new Gocho(Palo.OROS, 3, 10);
		Carta cuatroOros = new Carta(Palo.OROS, 4, 4);
		Carta cincoOros = new Carta(Palo.OROS, 5, 5);
		Carta seisOros = new Carta(Palo.OROS, 6, 6);
		Carta sieteOros = new Carta(Palo.OROS, 7, 7);
		Carta sotaOros = new Carta(Palo.OROS, 10, 10);
		Carta caballoOros = new Carta(Palo.OROS, 11, 10);
		Carta reyOros = new Gocho(Palo.OROS, 12, 10);
		
		Carta unoCopas = new Pito(Palo.COPAS, 1, 1);
		Carta dosCopas = new Pito(Palo.COPAS, 2, 1);
		Carta tresCopas = new Gocho(Palo.COPAS, 3, 10);
		Carta cuatroCopas = new Carta(Palo.COPAS, 4, 4);
		Carta cincoCopas = new Carta(Palo.COPAS, 5, 5);
		Carta seisCopas = new Carta(Palo.COPAS, 6, 6);
		Carta sieteCopas = new Carta(Palo.COPAS, 7, 7);
		Carta sotaCopas = new Carta(Palo.COPAS, 10, 10);
		Carta caballoCopas = new Carta(Palo.COPAS, 11, 10);
		Carta reyCopas = new Gocho(Palo.COPAS, 12, 10);
	
		Carta unoBastos = new Pito(Palo.BASTOS, 1, 1);
		Carta dosBastos = new Pito(Palo.BASTOS, 2, 1);
		Carta tresBastos = new Gocho(Palo.BASTOS, 3, 10);
		Carta cuatroBastos = new Carta(Palo.BASTOS, 4, 4);
		Carta cincoBastos = new Carta(Palo.BASTOS, 5, 5);
		Carta seisBastos = new Carta(Palo.BASTOS, 6, 6);
		Carta sieteBastos = new Carta(Palo.BASTOS, 7, 7);
		Carta sotaBastos = new Carta(Palo.BASTOS, 10, 10);
		Carta caballoBastos = new Carta(Palo.BASTOS, 11, 10);
		Carta reyBastos = new Gocho(Palo.BASTOS, 12, 10);
		
		Carta unoEspadas = new Pito(Palo.ESPADAS, 1, 1);
		Carta dosEspadas= new Pito(Palo.ESPADAS, 2, 1);
		Carta tresEspadas = new Gocho(Palo.ESPADAS, 3, 10);
		Carta cuatroEspadas = new Carta(Palo.ESPADAS, 4, 4);
		Carta cincoEspadas = new Carta(Palo.ESPADAS, 5, 5);
		Carta seisEspadas = new Carta(Palo.ESPADAS, 6, 6);
		Carta sieteEspadas = new Carta(Palo.ESPADAS, 7, 7);
		Carta sotaEspadas = new Carta(Palo.ESPADAS, 10, 10);
		Carta caballoEspadas = new Carta(Palo.ESPADAS, 11, 10);
		Carta reyEspadas = new Gocho(Palo.ESPADAS, 12, 10);
		
		System.out.println("Creando mazo");
		Mazo mazo = new Mazo("BarajaMus");
		mazo.añadir(unoOros,dosOros,tresOros,cuatroOros,cincoOros,
				seisOros,sieteOros,sotaOros,caballoOros,reyOros); 
		
		mazo.añadir(unoCopas,dosCopas,tresCopas,cuatroCopas,cincoCopas,
				seisCopas,sieteCopas,sotaCopas,caballoCopas,reyCopas);
		
		mazo.añadir(unoEspadas,dosEspadas,tresEspadas,cuatroEspadas,cincoEspadas,
				seisEspadas,sieteEspadas,sotaEspadas,caballoEspadas,reyEspadas);
		
		mazo.añadir(unoBastos,dosBastos,tresBastos,cuatroBastos,cincoBastos,
				seisBastos,sieteBastos,sotaBastos,caballoBastos,reyBastos);
		
		DaoMazoHibernate daoMazo = new DaoMazoHibernate();
		
		daoMazo.abrirSesion();
		daoMazo.insertar(mazo);
		daoMazo.cerrarSesion();
		System.out.println("BD inicializada");
		System.exit(0);
	}
}
