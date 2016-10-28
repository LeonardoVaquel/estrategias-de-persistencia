package ar.edu.unq.epers.bichomon.backend.model.experiencia;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

/**
 * ExpHandler es una clase que representa un manejador de puntos de experiencia y nivel.
 * Recibe y Ofrece servicios a un Entrenador a traves de una instancia de Experiencia.
 * @author santiago
 *
 */
public class ExpHandler {

	private Experiencia cfg;
	private Double baseExp;
	
	public ExpHandler(Experiencia configuracion) {
		
		this.setCfg(configuracion);
		this.setBaseExp(configuracion.getBaseExp());
	}
	
	public Experiencia getCfg() {
		return this.cfg;
	}
	
	public void setCfg(Experiencia configuracion) {
		this.cfg = configuracion;
	}
	
	public Double getBaseExp() {
		return this.baseExp;
	}
	
	public void setBaseExp(Double baseExp) {
		this.baseExp = baseExp;
	}

	/**
	 * Dada una cantidad de experiencia y un entrenador, se setean los valores en un entrenador
	 * y se evalúa si éste debería subir de nivel.
	 * @param exp
	 * @param entrenador
	 */
	public void evaluateGainedExp(Double exp, Entrenador entrenador) {
		entrenador.setTotalExp(entrenador.getTotalExp() + exp);
		entrenador.setCurrentExp(entrenador.getCurrentExp() + exp);
		this.evaluateLevelUp(entrenador.getCurrentExp(), entrenador);
		
	}
	
	/**
	 * Dada una cantidad de experiencia, un nivel y un entrenador, si la cantidad cumple los 
	 * requisitos para subir de nivel, entonces se setean nuevos valores en un entrenador
	 * hasta satisfacer la condición.
	 * @param currentExp
	 * @param lvl
	 * @param entrenador
	 */
	public void evaluateLevelUp(Double currentExp, Entrenador entrenador) {
		Level trainerLevel = entrenador.getNivel();
		Double expToLvlUp = this.cfg.getExpByLvl(trainerLevel.getNivel());
		
		if(currentExp >= expToLvlUp) {
			
			currentExp  = currentExp - expToLvlUp;
			entrenador.setCurrentExp(currentExp);
			
			Integer updatedLvl = trainerLevel.getNivel() + 1;
			Level newLevel = cfg.getLevelByNumber(updatedLvl);
			
			entrenador.setNivel(newLevel);
			
			this.evaluateLevelUp(currentExp, entrenador);
		}
	}

	/**
	 * Dada una cantidad de experiencia y un nivel, se evalúa si la cantidad supera
	 * o iguala los requisitos de experiencia del nivel pasado como parámetro.
	 * @param exp
	 * @param lvl
	 * @return
	 */
	private boolean isLevelUp(Double exp, Integer lvl) {
		return exp >= cfg.getExpByLvl(lvl);
	}
	
}
