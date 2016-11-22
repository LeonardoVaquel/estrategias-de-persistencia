package ar.edu.unq.epers.bichomon.backend.model.evento;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Clase que modela eventos para un {@link Entrenador} que serán usados
 * al representar el feed del juego en una base de datos en MongoDB
 * @author santiago
 */
@JsonTypeInfo(use=Id.CLASS,property="_tipo")
public class Evento {

	private static long fecha = System.currentTimeMillis();
	
	@MongoId
	@MongoObjectId
	private String id;
	
	private String entrenador;
	private String tipo;
	private String ubicacion;
	
	protected Evento() {}
	
	public Evento(String entrenador, String tipo, String ubicacion) {
		this.entrenador = entrenador;
		this.tipo      = tipo;
		this.ubicacion  = ubicacion;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEntrenador() {
		return entrenador;
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
