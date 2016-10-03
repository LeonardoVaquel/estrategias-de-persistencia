package ar.edu.unq.epers.bichomon.backend.service.experiencia;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.experiencia.Level;

/**
 * @author santiago
 */
public interface ExperienciaService {
	
	/**
	 * @return una lista de {@link Level}
	 */
	public List<Level> getAllLevels();

}
