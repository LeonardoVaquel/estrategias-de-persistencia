package ar.edu.unq.epers.bichomon.backend.service.feed;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evento.Evento;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

public interface FeedService {
	
	/**
	 * Recibe el nombre de un {@link Entrenador} y el nombre de una {@link Ubicacion} para persistir 
	 * un {@link Evento} en una base de datos documental.
	 * 
	 * @param entrenador - String
	 * @param ubicacion - String
	 */
	public void saveAbandono(String entrenador, String ubicacion);
	
	/**
	 * Recibe el nombre de un {@link Entrenador}, el nombre de una {@link Ubicacion} origen y el
	 * nombre de una {@link Ubicacion} de destino para persistir un {@link Evento} en una base 
	 * de datos documental.
	 * 
	 * @param entrenador - String
	 * @param origen - String
	 * @param destino - String
	 */
	public void saveArribo(String entrenador, String origen, String destino);

	/**
	 * Recibe el nombre de un {@link Entrenador}, el nombre de una {@link Ubicacion} y 
	 * el nombre de una {@link Especie} para persistir un {@link Evento} en una base 
	 * de datos documental.
	 * 
	 * @param entrenador - String
	 * @param ubicacion - String
	 * @param especie - String
	 */
	public void saveCaptura(String entrenador, String ubicacion, String especie);

	/**
	 * Recibe el nombre de un {@link Entrenador}, el nombre de una {@link Ubicacion} y 
	 * el nombre de una {@link Especie} para persistir un {@link Evento} en una base de
	 * datos documental.
	 * 
	 * @param entrenador - String
	 * @param ubicacion - String
	 * @param destronado - String
	 */
	public void saveCoronacion(String entrenador, String ubicacion, String destronado);
	
	/**
	 * Recibe el nombre de un {@link Entrenador} Se espera obtener una lista de
	 * todos los eventos relacionados con el parámetro especificado
	 * 
	 * @param entrenador - String
	 * @return - una lista de {@link Evento}
	 */
	public List<Evento> feedEntrenador(String entrenador);

	/**
	 * Recibe el nombre de un {@link Entrenador} Se espera obtener una lista de
	 * todos los eventos relacionados con la {@link Ubicacion} del nombre de
	 * entrenador especificado y aquellas con las que ésta esté conectada.
	 * 
	 * @param entrenador
	 * @return - una lista de {@link Evento}
	 */
	public List<Evento> feedUbicacion(String entrenador);

}
