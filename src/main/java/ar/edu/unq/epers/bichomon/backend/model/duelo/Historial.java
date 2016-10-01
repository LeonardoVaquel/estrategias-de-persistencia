package ar.edu.unq.epers.bichomon.backend.model.duelo;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

public class Historial {

	private List<Campeon> campeones;
	
	public Historial(){
		this.campeones = new ArrayList<>();
	};

	public void agregar(Bicho campeon, LocalDateTime fecha){
		Campeon nuevoCampeon = new Campeon(campeon, fecha);
		this.campeones.add(nuevoCampeon);
	}
	
	public void modificar(Bicho derrocado, LocalDateTime fecha){
		Campeon nuevoDerrocado = this.campeones.get(campeones.indexOf(derrocado));
		nuevoDerrocado.setDerrocado(fecha);;
		campeones.add(nuevoDerrocado);
	}
}
