package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * MapaSessionService 
 * 
 * @author santiago
 */
public class MapaSessionService {

	
	private MapaService mapaDAO;
	
	/**
	 * Crea una instancia de MapaSessionService que utilizará un dao,
	 * a quien delegará los pedidos necesarios sobre una base de datos.
	 * @param mapaDAO
	 */
	public MapaSessionService(MapaService mapaDAO) {
		
		this.mapaDAO = mapaDAO;
	}
	
	public void mover(String entrenador, String ubicacion) {
		Runner.runInSession(() -> {
			mapaDAO.mover(entrenador, ubicacion);
			return null;
		});
	}
	
	public int cantidadEntrenadores(String ubicacion) {
		return Runner.runInSession(() -> {
			return mapaDAO.cantidadEntrenadores(ubicacion);
		});
	}
	
	public Bicho campeon(String dojo) {
		return Runner.runInSession(() -> {
			return mapaDAO.campeon(dojo);
		});
	}
	
	public Bicho camperonHistorico(String dojo) {
		return Runner.runInSession(() -> {
			return mapaDAO.campeonHistorico(dojo);
		});		
	}
	
	
}
