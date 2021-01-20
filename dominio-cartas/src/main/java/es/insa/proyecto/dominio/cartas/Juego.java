package es.insa.proyecto.dominio.cartas;

public enum Juego {
	TREINTAYUNA(2),
	JUEGO(1),
	PUNTO(0);
	
	
	
	@SuppressWarnings("unused")
	private int valor;
	
	Juego(int valor){
		this.valor = valor;
	}
}
