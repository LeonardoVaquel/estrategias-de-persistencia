package ar.edu.unq.epers.bichomon.backend.dao.mongod;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.evento.Evento;

public class MongoFeedDAO extends GenericMongoDAO<Evento> {
	
	public MongoFeedDAO() {
		super(Evento.class);
	}
	
	/**
	 * Se espera persistir el objeto especificado por parámetro.
	 * @param {@link Evento} 
	 */
	public void guardarEvento(Evento evento){
		this.save(evento);
	}
	
	/**
	 * Recibe un nombre de {@link Entrenador}
	 * Se espera obtener todos los eventos en los que el entrenador
	 * especificado por parámetro se encuentre involucrado.
	 * @param entrenador - String
	 * @return - List<Evento> ordenada por fecha
	 */
	public List<Evento> getEventosByEntrenador(String entrenador) {
		
		return this.findSorted("{ entrenador: # }", "{ fecha: 1 }", entrenador);
	}
	
	/**
	 * Recibe nombres de {@link Ubicacion}
	 * Se espera obtener todos los eventos que tomaron parte en las ubicaciones
	 * especificadas como parámetro.
	 * @param ubicaciones - List<String>
	 * @return - List<Evento> ordenada por fecha
	 */
	public List<Evento> getEventosByUbicaciones(List<String> ubicaciones) {
		
		return this.findSorted("{ ubicacion: { $in: # } }", "{ fecha: 1 }", ubicaciones);
	}
	
}
