package ar.edu.unq.epers.bichomon.backend.model.duelo;

import org.joda.time.LocalDateTime;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

/**
 * Se lleva un registro de las fechas en donde un {@link Bicho} se corono campeon
 * y fue derrocado.
 * 
 * @author leonardo
 *
 */
public class Campeon {

	private LocalDateTime fechaCoronado;
	private LocalDateTime fechaDerrocado;
	private Bicho campeon;
	private Entrenador entrenador;
	private String dojo;
	
	public Campeon(Bicho bicho, LocalDateTime fechaCoronado, Entrenador entrenador, String dojo){
		this.fechaCoronado 	= fechaCoronado;
		this.campeon	= bicho;
		this.entrenador = entrenador;
		this.dojo = dojo;
	}

	public LocalDateTime getCoronado() {
		return fechaCoronado;
	}

	public void setCoronado(LocalDateTime coronado) {
		this.fechaCoronado = coronado;
	}

	public LocalDateTime getDerrocado() {
		return fechaDerrocado;
	}

	public void setDerrocado(LocalDateTime derrocado) {
		this.fechaDerrocado = derrocado;
	}

	public Bicho getCampeon() {
		return campeon;
	}

	public void setCampeon(Bicho campeon) {
		this.campeon = campeon;
	};
	
	public void setEntrenador(Entrenador entrenador){
		this.entrenador = entrenador;
	}
	
	public Entrenador getEntrenador(){
		return entrenador;
	}
	
	public void setDojo(String dojo){
		this.dojo = dojo;
	}
	
	public String getDojo(){
		return dojo;
	}
	
	
}
