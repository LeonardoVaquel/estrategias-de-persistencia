package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * BichoSessionService es una clase que modela el comportamiento de un {@link Bicho} al conectarse a 
 * una sesión en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir información en una base de datos y 
 * delegar comportamiento sobre las instancias {@link Bicho} que sean necesarias.
 * 
 * @author santiago
 */
public class BichoSessionService {

	
	private BichoDAO bichoDAO;
	
	public BichoSessionService(BichoDAO bichoDAO) {
		this.bichoDAO = bichoDAO;
	}
	
	public void puedeEvolucionar(String entrenador, int bicho) {
		Runner.runInSession(() -> {
			bichoDAO.puedeEvolucionar(entrenador, bicho);
			return null;
		});
	}
}
