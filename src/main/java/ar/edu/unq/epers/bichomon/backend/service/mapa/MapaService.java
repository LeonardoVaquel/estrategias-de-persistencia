package ar.edu.unq.epers.bichomon.backend.service.mapa;

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
	 * Recibe por parámetro un nombre de {@link Ubicacion}
	 * Se espera obtener la cantidad de objetos {@link Entrendor} en dicha ubicación.
	 * @param ubicacion - el nombre de una {@link Ubicacion}
	 * @return - un número que representa una cantidad de entrenadores.
	 */
	public int cantidadEntrenadores(String ubicacion);
	
}
