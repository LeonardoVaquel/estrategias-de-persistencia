package ar.edu.unq.epers.bichomon.backend.service.feed;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.mongod.MongoFeedDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.evento.Abandono;
import ar.edu.unq.epers.bichomon.backend.model.evento.Arribo;
import ar.edu.unq.epers.bichomon.backend.model.evento.Captura;
import ar.edu.unq.epers.bichomon.backend.model.evento.Coronacion;
import ar.edu.unq.epers.bichomon.backend.model.evento.Evento;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;

/**
 * FeedSessionService provee servicios de feed, conocidos como eventos, representando
 * información de jugadores y ubicaciones
 * @author santiago
 */
public class FeedSessionService implements FeedService {
	
	private EntrenadorDAO entrenadorDAO;
	private MapaService mapaService;
	private MongoFeedDAO feedDAO;
	
	public FeedSessionService(MongoFeedDAO mongoFeedDAO){
		this.feedDAO = mongoFeedDAO;
	}
	
	public FeedSessionService(EntrenadorDAO entrenadorDAO, MapaService mapaService, MongoFeedDAO mongoFeedDAO){
		this.entrenadorDAO = entrenadorDAO;
		this.mapaService   = mapaService;
		this.feedDAO       = mongoFeedDAO;
	}

	public void saveAbandono(String entrenador, String ubicacion) {
		this.feedDAO.save(new Abandono(entrenador, ubicacion));
	}
	
	public void saveArribo(String entrenador, String ubicacion, String ubicacionOrigen) {
		this.feedDAO.save(new Arribo(entrenador, ubicacion, ubicacionOrigen));
	}
	
	public void saveCaptura(String entrenador, String ubicacion, String especie) {
		this.feedDAO.save(new Captura(entrenador, ubicacion, especie));
	}
	
	public void saveCoronacion(String entrenador, String ubicacion, String destronado) {
		this.feedDAO.save(new Coronacion(entrenador, ubicacion, destronado));
	}
	
	@Override
	public List<Evento> feedEntrenador(String entrenador) {
		return feedDAO.getEventosByEntrenador(entrenador);
	}

	@Override
	public List<Evento> feedUbicacion(String nombreEntrenador) {
		
		Entrenador entrenador = this.entrenadorDAO.getEntrenador(nombreEntrenador);
		
		List<Ubicacion> ubicaciones = this.mapaService.conectados(entrenador.getUbicacion().getNombre(), "");
		
		List<String> ubicacionesList;
		
		return feedDAO.getEventosByUbicaciones(ubicacionesList);
	}
}
