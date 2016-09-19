package ar.edu.unq.epers.bichomon.backend.model.experiencia;

import java.util.HashMap;

import javax.persistence.Entity;

/**
 * Experiencia es una clase que representa una estructura compuesta por una base de experiencia
 * que es un número, y una lista de nieveles, sobre la cual obtendran acceso otros componentes.
 * Generalmente será utilizada por ExpHandler.
 * @author santiago
 *
 */
@Entity
public class Experiencia {

	private Double baseExp;
	private HashMap<Integer,Double> levels;
	private Integer capLevel;
	
	public Experiencia(Double baseExp) {
		
		this.setBaseExp(baseExp);
		this.levels = new HashMap<>();
		this.setCapLevel();
	}

	public Double getBaseExp() {
		return baseExp;
	}

	public void setBaseExp(Double baseExp) {
		this.baseExp = baseExp;
	}

	public HashMap<Integer, Double> getLevels() {
		return levels;
	}

	public void setLevels(HashMap<Integer, Double> levels) {
		this.levels = levels;
	}
	
	public Integer getCapLevel() {
		return this.capLevel;
	}
	
	public void setCapLevel() {
		this.capLevel = levels.size();
	}
	
	public Double getExpByLvl(Integer lvl) {
		return this.levels.get(lvl) * this.baseExp;
	}
	
}
