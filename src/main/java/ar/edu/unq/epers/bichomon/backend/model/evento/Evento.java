package ar.edu.unq.epers.bichomon.backend.model.feed.evento;

import ar.edu.unq.epers.bichomon.backend.model.feed.Feed;

/**
 * Clase que modela eventos para un {@link Entrenador} que serán usados
 * al representar un {@link Feed} para una base de datos en MongoDB
 * @author santiago
 */
public class Evento {

	private String tipo;
	private String ubicacion;
	private long fecha;
	
	protected Evento() {}
	
	public Evento(String tipo, String ubicacion, long fecha) {
		this.tipo      = tipo;
		this.ubicacion = ubicacion;
		this.fecha     = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public long getFecha() {
		return fecha;
	}
}
