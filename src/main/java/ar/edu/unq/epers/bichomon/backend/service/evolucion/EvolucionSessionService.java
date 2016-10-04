package ar.edu.unq.epers.bichomon.backend.service.evolucion;

import ar.edu.unq.epers.bichomon.backend.dao.EvolucionDAO;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * TODO
 * EspecieSessionService es una clase que modela el comportamiento
 * de una Especie al conectarse a una sesion en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir
 * información en una base de datos y delegar comportamiento 
 * sobre las instancias Especie que sean necesarias.
 * 
 * @author santiago
 */
public class EvolucionSessionService {
	
	private EvolucionDAO evolucionDAO;
	
	/**
	 * Crea una instancia de EspecieSessionService que utilizará un dao,
	 * a quien delegará los pedidos necesarios sobre una base de datos.
	 * @param especieDAO
	 */
	public EvolucionSessionService(EvolucionDAO evolucionDAO) {
		this.evolucionDAO = evolucionDAO;
	}


}

