package ar.edu.unq.epers.bichomon.backend.dao;

import java.util.Collection;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.Evolucion;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieNoExistente;

/**
 * 
 */
public interface EvolucionDAO {
	
	/**
	 * 
	 * TODO
	 * Este método devolverá la {@link Especie} cuyo nombre sea igual al provisto por
	 * parámetro.
	 * 
	 * Se espera que este método devuelva, a lo sumo, un solo resultado.
	 * 
	 * @param nombreEspecie - el nombre de la especie que se busca
	 * @return la especie encontrada
	 * @throws la excepción {@link EspecieNoExistente} (no chequeada)
	 */
	Evolucion getEvolucion(String nombreEspecie);

	/**
	 * TODO
	 */
	Collection<CriterioEvolucion> getCriteriosDeEvolucion(String nombreEspecie);
	
}
