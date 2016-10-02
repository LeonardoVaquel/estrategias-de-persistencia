package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

/**
 * Interfaz para servicios de {@link Entrenador} 
 * @author santiago
 *
 */
public interface EntrenadorDAO {

	/**
	 * Éste método buscara un {@link Entrenador} de un nombre en particular
	 * @param nombreEntrenador - el nombre del entrenador a ser buscado 
	 */
	public Entrenador getEntrenador(String nombreEntrenador);
	
	/**
	 * Este método persiste el estado actual de un {@link Entrenador} en una base de datos 
	 * @param entrenador - el {@link Entrenador} a ser persistido
	 */
	public void guardarEntrenador(Entrenador entrenador);
	

	
}
