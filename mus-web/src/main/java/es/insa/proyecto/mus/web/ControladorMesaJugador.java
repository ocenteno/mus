package  es.insa.proyecto.mus.web;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("controladorMesaJugador")
public class ControladorMesaJugador {
	
	@RequestMapping("/iniciarJuego.html")
	public String iniciarPartida(Model m){
		
		return "mesaJugador";
	}

}
