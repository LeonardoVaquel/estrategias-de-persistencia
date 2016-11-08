package ar.edu.unq.epers.bichomon.backend.service.mapa;

import java.util.List;
import java.util.stream.Collectors;


import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.MapaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.neo4j.Neo4jMapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * MapaSessionService 
 * 
 * @author santiago
 */
public class MapaSessionService implements MapaService {

	
	private GenericService service;
	private EntrenadorDAO entrenadorDAO;
	private MapaDAO mapaDAO;
	private Neo4jMapaDAO neo4jMapaDAO;
	
	/**
	 * Crea una instancia de MapaSessionService que utilizará un dao,
	 * a quien delegará los pedidos necesarios sobre una base de datos.
	 * @param mapaDAO
	 */
	public MapaSessionService(MapaDAO mapaDAO, EntrenadorDAO entrenadorDAO, GenericService service, 
			Neo4jMapaDAO neo4jMapaDAO) {
		this.service 	   = service;
		this.mapaDAO	   = mapaDAO;
		this.entrenadorDAO = entrenadorDAO;
		this.neo4jMapaDAO  = neo4jMapaDAO;
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
			
			String desde = entrenador.getUbicacion().getNombre();
			String hasta = ubicacion.getNombre();
			
			Integer costo = this.neo4jMapaDAO.getCostoLindantes(desde, hasta);
			
			entrenador.mover(ubicacion, costo);
			
			return null;
		});		
	}
	
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

	@Override
	public List<Ubicacion> conectados(String nombreUbicacion, String tipoCamino) {
		return Runner.runInSession(() -> {

//			Ubicacion ubicacion   = this.service.recuperarEntidad(Ubicacion.class, nombreUbicacion);
			
//			String unaUbicacion = ubicacion.getNombre();
//			String tipo=ubicacion.; //necesito el tipo
			
			List<String> nombresDeUbicacion = this.neo4jMapaDAO.conectados(nombreUbicacion, tipoCamino); 
			
			
			List<Ubicacion> ubicaciones = nombresDeUbicacion.stream().map((String s) -> 
				this.service.recuperarEntidad(Ubicacion.class, s)).collect(Collectors.toList());
				
		
			return ubicaciones;
		});	
	}
	
	
}
