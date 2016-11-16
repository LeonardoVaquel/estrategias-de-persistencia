package ar.edu.unq.epers.bichomon.backend.model.feed;

import java.util.ArrayList;
import java.util.List;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import ar.edu.unq.epers.bichomon.backend.model.feed.evento.Evento;

/**
 * Feed es una clase que representa un modelo de log de eventos
 * para una base documental en MongoDB
 * @author santiago
 */
public class Feed {

	@MongoId
	@MongoObjectId
	private String id;
	
	private String entrenador;
	private List<Evento> eventos = new ArrayList<>();
	
	protected Feed() { }
	
	public Feed(String entrenador) {
		this.entrenador = entrenador;
	}

	public String getId() {
		return id;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public List<Evento> getEventos() {
		return eventos;
	}
	
	public void addEvento(Evento evento) {
		this.eventos.add(evento);
	}
	
	
}
