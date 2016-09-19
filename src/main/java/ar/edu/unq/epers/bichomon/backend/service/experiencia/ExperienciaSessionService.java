package ar.edu.unq.epers.bichomon.backend.service.experiencia;

import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * EspecieSessionService es una clase que modela el comportamiento
 * de una Especie al conectarse a una sesion en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir
 * información en una base de datos y delegar comportamiento 
 * sobre las instancias Especie que sean necesarias.
 * 
 * @author santiago
 */
public class ExperienciaSessionService {
	
	private ExperienciaDAO experienciaDAO;
	
	/**
	 * Crea una instancia de ExperienciaSessionService que utilizará un dao,
	 * a quien delegará los pedidos necesarios sobre una base de datos.
	 * @param especieDAO
	 */
	public ExperienciaSessionService(ExperienciaDAO experienciaDAO) {
		this.experienciaDAO = experienciaDAO;
	}

	/**
	 * Se obtienen todas las especies existentes durante una sesión 
	 * en una base bases de datos
	 */
	public void getExperiencia() {
		Runner.runInSession(() -> {
			experienciaDAO.getAllLevels();
			return null;
		});
	}

}

