package mongoDB;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.mongod.MongoFeedDAO;
import ar.edu.unq.epers.bichomon.backend.model.evento.Abandono;
import ar.edu.unq.epers.bichomon.backend.model.evento.Captura;
import ar.edu.unq.epers.bichomon.backend.model.evento.Coronacion;
import ar.edu.unq.epers.bichomon.backend.model.evento.Evento;

public class TestMongoDBFeedDAO {

	private MongoFeedDAO dao;
	
	@Before
	public void setUp() {
		this.dao = new MongoFeedDAO();

		Evento evento1 = new Abandono("Vegeta", "Guarderia24Hrs");
		Captura evento2 = new Captura("Vegeta", "Dojo DonBosco", "Charmander");
		Evento evento3 = new Abandono("Vegeta", "GuarderiaDeFuego");
		Evento evento4 = new Coronacion("Vegeta", "Dojo DonBosco", "Krilin");
		
		this.dao.save(evento1);
		this.dao.save(evento2);
		this.dao.save(evento3);
		this.dao.save(evento4);
		
	}
	
	@After
	public void deleteAll() {
		this.dao.deleteAll();
	}
	
	@Test
	public void se_recuperan_eventos_por_nombre_de_entrenador() {
		
		List<Evento> eventos = this.dao.getEventosByEntrenador("Vegeta");
		Assert.assertNotNull(eventos.get(0).getId());
		Assert.assertNotNull(eventos.get(1).getId());
		Assert.assertNotNull(eventos.get(2).getId());
		Assert.assertNotNull(eventos.get(3).getId());
		Assert.assertEquals(eventos.get(0).getUbicacion(), "Guarderia24Hrs");
		Assert.assertEquals(eventos.get(1).getUbicacion(), "Dojo DonBosco");
		Assert.assertEquals(eventos.get(2).getUbicacion(), "GuarderiaDeFuego");
		Assert.assertEquals(eventos.get(3).getUbicacion(), "Dojo DonBosco");
		Assert.assertEquals(4, eventos.size());
	}
	
	@Test
	public void dada_una_lista_de_ubicaciones_se_recuperan_eventos_que_ocurren_en_estas(){
		List<String> ubicaciones = new ArrayList<>();
		ubicaciones.add("Dojo DonBosco");
		ubicaciones.add("GuarderiaDeFuego");
		
		List<Evento> eventos = this.dao.getEventosByUbicaciones(ubicaciones);
		
		Assert.assertNotNull(eventos.get(0));
		Assert.assertNotNull(eventos.get(1));
		Assert.assertNotNull(eventos.get(2));
		Assert.assertEquals(3, eventos.size());
		Assert.assertEquals("Dojo DonBosco", eventos.get(0).getUbicacion());
		Assert.assertEquals("GuarderiaDeFuego", eventos.get(1).getUbicacion());
		Assert.assertEquals("Dojo DonBosco", eventos.get(2).getUbicacion());
		
	}
	
}

