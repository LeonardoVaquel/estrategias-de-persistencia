package ar.edu.unq.epers.bichomon.backend.dao.mongod;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.feed.Feed;

public class MongoFeedDAO extends GenericMongoDAO<Feed> {
	
	public MongoFeedDAO() {
		super(Feed.class);
	}
	
	public List<Feed> getByEntrenador(String entrenador) {
		
		return this.find("{Entrenador: #}", entrenador);
	}

}
