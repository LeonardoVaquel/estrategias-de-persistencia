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
	private Integer nivel;
	private Double coeficienteExp;
	private Integer coeficienteBichos;
	@ManyToOne(cascade=CascadeType.ALL)
	private Experiencia configuracion;
	
	public Level(Integer nivel, Double coeficienteExp, Integer coeficienteBichos) {
		this.nivel       	   = nivel;
		this.coeficienteExp    = coeficienteExp;
		this.coeficienteBichos = coeficienteBichos;
	}

	public Level() {}
	
	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Double getCoeficienteExp() {
		return coeficienteExp;
	}

	public void setCoeficienteExp(Double coeficiente) {
		this.coeficienteExp = coeficiente;
	}

	public Integer getCoeficienteBichos() {
		return coeficienteBichos;
	}

	public void setCoeficienteBichos(Integer coeficienteCaptura) {
		this.coeficienteBichos = coeficienteCaptura;
	}
	
}
