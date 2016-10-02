package ar.edu.unq.epers.bichomon.backend.service.entrenador;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * EntrenadorSessionService es una clase que modela el comportamiento de un {@link Entrenador} al conectarse a 
 * una sesión en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir información en una base de datos y 
 * delegar comportamiento sobre las instancias {@link Entrenador} que sean necesarias.
 * 
 * @author santiago
 */
public class EntrenadorSessionService {

	
	private EntrenadorDAO entrenadorDAO;
	
	public EntrenadorSessionService(EntrenadorDAO entrenadorDAO) {
		this.entrenadorDAO = entrenadorDAO;
	}
	
	public Entrenador getEntrenador(String nombreEntrenador) {
		return Runner.runInSession(() -> {
			return entrenadorDAO.getEntrenador(nombreEntrenador);
		});
	}
	
	public void guardarEntrenador(Entrenador entrenador) {
		Runner.runInSession(() -> {
			entrenadorDAO.guardarEntrenador(entrenador);
			return null;
		});
	}
	
}
