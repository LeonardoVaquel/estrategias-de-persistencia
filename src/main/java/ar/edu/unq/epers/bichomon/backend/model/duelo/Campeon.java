package ar.edu.unq.epers.bichomon.backend.model.duelo;

import org.joda.time.LocalDateTime;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

/**
 * Se lleva un registro de las fechas en donde un {@link Bicho} se corono campeon
 * y fue derrocado.
 * 
 * @author leonardo
 *
 */
public class Campeon {

	private LocalDateTime coronado;
	private LocalDateTime derrocado;
	private Bicho campeon;
	
	public Campeon(Bicho bicho, LocalDateTime fechaCoronado){
		this.coronado 	= fechaCoronado;
		this.campeon	= bicho;
	}

	public LocalDateTime getCoronado() {
		return coronado;
	}

	public void setCoronado(LocalDateTime coronado) {
		this.coronado = coronado;
	}

	public LocalDateTime getDerrocado() {
		return derrocado;
	}

	public void setDerrocado(LocalDateTime derrocado) {
		this.derrocado = derrocado;
	}

	public Bicho getCampeon() {
		return campeon;
	}

	public void setCampeon(Bicho campeon) {
		this.campeon = campeon;
	};
	
	
}
