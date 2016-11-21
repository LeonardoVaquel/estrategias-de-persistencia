package ar.edu.unq.epers.bichomon.backend.service.mapa;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.MapaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.neo4j.Neo4jMapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * MapaSessionService es una clase que provee servicios de ubicacion.
 * @author santiago
 */
public class MapaSessionService implements MapaService {

	private GenericService service;
	private EntrenadorDAO entrenadorDAO;
	private MapaDAO mapaDAO;
	private Neo4jMapaDAO neo4jMapaDAO;
	private FeedService feedService;
	
	/**
	 * Crea una instancia de MapaSessionService que utilizará un dao,
	 * a quien delegará los pedidos necesarios sobre una base de datos.
	 * @param mapaDAO
	 */
	public MapaSessionService(MapaDAO mapaDAO, EntrenadorDAO entrenadorDAO, GenericService service, Neo4jMapaDAO neo4jMapaDAO, FeedService feedService) {
		this.service 	   = service;
		this.mapaDAO	   = mapaDAO;
		this.entrenadorDAO = entrenadorDAO;
		this.neo4jMapaDAO  = neo4jMapaDAO;
		this.feedService   = feedService;
	}
	
	/**
	 * Dado un nombre de {@link Entrenador} y un nombre de {@link Ubicacion} se mueve al entrenador
	 * a la ubicación especificada.
	 * Se espera que la {@link Ubicacion} de un entrenador se modifique por la del nombre pasado
	 * como parámetro
	 */
	@Override
	public void mover(String nombreEntrenador, String nombreUbicacion) {
		Runner.runInSession(() -> {
		
			Entrenador entrenador = this.entrenadorDAO.getEntrenador(nombreEntrenador);
			Ubicacion ubicacion   = this.service.recuperarEntidad(Ubicacion.class, nombreUbicacion);
			
			String ubicacionOrigen = entrenador.getUbicacion().getNombre();
			
			Integer costo = this.neo4jMapaDAO.getCostoLindantes(ubicacionOrigen, nombreUbicacion);
			
			entrenador.mover(ubicacion, costo);
			
			this.feedService.saveArribo(nombreEntrenador, nombreUbicacion, ubicacionOrigen);
			
			return null;
		});		
	}
	
	/**
	 * Dado un nombre de {@link Entrenador} y un nombre de {@link Ubicacion} se mueve a dicho entrenador a la ubicacion
	 * pasada como parámetro.
	 * En caso que el entrenador no cuente con monedas suficientes para realizar el movimiento, 
	 * se levantará una excepción {@link CaminoMuyCostoso}
	 */
	@Override
	public void moverMasCorto(String nombreEntrenador, String nombreUbicacion) {
		
			Runner.runInSession(() -> {
			
			Entrenador entrenador = this.entrenadorDAO.getEntrenador(nombreEntrenador);
			Ubicacion ubicacion   = this.service.recuperarEntidad(Ubicacion.class, nombreUbicacion);
			
			String desde = entrenador.getUbicacion().getNombre();
			String hasta = ubicacion.getNombre();
			
			Integer costo = this.neo4jMapaDAO.getCostoEntreUbicaciones(desde, hasta);
			
			entrenador.mover(ubicacion, costo);
			
			return null;
		});
	}
	
	/**
	 * Dado un nombre de {@link Ubicacion} se obtiene la cantidad de entrenadores
	 * que se encuentran actualmente en la ubicación especificada
	 */
	@Override
	public int cantidadEntrenadores(String nombreUbicacion) {
		return Runner.runInSession(() -> {
			return this.mapaDAO.cantidadEntrenadores(nombreUbicacion);
		});
	}

	/**
	 * Recibe por parámetro el nombre de una {@link Ubicacion} y un tipo de camino.
	 * Se espera devolver una lista de {@link Ubicacion} las cuales se encuentran conectadas 
	 * directamente con aquella pasada como parámetro.
	 * @param ubicacion - un string
	 * @param tipoCamino - un string
	 * @return una lista de {@link Ubicacion}
	 */
	@Override
	public List<Ubicacion> conectados(String nombreUbicacion, String tipoCamino) {
		return Runner.runInSession(() -> {

			List<String> nombresDeUbicacion = this.neo4jMapaDAO.conectados(nombreUbicacion, tipoCamino); 
			
			return this.mapaDAO.getUbicacionesDeNombre(nombresDeUbicacion);
		});	
	}

	/**
	 * Recibe una instancia de {@link Ubicacion} para ser persistida en una base de datos en 
	 * Hibernate y Neo4j
	 * @param ubicacion - una instancia de {@link Ubicacion}
	 */
	@Override
	public void crearUbicacion(Ubicacion ubicacion) {
		
		Runner.runInSession(()-> {
			
			this.service.crearEntidad(ubicacion);
			this.neo4jMapaDAO.crearUbicacion(ubicacion);
			return null;
		});
	}
	
	/**
	 * Recibe dos nombres de {@link Ubicacion} y un tipo de camino
	 * Se espera que las ubicaciones estén relacionadas por el tipo de camino dado, 
	 * en una base de datos en Neo4j
	 */
	@Override
	public void conectar(String nombreUbicacion1, String nombreUbicacion2, String tipoCamino) {
		Runner.runInSession(()-> {
			this.neo4jMapaDAO.conectar(nombreUbicacion1, nombreUbicacion2, tipoCamino);
			return null;
		});
	}
	
}
