package ar.edu.unq.epers.bichomon.backend.service.evolucion;

import java.util.Collection;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;

public interface EvolucionService {
	
	/**
	 * Este método devolverá una colección de {@link CriterioEvolucion} para una {@link Especie} cuyo
	 * nombre sea igual al provisto por parámetro.
	 * 
	 * Se espera que espera que este método devuelva, a lo sumo, un solo resultado
	 * 
	 * @param nombreEspecie - el nombre de la especie para una colección de criterios
	 * @return una colección de {@link CriterioEvolucion}
	 */
	Collection<CriterioEvolucion> getCriteriosDeEvolucion(String nombreEspecie);

}
