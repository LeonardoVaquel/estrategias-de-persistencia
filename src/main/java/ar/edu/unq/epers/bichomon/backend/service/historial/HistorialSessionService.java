package ar.edu.unq.epers.bichomon.backend.service.historial;

import ar.edu.unq.epers.bichomon.backend.dao.HistorialDAO;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * HistorialSessionService es una clase que modela el comportamiento de un {@link Campeon} al conectarse a 
 * una sesión en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir información en una base de datos y 
 * delegar comportamiento sobre las instancias {@link Campeon} que sean necesarias.
 * 
 * @author santiago
 */
public class HistorialSessionService implements HistorialDAO {

	
	private HistorialDAO historialDAO;
	
	public HistorialSessionService(HistorialDAO historialDAO) {
		this.historialDAO = historialDAO;
	}
	
	@Override
	public void guardarCampeon(Campeon campeon) {
		Runner.runInSession(() -> {
			historialDAO.guardarCampeon(campeon);
			return null;
		});
	}

}
