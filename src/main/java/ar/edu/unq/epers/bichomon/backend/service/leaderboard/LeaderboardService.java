package ar.edu.unq.epers.bichomon.backend.service.leaderboard;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

/**
 * LeaderboardService es una interfaz provista por los encargados frontend
 * para obtener servicios sobre entrenadores y especies relacionados con un ranking
 * 
 * @author santiago
 */
public interface LeaderboardService {

	/**
	 * Se espera retornar aquellos campeones que posean un bicho que actualmente
	 * sea campeón de un dojo, ordenados de tal manera que las primeras apariciones reflejaran
	 * aquellos que ocupan el puesto de campeón desde hace mas tiempo  
	 * @return - una lista de {@link Entrenador}
	 */
	public List<Entrenador> campeones();
	
	/**
	 * TODO
	 * @return
	 */
	public Especie especieLider();
	
	/**
	 * TODO
	 * @return
	 */
	public List<Entrenador> lideres();
}
