package ar.edu.unq.epers.bichomon.backend.dao;

import java.util.HashMap;

/**
 * Tiene la responsabilidad de guardar y recuperar la Experiencia desde
 * el medio persistente
 */
public interface ExperienciaDAO {
	
	/**
	 * @return un hashmap con key Integer representando un nivel y value Double representando un coeficiente.
	 */
	HashMap<Integer, Double> getAllLevels();
	
}
