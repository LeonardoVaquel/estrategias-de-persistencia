package hibernate;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateLeaderboardDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.TestService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoSessionService;
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
	private BichoSessionService bichoService;
	private BichoDAO hibernateBichoDAO;
	
	@Before
	public void prepare() {
		
		this.hibernateLeaderboardDAO = new HibernateLeaderboardDAO();
		this.service = new LeaderboardSessionService(hibernateLeaderboardDAO);
		this.dataService = new DataSessionService(new DataManager());
		this.testService = new TestService();
		
		this.hibernateBichoDAO = new HibernateBichoDAO();
		this.bichoService = new BichoSessionService(hibernateBichoDAO);
		
		dataService.crearSetDatosIniciales();
	}
	
	
	@After
	public void deleteAll() {
		//this.dataService.eliminarTablas();
	}
	
	@Test
	public void se_obtienen_aquellos_entrenadores_que_poseen_un_bicho_que_actualmente_es_campeon_en_orden_descendente_por_fecha_de_victoria() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador1 = this.testService.recuperarEntidad(Entrenador.class, "Vegetal");
			Entrenador entrenador2 = this.testService.recuperarEntidad(Entrenador.class, "Explorador7");
			
			Bicho bicho1 = this.testService.recuperarEntidad(Bicho.class, 8);
			Bicho bicho2 = this.testService.recuperarEntidad(Bicho.class, 6);
			
			
			this.bichoService.duelo("Vegetal", 8);
			this.bichoService.duelo("Explorador7", 6);
			
			List<Entrenador> entrenadores = this.service.campeones();
			
			Assert.assertEquals(3, entrenadores.size());
			Assert.assertEquals(entrenadores.get(0).getNombre(), "Santiago");
			Assert.assertEquals(entrenadores.get(1).getNombre(), "Jackson");
			Assert.assertEquals(entrenadores.get(2).getNombre(), "Explorador7");
			
			return null;
		});
		
	}
	
	
	
}
