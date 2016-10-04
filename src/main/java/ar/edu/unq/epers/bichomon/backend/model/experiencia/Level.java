package ar.edu.unq.epers.bichomon.backend.model.experiencia;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * La clase Level representa un elemento de la Experciencia dentro del juego.
 * Un Level consta de un número y un coeficiente, donde el número es un nivel y el coeficiente
 * un valor que se utilizará para realizar cálculos sobre la experiencia que un {@link Entrenador} debería
 * recibir.
 * 
 * @author santiago
 */
@Entity
public class Level {

	@Id
	Integer nivel;
	Double coeficiente;
	
	@ManyToOne(cascade=CascadeType.ALL)
	Experiencia configuracion;
	
	public Level(Integer nivel, Double coeficiente) {
		this.nivel       = nivel;
		this.coeficiente = coeficiente;
	}

	public Level() {}
	
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
