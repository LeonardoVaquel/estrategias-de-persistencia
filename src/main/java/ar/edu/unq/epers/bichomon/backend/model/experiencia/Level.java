package ar.edu.unq.epers.bichomon.backend.model.experiencia;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * La clase Level representa un elemento de la Experciencia dentro del juego.
 * Un Level consta de un número y un coeficiente. 
 * @author santiago
 *
 */
@Entity
public class Level {

	Integer nivel;
	Double coeficiente;
	
	@ManyToOne
	Experiencia configuracion;
	
	public Level(Integer nivel, Double coeficiente) {
		this.nivel       = nivel;
		this.coeficiente = coeficiente;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Double getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Double coeficiente) {
		this.coeficiente = coeficiente;
	}
	
}
