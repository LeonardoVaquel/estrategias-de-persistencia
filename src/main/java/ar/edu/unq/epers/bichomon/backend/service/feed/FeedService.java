package ar.edu.unq.epers.bichomon.backend.service.feed;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.feed.Feed;

public interface FeedService {
	
	/**
	 * Recibe el nombre de un {@link Entrenador}
	 * Se espera obtener una lista de todos los eventos relacionados con el parámetro especificado
	 * @param entrenador - string
	 * @return - una lista de {@link Evento}
	 */
	public List<Feed> feedEntrenador(String entrenador);
	
	/**
	 * Recibe el nombre de un {@link Entrenador}
	 * Se espera obtener una lista de todos los eventos relacionados con la {@link Ubicacion} del
	 * nombre de entrenador especificado y aquellas con las que ésta esté conectada.
	 * @param entrenador
	 * @return - una lista de {@link Evento}
	 */
	public List<Feed> feedUBicacion(String entrenador);
	
}
