package hibernate;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateLeaderboardDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.TestService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * TestHibernateLeaderbaordService es una clase para hacer pruebas con servicios relacionados
 * con instancias de {@link Entrenador} y {@link Ubicacion} del juego, en un ambiente persistente.
 * 
 * @author santiago
 */
public class TestHibernateLeaderboardService {

	private LeaderboardSessionService service;
	private LeaderboardService hibernateLeaderboardDAO;
	private DataService dataService;
	private TestService testService;	
	
	@Before
	public void prepare() {
		
		this.hibernateLeaderboardDAO = new HibernateLeaderboardDAO();
		this.service = new LeaderboardSessionService(hibernateLeaderboardDAO);
		this.dataService = new DataSessionService(new DataManager());
		this.testService = new TestService();
		
		dataService.crearSetDatosIniciales();
	}
	
	
	@After
	public void deleteAll() {

	}
	
	@Test
	public void se_obtienen_aquellos_entrenadores_que_poseen_un_bicho_que_actualmente_es_campeon_en_orden_descendente_por_fecha_de_victoria() {
		
		Runner.runInSession(() -> {
			
			List<Entrenador> entrenadores = this.service.campeones();
			
			System.out.println(entrenadores);
			
			Assert.assertEquals(3, entrenadores.size());
			Assert.assertEquals(entrenadores.get(0).getNombre(), "Santiago");
			Assert.assertEquals(entrenadores.get(1).getNombre(), "Jackson");
			Assert.assertEquals(entrenadores.get(2).getNombre(), "Jackson");
			
			return null;
		});
		
	}
	
	
	
}
