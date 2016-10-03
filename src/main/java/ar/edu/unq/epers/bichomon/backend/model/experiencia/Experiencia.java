package ar.edu.unq.epers.bichomon.backend.model.experiencia;

import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * Experiencia es una clase que representa una estructura compuesta por una base de experiencia
 * que es un número, y una lista de nieveles, sobre la cual obtendran acceso otros componentes.
 * Generalmente será utilizada por ExpHandler.
 * @author santiago
 *
 */
@Entity
public class Experiencia {

	@Id
	private String version; 
	
	private Double baseExp;
	@Transient
	private HashMap<Integer,Double> levels;
	
	private Integer capLevel;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Level> levelList;
	
	public Experiencia(Double baseExp, List<Level> levelList) {
		
		this.setBaseExp(baseExp);
		this.levelList = levelList;
		this.levels = new HashMap<>();
		this.setCapLevel();
		this.listToMap();
	}
	
	public Experiencia() {}

	public String getVersion() {
		return this.version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public Double getBaseExp() {
		return baseExp;
	}

	public void setBaseExp(Double baseExp) {
		this.baseExp = baseExp;
	}

	public void setLevelList(List<Level> levelList) {
		this.levelList = levelList;
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
		this.listToMap();
		return this.levels.get(lvl) * this.baseExp;
	}
	
	public void listToMap() {
		this.levels = new HashMap<>();
		for(Level level:levelList) {
			this.levels.put(level.getNivel(), level.getCoeficiente());
		}
	}
	
}
