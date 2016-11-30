package hibernate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateLeaderboardDAO;
import ar.edu.unq.epers.bichomon.backend.dao.infinispan.ServiceCache;
import ar.edu.unq.epers.bichomon.backend.dao.mongod.MongoFeedDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoSessionService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedSessionService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * TestHibernateLeaderbaordService es una clase para hacer pruebas con servicios
 * relacionados con instancias de {@link Entrenador} y {@link Ubicacion} del
 * juego, en un ambiente persistente.
 * 
 * @author santiago
 */
public class TestHibernateLeaderboardService {

	private LeaderboardSessionService service;
	private LeaderboardService hibernateLeaderboardDAO;
	private DataService dataService;
	private GenericService testService;
	private BichoSessionService bichoService;
	private BichoDAO bichoDAO;
	private EntrenadorDAO entrenadorDAO;
	private ExperienciaDAO experienciaDAO;
	private MongoFeedDAO feedDAO;
	private FeedService feedService;
	private @Mock ServiceCache serviceCache;
	
	@Before
	public void prepare() {

		MockitoAnnotations.initMocks(this);
		
		this.hibernateLeaderboardDAO = new HibernateLeaderboardDAO();
		this.service 	 = new LeaderboardSessionService(hibernateLeaderboardDAO, serviceCache);
		this.dataService = new DataSessionService(new DataManager());
		this.testService = new GenericService();

		this.bichoDAO 		= new HibernateBichoDAO();
		this.entrenadorDAO 	= new HibernateEntrenadorDAO();
		this.experienciaDAO = new HibernateExperienciaDAO();
		this.feedDAO        = new MongoFeedDAO();
		this.feedService    = new FeedSessionService(feedDAO);
		this.bichoService 	= new BichoSessionService(bichoDAO, entrenadorDAO, experienciaDAO, feedService, serviceCache);

		dataService.crearSetDatosIniciales();
		
	}
	
	@After
	public void deleteAll() {
		this.dataService.eliminarTablas();
	}

	@Test
	public void se_obtienen_aquellos_entrenadores_que_poseen_un_bicho_que_actualmente_es_campeon_en_orden_descendente_por_fecha_de_victoria() {

		Runner.runInSession(() -> {
			when(serviceCache.get("campeones")).thenReturn(null);
			Entrenador entrenador1 = this.testService.recuperarEntidad(Entrenador.class, "Vegetal");

			Bicho bicho1 = this.testService.recuperarEntidad(Bicho.class, 8);

			this.bichoService.duelo(entrenador1.getNombre(), bicho1.getId());

			List<Entrenador> entrenadores = this.service.campeones();

			Assert.assertEquals(4, entrenadores.size());
			Assert.assertEquals(entrenadores.get(0).getNombre(), "Santiago");
			Assert.assertEquals(entrenadores.get(1).getNombre(), "Jackson");
			Assert.assertEquals(entrenadores.get(2).getNombre(), "Jackson");
			Assert.assertEquals(entrenadores.get(3).getNombre(), "Vegetal");
			
			verify(serviceCache, times(1)).put("campeones", entrenadores);

			return null;
		});

	}

	@Test
	public void se_obtienen_los_entrenadores_campeones_desde_la_cache(){
		
		Runner.runInSession(()-> {
			
			List<Entrenador> entrenadores = this.service.campeones();
			
			when(serviceCache.get("campeones")).thenReturn(entrenadores);
			
			List<Entrenador> entrenadoresCache = this.service.campeones();
			
			// put es llamado solamente una vez al asignar la variable 'entrenadores',
			// en el excersice no es tenido en cuenta
			// get es llamado dos veces, en la asignación de la variable retornando null
			// y por segunda vez en el excersice retornando una lista
			verify(serviceCache, times(1)).put("campeones", entrenadores);
			verify(serviceCache, times(2)).get("campeones");
			
			Assert.assertNotNull(entrenadoresCache);
			Assert.assertEquals(3, entrenadoresCache.size());
			
			return null;
		});
	}
	
}
