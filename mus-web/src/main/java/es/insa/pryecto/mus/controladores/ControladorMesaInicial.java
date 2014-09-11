package es.insa.pryecto.mus.controladores;

import java.sql.Array;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.modelo.Partida;

@Controller
public class ControladorMesaInicial {

	Partida partida = new Partida();
	
	public String pintarMesa(Model m){
		Jugador[] miMesa = partida.getMesa();
		m.addAttribute("resultado", miMesa);
		return "mesaInicial";
		
		
			
		
			}
	
	
	
	public void rellenarMesa(Jugador j, int silla){
		
		if (partida.sentarJugador(j, silla)){
			
		}
		
	}
	
}
