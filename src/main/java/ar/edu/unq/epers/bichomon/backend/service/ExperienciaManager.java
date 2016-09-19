package ar.edu.unq.epers.bichomon.backend.service;

import java.util.HashMap;

import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.service.experiencia.ExperienciaService;

/**
 * Clase que implementa la interfaz para obtener los servicios de experiencia
 * requeridos sobre un DAO.
 * 
 * @author santiago
 */
public class ExperienciaManager implements ExperienciaService {

	private ExperienciaDAO experienciaDAO;
	
	public ExperienciaManager(ExperienciaDAO experienciaDAO) {
		
		this.experienciaDAO = experienciaDAO;
	}
	
	@Override
	public HashMap<Integer, Double> getAllLevels() {
		return experienciaDAO.getAllLevels();
	}


}
