package es.insa.proyecto.mus.persistencia.test;

import es.insa.proyecto.dominio.cartas.Mazo;

public class CargaCartas {

	public static void main(String[] args) {
		System.out.println("Inicializando BD");
		GeneradorMazos cargador = new GeneradorMazos();
		System.out.println("Creando mazo");
		Mazo mazo = cargador.crearBaraja();
		System.out.println("Guardando en Base de Datos");
		cargador.guardarMazo(mazo);
		System.out.println("BD inicializada");
		System.exit(0);
	}
}
