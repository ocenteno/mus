package es.insa.proyecto.mus.web.controladores;

import java.sql.Array;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.insa.proyecto.dominio.cartas.Jugador;
import es.insa.proyecto.mus.modelo.Partida;

@Controller
public class ControladorMesaInicial {

	//Partida partida = new Partida();
	@Autowired
	private Partida partida;
	

	
	
	@RequestMapping("/buscarMesa.html")
	public String buscarMesa(String nombre, Model m) {
		Jugador[] miMesa = partida.getMesa();
		m.addAttribute("mesa", miMesa);
		m.addAttribute("jugador", nombre);
		return "mesaInicial";

	}

	@RequestMapping("/sentarJugador.html")
	public String sentarJugador(String nombre, int silla, Model m, HttpSession sesion) {
		Jugador j = new Jugador(nombre);
		if (partida.sentarJugador(j, silla)) {
			sesion.setAttribute("jugadorActual", nombre);
			return refrescarMesa(m);
		}
		return "mesaInicial";
	}

	@RequestMapping("/refrescarMesa.html")
	private String refrescarMesa(Model m) {
		Jugador[] miMesa = partida.getMesa();
		m.addAttribute("mesa", miMesa);
		boolean lleno = true;
		for (int x=0; x<=3; x++){
			if(miMesa[x]== null) lleno=false;
		}
		if(lleno){
			return "mesaJugador";
		}else{
			return "mesaInicial";
		}
	}

}
