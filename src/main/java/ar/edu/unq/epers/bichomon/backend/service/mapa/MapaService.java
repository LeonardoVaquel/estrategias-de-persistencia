package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

public interface MapaService {

	/**
	 * TODO
	 * @param entrenador
	 * @param ubicacion
	 */
	public void mover(String entrenador, String ubicacion);
	
	/**
	 * TODO
	 * @param ubicacion
	 * @return
	 */
	public int cantidadEntrenadores(String ubicacion);
	
	/**
	 * TODO
	 * @param dojo
	 * @return
	 */
	public Bicho campeon(String dojo);
	
	/**
	 * TODO
	 * @param dojo
	 * @return
	 */
	public Bicho campeonHistorico(String dojo);
}
