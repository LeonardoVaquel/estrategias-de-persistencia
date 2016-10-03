package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;

/**
 * Interfaz para servicios de historial de campeones.
 * @author santiago
 *
 */
public interface HistorialDAO {

	/**
	 * Dado un {@link Campeon} se persiste en una base de datos
	 * @param campeon - una instancia de {@link Campeon}
	 */
	public void guardarCampeon(Campeon campeon);
}
