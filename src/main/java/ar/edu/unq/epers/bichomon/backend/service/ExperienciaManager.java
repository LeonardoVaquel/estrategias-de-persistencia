package ar.edu.unq.epers.bichomon.backend.service;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Level;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.TablaDeExperiencia;

/**
 * Clase que implementa la interfaz para obtener los servicios de experiencia
 * requeridos sobre un DAO.
 * 
 * @author santiago
 */
public class ExperienciaManager implements ExperienciaDAO {

	private ExperienciaDAO experienciaDAO;
	
	public ExperienciaManager(ExperienciaDAO experienciaDAO) {
		
		this.experienciaDAO = experienciaDAO;
	}
	
	@Override
	public List<Level> getAllLevels() {
		return experienciaDAO.getAllLevels();
	}

	@Override
	public void guardarLevel(Level level) {
		experienciaDAO.guardarLevel(level);
	}
	
	@Override
	public void guardarTablaDeExperiencia(TablaDeExperiencia expTable) {
		experienciaDAO.guardarTablaDeExperiencia(expTable);
	}
	
	@Override
	public int getExperiencieByEvent(String event) {
		return experienciaDAO.getExperiencieByEvent(event);
	}

	@Override
	public Experiencia getExperienciaConfig(String numeroVersion) {
		return experienciaDAO.getExperienciaConfig(numeroVersion);
	}

	@Override
	public void guardarExperienciaConfig(Experiencia expConfig) {
		experienciaDAO.guardarExperienciaConfig(expConfig);
		
	}

	
}
