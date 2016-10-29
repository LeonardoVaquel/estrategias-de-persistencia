package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

/**
 * Interfaz para servicios HibernateBichoDAO.
 * @author santiago
 *
 */
public interface BichoDAO {

	/**
	 * Éste método buscara un bicho de un nombre en particular
	 * @param nombreBicho - el nombre del bicho a ser buscado 
	 */
	public Bicho getBicho(int idBicho);
	
	/**
	 * Este método persiste el estado actual de un bicho en una base de datos 
	 * @param bicho - el {@link Bicho} a ser persistido
	 */
	public void guardarBicho(Bicho bicho);
	
	
	public void removeAllBichos();
}
