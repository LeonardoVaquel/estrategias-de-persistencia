package mongoDB;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.mongod.MongoFeedDAO;
import ar.edu.unq.epers.bichomon.backend.model.feed.Feed;
import ar.edu.unq.epers.bichomon.backend.model.feed.evento.Captura;

public class TestMongoDBFeedDAO {

	private MongoFeedDAO dao;
	
	@Before
	public void setUp() {
		this.dao = new MongoFeedDAO();

	}
	
	@After
	public void deleteAll() {
//		this.dao.deleteAll();
	}
	
	@Test
	public void se_guarda_un_evento_y_se_recupera_por_entrenador() {
		
		long fecha1 = System.currentTimeMillis();
		Captura evento1 = new Captura("Dojo-DonBosco", fecha1, "Bottimon");
		
		Feed feed1 = new Feed("Krilin");
		feed1.addEvento(evento1);
		
		this.dao.save(feed1);
		
		Assert.assertNotNull(feed1.getId());
		
	}
	
}
