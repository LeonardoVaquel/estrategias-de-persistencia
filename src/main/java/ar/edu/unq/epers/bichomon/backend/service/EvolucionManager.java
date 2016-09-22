package ar.edu.unq.epers.bichomon.backend.service;

import java.util.Collection;

import ar.edu.unq.epers.bichomon.backend.dao.EvolucionDAO;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.Evolucion;
import ar.edu.unq.epers.bichomon.backend.service.evolucion.EvolucionService;

/**
 * Clase que implementa la interfaz provista por el equipo de Frontend
 * para obtener los servicios de especie requeridos sobre un DAO.
 * 
 * @author santiago
 */
public class EvolucionManager implements EvolucionService {

	private EvolucionDAO evolucionDAO;
	
	public EvolucionManager(EvolucionDAO evolucionDAO) {
		
		this.evolucionDAO = evolucionDAO;
	}

	@Override
	public Evolucion getEvolucion(String nombreEspecie) {
		return evolucionDAO.getEvolucion(nombreEspecie);
	}

	@Override
	public Collection<CriterioEvolucion> getCriteriosDeEvolucion(String nombreEspecie) {
		return evolucionDAO.getCriteriosDeEvolucion(nombreEspecie);
	}
	

}
