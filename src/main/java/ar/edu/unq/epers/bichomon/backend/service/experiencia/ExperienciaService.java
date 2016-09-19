package ar.edu.unq.epers.bichomon.backend.service.experiencia;

import java.util.HashMap;

/**
 * @author santiago
 */
public interface ExperienciaService {
	
	/**
	 * @return un hashmap con key Integer representando un nivel y value Double representando un coeficiente.
	 */
	HashMap<Integer, Double> getAllLevels();

}
