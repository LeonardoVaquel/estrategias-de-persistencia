package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

/**
 * MapaService es una interfaz provista por los encargados frontend
 * para obtener servicios sobre las ubicaciones del juego
 * 
 * @author santiago
 */
public interface MapaService {

	/**
	 * Recibe por parámetro un nombre de {@link Entrenador} y un nombre de {@link Ubicacion}
	 * Se espera que el entrenador obtenga su {@link Ubicacion} modificada a la que
	 * se especifica por parámetro.
	 * @param entrenador - el nombre de un {@link Entrenador}
	 * @param ubicacion - el nombre de una {@link Ubicacion}
	 */
	public void mover(String entrenador, String ubicacion);
	
	/**
	 * Recibe por parámetro el nombre de una {@link Ubicacion} para devolver la cantidad de
	 * entrenadores que estén ubicados allí actualmente
	 * @param ubicacion - el nombre de una {@link Ubicacion}
	 * @return un número que representa una cantidad de entrenadores
	 */
	public int cantidadEntrenadores(String ubicacion);
	
	/**
	 * Recibe por parámetro el nombre de una {@link Ubicacion} para devolver el actual {@link Bicho} 
	 * campeón de dicha instancia.
	 * Se espera que la ubicación especificada sea de tipo {@link Dojo}
	 * @param dojo - el nombre de una {@link Ubicacion} de tipo {@link Dojo}
	 * @return una instancia de {@link Bicho}
	 */
	public Bicho campeon(String dojo);
	
	/**
	 * TODO
	 * @param dojo
	 * @return
	 */
	public Bicho campeonHistorico(String dojo);
}
