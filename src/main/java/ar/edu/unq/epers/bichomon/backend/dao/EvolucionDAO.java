package ar.edu.unq.epers.bichomon.backend.dao;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;

/**
 * Interfaz para servicios relacionados con bichos y eventos de evolución.
 */
public interface EvolucionDAO {
	

	/**
	 * Dado un nombre de {@link Especie} se retorna una lista de {@link CriterioEvolucion}
	 */
	List<CriterioEvolucion> getCriteriosDeEvolucion(String nombreEspecie);
	
}
