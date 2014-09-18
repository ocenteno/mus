package es.insa.proyecto.dominio.cartas;

public enum Pares {
	NO(0),
	PAR(1),
	MEDIAS(2),
	DUPLES(3);
	
	@SuppressWarnings("unused")
	private int valor;
	
	Pares(int valor){
		this.valor = valor;
	}
}
