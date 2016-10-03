package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;

/**
 * Interfaz para servicios de {@link Ubicacion}
 * @author santiago
 *
 */
public interface UbicacionDAO {

	/**
	 * Éste método guardará una {@link Guarderia} en una base de datos
	 * @param guarderia - una instancia de {@link Guarderia} 
	 */
	public void guardarGuarderia(Guarderia guarderia);
	

}
