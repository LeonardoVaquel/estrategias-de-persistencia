package ar.edu.unq.epers.bichomon.backend.model.duelo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;

/**
 * Se lleva un registro de las fechas en donde un {@link Bicho} se corono campeon
 * y fue derrocado.
 * 
 * @author leonardo
 *
 */
@Entity
public class Campeon {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Dojo dojo;
	private LocalDateTime fechaCoronado;
	private LocalDateTime fechaDerrocado;
	@ManyToOne(cascade=CascadeType.ALL)
	private Bicho campeon;
	@ManyToOne(cascade=CascadeType.ALL)
	private Entrenador entrenador;
	
	
	public Campeon(Bicho bicho, LocalDateTime fechaCoronado, Entrenador entrenador, Dojo dojo){
		this.fechaCoronado 	= fechaCoronado;
		this.campeon	= bicho;
		this.entrenador = entrenador;
		this.dojo = dojo;
	}
	
	public Campeon() {}

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
	
	public void setDojo(Dojo dojo){
		this.dojo = dojo;
	}
	
	public Dojo getDojo(){
		return dojo;
	}
	
	
}
