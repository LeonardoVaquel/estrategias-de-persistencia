package ar.edu.unq.epers.bichomon.backend.service.ranking;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

public interface LeaderBoardService {

	/**
	 * TODO
	 * @return
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
