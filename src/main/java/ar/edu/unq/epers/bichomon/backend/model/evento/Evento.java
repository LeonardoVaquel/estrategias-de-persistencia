package ar.edu.unq.epers.bichomon.backend.model.evento;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

/**
 * Clase que modela eventos para un {@link Entrenador} que serán usados
 * al representar el feed del juego en una base de datos en MongoDB
 * @author santiago
 */
public class Evento {

	@MongoId
	@MongoObjectId
	private String id;
	
	private String entrenador;
	private String tipo;
	private String ubicacion;
	protected String extraProperty;
	private static long fecha = System.currentTimeMillis();
	
	protected Evento() {}
	
	public Evento(String entrenador, String tipo, String ubicacion) {
		this.entrenador = entrenador;
		this.tipo       = tipo;
		this.ubicacion  = ubicacion;
	}
	
	public String getExtraProperty(){
		return this.extraProperty;
	}
	
//	public String getEspecie(){
//		return this.extraProperty;
//	}
//	
//	public String getEntrenadorDerrocado(){
//		return this.extraProperty;
//	}
//	
//	public String getUbicacionOrigen(){
//		return this.extraProperty;
//	}
	
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
