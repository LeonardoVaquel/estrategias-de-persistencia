package ar.edu.unq.epers.bichomon.backend.service.feed;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.epers.bichomon.backend.dao.mongod.MongoFeedDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evento.Abandono;
import ar.edu.unq.epers.bichomon.backend.model.evento.Arribo;
import ar.edu.unq.epers.bichomon.backend.model.evento.Captura;
import ar.edu.unq.epers.bichomon.backend.model.evento.Coronacion;
import ar.edu.unq.epers.bichomon.backend.model.evento.Evento;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;

/**
 * FeedSessionService provee servicios de feed, conocidos como eventos, representando
 * información de jugadores y ubicaciones
 * @author santiago
 */
public class FeedSessionService implements FeedService {
	
	private GenericService service;
	private MapaService mapaService;
	private MongoFeedDAO feedDAO;
	
	public FeedSessionService(MongoFeedDAO mongoFeedDAO){
		this.feedDAO = mongoFeedDAO;
	}
	
	public FeedSessionService(GenericService service, MapaService mapaService, MongoFeedDAO mongoFeedDAO){
		this.service 	 = service;
		this.mapaService = mapaService;
		this.feedDAO     = mongoFeedDAO;
	}

	/**
	 * Recibe el nombre de un {@link Entrenador} y el nombre de una {@link Ubicacion} para persistir 
	 * un {@link Abandono} en una base de datos documental.
	 * 
	 * @param entrenador - String
	 * @param ubicacion - String
	 */
	public void saveAbandono(String entrenador, String ubicacion) {
		this.feedDAO.save(new Abandono(entrenador, ubicacion));
	}
	
	/**
	 * Recibe el nombre de un {@link Entrenador}, el nombre de una {@link Ubicacion} origen y el
	 * nombre de una {@link Ubicacion} de destino para persistir un {@link Arribo} en una base 
	 * de datos documental.
	 * 
	 * @param entrenador - String
	 * @param origen - String
	 * @param destino - String
	 */
	public void saveArribo(String entrenador, String ubicacion, String ubicacionOrigen) {
		this.feedDAO.save(new Arribo(entrenador, ubicacion, ubicacionOrigen));
	}
	
	/**
	 * Recibe el nombre de un {@link Entrenador}, el nombre de una {@link Ubicacion} y 
	 * el nombre de una {@link Especie} para persistir una {@link Captura} en una base 
	 * de datos documental.
	 * 
	 * @param entrenador - String
	 * @param ubicacion - String
	 * @param especie - String
	 */
	public void saveCaptura(String entrenador, String ubicacion, String especie) {
		this.feedDAO.save(new Captura(entrenador, ubicacion, especie));
	}
	
	public void saveCoronacion(String entrenador, String ubicacion, String destronado) {
		this.feedDAO.save(new Coronacion(entrenador, ubicacion, destronado));
	}
	
	/**
	 * Recibe el nombre de un {@link Entrenador} Se espera obtener una lista de
	 * todos los eventos relacionados con el parámetro especificado
	 * 
	 * @param entrenador - String
	 * @return - una lista de {@link Evento}
	 */
	@Override
	public List<Evento> feedEntrenador(String entrenador) {
		return feedDAO.getEventosByEntrenador(entrenador);
	}

	/**
	 * Recibe el nombre de un {@link Entrenador} Se espera obtener una lista de
	 * todos los eventos relacionados con la {@link Ubicacion} del nombre de
	 * entrenador especificado y aquellas con las que ésta esté conectada.
	 * 
	 * @param entrenador
	 * @return - una lista de {@link Evento}
	 */
	@Override
	public List<Evento> feedUbicacion(String nombreEntrenador) {
		Entrenador entrenador = this.service.recuperarEntidad(Entrenador.class, nombreEntrenador);
		String ubicacionActual = entrenador.getUbicacion().getNombre();		
		List<Ubicacion> ubicaciones = this.mapaService.conectados(ubicacionActual);
		List<String> ubicacionesList = ubicaciones.stream()
											.map((Ubicacion u) -> u.getNombre())
											.collect(Collectors.toList());
		ubicacionesList.add(ubicacionActual);
		return feedDAO.getEventosByUbicaciones(ubicacionesList);
	}
}
