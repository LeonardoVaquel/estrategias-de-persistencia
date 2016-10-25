package ar.edu.unq.epers.bichomon.backend.model.buscador;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public interface BusquedaEnUbicacion {

	/**
	 * Signatura que evalúa si un {@link Entrenador} realiza una búsqueda exitosa en una {@link Ubicación}
	 * @param entrenador
	 * @return boolean indicando si la búsqueda fue exitosa
	 */
	public boolean esBusquedaExitosa(Entrenador entrenador);
}
