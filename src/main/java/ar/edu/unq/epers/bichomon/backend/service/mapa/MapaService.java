package ar.edu.unq.epers.bichomon.backend.service.mapa;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

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
	public void mover(String entrenador, String ubicacion) throws RuntimeException;
	
	/**
	 * Recibe por parámetro un nombre de {@link Ubicacion}
	 * Se espera obtener la cantidad de objetos {@link Entrendor} en dicha ubicación.
	 * @param ubicacion - el nombre de una {@link Ubicacion}
	 * @return - un número que representa una cantidad de entrenadores.
	 */
	public int cantidadEntrenadores(String ubicacion);
	
	/**
	 * Recibe por parámetro un nombre de {@link Entrenador} y un nombre de {@link Ubicacion} para
	 * mover al entrenador hacia la ubicación mediante el camino mas corto.
	 * Se espera que el entrenador obtenga su {@link Ubicacion} modificada a la que
	 * se especifica por parámetro.
	 * @param entrenador - el nombre de un {@link Entrenador}
	 * @param ubicacion - el nombre de una {@link Ubicacion}
	 */
	public void moverMasCorto(String entrenador, String ubicacion);

	/**
	 * Recibe por parámetro el nombre de una {@link Ubicacion} y un tipo de camino.
	 * Se espera devolver una lista de {@link Ubicacion} las cuales se encuentran conectadas 
	 * directamente con aquella pasada como parámetro.
	 * @param ubicacion - un string
	 * @param tipoCamino - un string
	 * @return una lista de {@link Ubicacion}
	 */
	public List<Ubicacion> conectados(String ubicacion, String tipoCamino);
	
	/**
	 * Recibe una instancia de {@link Ubicacion} para ser persistida en una base de datos en Hibernate y Neo4j
	 * @param ubicacion - una instancia de {@link Ubicacion}
	 */
	public void crearUbicacion(Ubicacion ubicacion);
	
	/**
	 * Recibe como parámetro dos nombres de {@link UBicacion} y un tipo de camino.
	 * Se espera que ambas ubicaciones mantengan una relación con el camino especificado por parámetro,
	 * @param ubicacion1 - string
	 * @param ubicacion2 - string
	 * @param tipoCamino - string
	 */
	public void conectar(String ubicacion1, String ubicacion2, String tipoCamino);
	
}
