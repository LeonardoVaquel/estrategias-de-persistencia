package ar.edu.unq.epers.bichomon.backend.service.leaderboard;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * LeaderbaordSessionService es una clase que modela el comportamiento
 * que un una clase {@link LeaderboardService} tendrá al obtener acceso
 * a una sesión en una base de datos 
 * 
 * @author santiago
 */
public class LeaderboardSessionService {
	
	private LeaderboardService leaderboardService;
	
	/**
	 * Crea una instancia de EspecieSessionService que utilizará un dao,
	 * a quien delegará los pedidos necesarios sobre una base de datos.
	 * @param especieDAO
	 */
	public LeaderboardSessionService(LeaderboardService leaderboardService) {
		this.leaderboardService = leaderboardService;
	}

	/**
	 * Se obtiene una lista de {@link Entrenador} que actualmente posean un {@link Bicho}
	 * que sea campeón de algún Dojo, ordenados de tal manera que las primeras apariciones
	 * sean de aquellos que tienen bichos campeones desde hace mas tiempo
	 * @return - una lista de {@link Entrenador}
	 */
	public List<Entrenador> campeones() {
		return Runner.runInSession(() -> {
			return leaderboardService.campeones();
		});
	}

}

