package ar.edu.unq.epers.bichomon.backend.model.duelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;

/**
 * La Clase Historial Representa o wrappea a una Lista de Campeones donde está
 * registrado los datos de cada Bicho campeon.
 * 
 * @author Leonardo
 *
 */
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


	/**
	 * Se agrega un {@link Campeon} al Historial.
	 * @param campeon Un {@link Bicho} campeon.
	 * @param fecha Un {@link LocalDateTima} indicando la fecha en que el Bicho se corono Campeon
	 * @param entrenador El {@link Entrenador} del Campeon.
	 * @param dojo El {@link Dojo} donde se produjo el {@link Duelo}.
	 */
	public void agregar(Bicho campeon, LocalDateTime fecha, Entrenador entrenador, Dojo dojo){
		Campeon nuevoCampeon = new Campeon(campeon, fecha, entrenador, dojo);
		this.campeones.add(nuevoCampeon);
	}
	
	
	/**
	 * Actualiza al {@link Campeon} derrotado registrando la fecha en la que fue derrocado.
	 * @param bicho {@link Bicho} derrotado.
	 * @param fecha {@link LocalDateTime} fecha que fue derrocado el exCampeon.
	 */
	public void actualizarCampeon(Bicho bicho, LocalDateTime fecha){
		
		Campeon nuevoDerrocado = this.getByBicho(bicho);
		
		nuevoDerrocado.setDerrocado(fecha);
		campeones.add(nuevoDerrocado);
	}
	
	/**
	 * Busca un Campeon con sus datos.
	 * @param bicho {@link Bicho} del que se quiere saber sus datos de Campeon.
	 * @return {@link Campeon}
	 */
	private Campeon getByBicho(Bicho bicho){
		Campeon result = null;
		for(Campeon campeon : campeones ){
			if(campeon.getCampeon() == bicho){
				result = campeon;
			}
		}
		return result;
	}

	/**
	 * 
	 * @return Un Integer con el total de Campeones registrados.
	 */
	public Integer totalDeCampeones() {
	
		return campeones.size();
	}

}
