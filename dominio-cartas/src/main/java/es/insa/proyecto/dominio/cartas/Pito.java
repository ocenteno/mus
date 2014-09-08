package es.insa.proyecto.dominio.cartas;

public class Pito extends Carta{
	
	@Override
	public boolean equals(Object o) {

		if (o instanceof Pito){
			return true;
		}
		return false;
	}
}
