package ar.edu.unq.epers.bichomon.backend.model.duelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;

public class Historial {

	private List<Campeon> campeones;
	
	public Historial(){
		this.campeones = new ArrayList<>();
	};

	public List<Campeon> getCampeones() {
		return campeones;
	}

	public void setCampeones(List<Campeon> campeones) {
		this.campeones = campeones;
	}



	public void agregar(Bicho campeon, LocalDateTime fecha, Entrenador entrenador, Dojo dojo){
		Campeon nuevoCampeon = new Campeon(campeon, fecha, entrenador, dojo);
		this.campeones.add(nuevoCampeon);
	}
	
	public void actualizarCampeon(Bicho bicho, LocalDateTime fecha){
		
		Campeon nuevoDerrocado = this.getByBicho(bicho);
		
		nuevoDerrocado.setDerrocado(fecha);
		campeones.add(nuevoDerrocado);
	}
	
	private Campeon getByBicho(Bicho bicho){
		Campeon result = null;
		for(Campeon campeon : campeones ){
			if(campeon.getCampeon() == bicho){
				result = campeon;
			}
		}
		return result;
	}

	public Integer totalDeCampeones() {
	
		return campeones.size();
	}

}
