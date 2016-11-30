package hibernate;

import static org.mockito.ArgumentMatchers.anyInt;
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
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateMapaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.infinispan.ServiceCache;
import ar.edu.unq.epers.bichomon.backend.dao.mongod.MongoFeedDAO;
import ar.edu.unq.epers.bichomon.backend.dao.neo4j.Neo4jMapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Turno;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.model.evento.Abandono;
import ar.edu.unq.epers.bichomon.backend.model.evento.Captura;
import ar.edu.unq.epers.bichomon.backend.model.evento.Coronacion;
import ar.edu.unq.epers.bichomon.backend.model.evento.Evento;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeAbandonarEnUbicacionException;
import ar.edu.unq.epers.bichomon.backend.model.utils.BichomonRandom;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoSessionService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedSessionService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardSessionService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * TestHibernateBichoService es una clase para hacer pruebas con servicios relacionados
 * con la clase {@link Bicho} del juego, en un ambiente persistente.
 * @author santiago
 *
 */
public class TestHibernateBichoService {

	
	private BichoSessionService service;
	private BichoDAO bichoDAO;
	private EntrenadorDAO entrenadorDAO;
	private ExperienciaDAO experienciaDAO;
	private MongoFeedDAO mongoFeedDAO;
	private DataService dataService;
	private GenericService testService;
	private FeedService feedService;
	private MapaSessionService mapaService;
	private HibernateMapaDAO mapaDAO;
	private Neo4jMapaDAO neo4jMapaDAO;
	private LeaderboardSessionService leaderboardService;
	private HibernateLeaderboardDAO leaderboardDAO;
	
	
	@Mock private BichomonRandom dummyRandom;
	private @Mock ServiceCache serviceCache;
	
	
	@Before
	public void prepare() {
		
		MockitoAnnotations.initMocks(this);
		
		this.bichoDAO       	= new HibernateBichoDAO();
		this.entrenadorDAO  	= new HibernateEntrenadorDAO();
		this.experienciaDAO 	= new HibernateExperienciaDAO();
		this.mongoFeedDAO   	= new MongoFeedDAO();
		this.testService    	= new GenericService();
		this.mapaService    	= new MapaSessionService();
		this.feedService    	= new FeedSessionService(testService, mapaService, mongoFeedDAO);
		this.service        	= new BichoSessionService(bichoDAO, entrenadorDAO, experienciaDAO, feedService, serviceCache);
		this.dataService    	= new DataSessionService(new DataManager());
		this.neo4jMapaDAO   	= new Neo4jMapaDAO();
		this.mapaDAO        	= new HibernateMapaDAO();
		this.leaderboardDAO 	= new HibernateLeaderboardDAO();
		this.leaderboardService	= new LeaderboardSessionService(leaderboardDAO, serviceCache);
		
		this.mapaService.setEntrenadorDAO(entrenadorDAO);
		this.mapaService.setFeedService(feedService);
		this.mapaService.setMapaDAO(mapaDAO);
		this.mapaService.setNeo4jMapaDAO(neo4jMapaDAO);
		this.mapaService.setService(testService);
		
		dataService.crearSetDatosIniciales();
		dataService.crearSetDeUbicaciones();
	}
	@After
	public void deleteAll() {
		this.dataService.eliminarTablas();
		this.dataService.eliminarUbicaciones();
		this.mongoFeedDAO.deleteAll();
	}
	
	@Test
	public void dado_un_bicho_este_puede_evolucionar() {
		
		// Bicho = Leomon
		int bichoId = 7;
		Bicho bicho = this.testService.recuperarEntidad(Bicho.class, bichoId);
		
		Runner.runInSession(() -> {
			
			Assert.assertEquals(bicho.getId(), bichoId);
			Assert.assertEquals(bicho.getEnergia(), 250);

			// El entrenador no es necesario
			boolean puedeEvolucionar = this.service.puedeEvolucionar(null, bicho.getId());
			
			Assert.assertTrue(puedeEvolucionar);
			
			return null;
		});
		
	}
	
	@Test
	public void dado_un_bicho_evoluciona_en_otra_especie() {
		
		int bichoId = 7;
		Bicho bicho = this.testService.recuperarEntidad(Bicho.class, bichoId);
		
		Especie especieAEvolucionar = this.testService.recuperarEntidad(Especie.class, bicho.getEspecie().getEvolucion().getNombre());
		
		Runner.runInSession(() -> {
			
			Assert.assertEquals(bicho.getId(), bichoId);
			Assert.assertEquals(bicho.getEspecie().getNombre(), "Leomon");

			// Se testea utilizando un entrenador, sin embargo no es necesario.
			// Desde el modelo, en HibernateBichoServicio es posible refactirozar 
			// la lógica para realizar ésta acción utlizando la referencia de 
			// Entrenador que existe en el bicho a evolucionar.
			Bicho nuevoBicho = this.service.evolucionar("Santiago", bicho.getId());
			
			Assert.assertEquals(nuevoBicho.getEspecie().getNombre(), especieAEvolucionar.getNombre());
			Assert.assertEquals(nuevoBicho.getOwner().getCurrentExp(), 5d, 0);
			
			return null;
		});
	}
	
	@Test
	public void un_entrenador_abandona_un_bicho_en_una_guarderia() {
		
		Runner.runInSession(() -> {

			// 5 -> Bicho.especie = Gisemon
			int bichoId = 5;
			this.service.abandonar("Jackson", bichoId);
			
			Guarderia guarderia = this.testService.recuperarEntidad(Guarderia.class, "Guarderia las 24 horas!");
			Bicho bicho = this.testService.recuperarEntidad(Bicho.class, bichoId);
			
			String nombreUbicacion = guarderia.getNombre();
			List<Evento> feedEntrenador = this.feedService.feedEntrenador("Jackson");
			Abandono abandonoFeedEntrenador = (Abandono) feedEntrenador.get(0);
			
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion("Jackson");
			Abandono abandonoFeedUbicacion = (Abandono) feedUbicacion.get(0);
			
			Assert.assertEquals(guarderia.getBichos().contains(bicho), true);
			Assert.assertEquals(bicho.getOwner(), null);

			Assert.assertEquals("Jackson", abandonoFeedEntrenador.getEntrenador());
			Assert.assertEquals("Abandono", abandonoFeedEntrenador.getTipo());
			Assert.assertEquals(nombreUbicacion, abandonoFeedEntrenador.getUbicacion());
			Assert.assertNotNull(abandonoFeedEntrenador.getFecha());
			
			Assert.assertEquals("Jackson", abandonoFeedUbicacion.getEntrenador());
			Assert.assertEquals("Abandono", abandonoFeedUbicacion.getTipo());
			Assert.assertEquals(nombreUbicacion, abandonoFeedUbicacion.getUbicacion());
			Assert.assertNotNull(abandonoFeedUbicacion.getFecha());
			Assert.assertEquals(1, feedUbicacion.size());
			
			return null;
		});
		
	}
	
	@Test(expected=NoSePuedeAbandonarEnUbicacionException.class)
	public void un_entrenador_abandona_un_bicho_en_una_ubicacion_distinta_a_una_guarderia() {
		
		Runner.runInSession(() -> {

			this.service.abandonar("Santiago", 8);

			return null;
		});
		
	}
	
	@Test
	public void un_entrenador_realiza_una_busqueda_exitosa_en_un_pueblo_y_se_devuelve_un_bicho() {
		
		Runner.runInSession(() -> {

			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Explorador2");
			
			Bicho bicho = this.service.buscar("Explorador2");
			
			String nombreUbicacion = entrenador.getUbicacion().getNombre();
			List<Evento> feedEntrenador = this.feedService.feedEntrenador("Explorador2");
			
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion("Explorador2");
			Captura capturaFeedUbicacion = (Captura) feedUbicacion.get(0);
			
			Assert.assertEquals(bicho.getOwner().getNombre(), entrenador.getNombre());
			Assert.assertEquals(bicho.getEspecie().getNombre(), "Leomon");
			Assert.assertEquals(bicho.getEspecie().getTipo(), TipoBicho.CHOCOLATE);
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			
			Captura captura = (Captura) feedEntrenador.get(0);
			
			Assert.assertEquals("Explorador2", captura.getEntrenador());
			Assert.assertEquals("Captura", captura.getTipo());
			Assert.assertEquals(nombreUbicacion, captura.getUbicacion());
			Assert.assertEquals(bicho.getEspecie().getNombre(), captura.getEspecie());
			Assert.assertNotNull(captura.getFecha());
			
			Assert.assertEquals("Explorador2", capturaFeedUbicacion.getEntrenador());
			Assert.assertEquals("Captura", capturaFeedUbicacion.getTipo());
			Assert.assertEquals(nombreUbicacion, capturaFeedUbicacion.getUbicacion());
			Assert.assertNotNull(capturaFeedUbicacion.getFecha());
			Assert.assertEquals(1, feedUbicacion.size());

			return null;
		});
	}
	
	
	@Test
	public void test_un_entrenador_realiza_una_busqueda_exitosa_en_un_dojo() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			Bicho bicho = this.service.buscar("Santiago");

			String nombreUbicacion = entrenador.getUbicacion().getNombre();
			List<Evento> feedEntrenador = this.feedService.feedEntrenador(entrenador.getNombre());
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion(entrenador.getNombre());
			Captura capturaFeedUbicacion = (Captura) feedUbicacion.get(0);
			
			Assert.assertEquals(bicho.getOwner().getNombre(), entrenador.getNombre());
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			
			Captura captura = (Captura) feedEntrenador.get(0);
			
			Assert.assertEquals("Santiago", captura.getEntrenador());
			Assert.assertEquals("Captura", captura.getTipo());
			Assert.assertEquals(nombreUbicacion, captura.getUbicacion());
			Assert.assertEquals(bicho.getEspecie().getNombre(), captura.getEspecie());
			Assert.assertNotNull(captura.getFecha());
			
			Assert.assertEquals("Santiago", capturaFeedUbicacion.getEntrenador());
			Assert.assertEquals("Captura", capturaFeedUbicacion.getTipo());
			Assert.assertEquals(nombreUbicacion, capturaFeedUbicacion.getUbicacion());
			Assert.assertNotNull(capturaFeedUbicacion.getFecha());
			Assert.assertEquals(1, feedUbicacion.size());
			
			return null;
		});
	}
	
	@Test
	public void un_entrenador_realiza_una_busqueda_exitosa_en_una_guarderia() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Jackson");
			
			when(dummyRandom.nextInt(anyInt())).thenReturn(1);
			entrenador.getUbicacion().setRandom(dummyRandom);
			
			Bicho bicho = this.service.buscar("Jackson");

			String nombreUbicacion = entrenador.getUbicacion().getNombre();
			List<Evento> feedEntrenador = this.feedService.feedEntrenador(entrenador.getNombre());
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion(entrenador.getNombre());
			Captura capturaFeedUbicacion = (Captura) feedUbicacion.get(0);
			
			Assert.assertEquals(bicho.getOwner().getNombre(), entrenador.getNombre());
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			
			Captura captura = (Captura) feedEntrenador.get(0);
			
			Assert.assertEquals(entrenador.getNombre(), captura.getEntrenador());
			Assert.assertEquals("Captura", captura.getTipo());
			Assert.assertEquals(bicho.getEspecie().getNombre(), captura.getEspecie());
			Assert.assertNotNull(captura.getFecha());
			
			Assert.assertEquals("Jackson", capturaFeedUbicacion.getEntrenador());
			Assert.assertEquals("Captura", capturaFeedUbicacion.getTipo());
			Assert.assertEquals(nombreUbicacion, capturaFeedUbicacion.getUbicacion());
			Assert.assertNotNull(capturaFeedUbicacion.getFecha());
			Assert.assertEquals(1, feedUbicacion.size());
			
			return null;
		});
		
	}
	
	@Test
	public void un_entrenador_desafia_a_duelo_a_un_campeon_y_se_obtiene_un_resultado_del_combate_donde_el_bicho_retador_es_campeon() {
		
		Runner.runInSession(() -> {
			
			Dojo dojo = this.testService.recuperarEntidad(Dojo.class, "Torre Karin");
			
			int bichoRetadorId = 8; // GisemonEvolucion
			int bichoRetadoId = 9; // Frutimon
			ResultadoCombate resultadoCombate = this.service.duelo("Vegetal", bichoRetadorId);
			
			String nombreUbicacion = resultadoCombate.getEntrenadorGanador().getUbicacion().getNombre();
			List<Evento> feedEntrenador = this.feedService.feedEntrenador("Vegetal");
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion("Vegetal");
			Coronacion coronacionFeedUbicacion = (Coronacion) feedUbicacion.get(0);
			Coronacion coronacionFeedEntrenador = (Coronacion) feedEntrenador.get(0);
			
			Assert.assertEquals(resultadoCombate.getEntrenadorGanador().getNombre(), "Vegetal");
			Assert.assertEquals(resultadoCombate.getBichoGanador().getId(), bichoRetadorId);
			Assert.assertEquals(resultadoCombate.getBichoGanador().getVictorias(), 101, 0);
			Assert.assertEquals(resultadoCombate.getBichoPerdedor().getId(), bichoRetadoId);
			Assert.assertEquals(resultadoCombate.getEntrenadorGanador().getCurrentExp(), 10d, 0);
			Assert.assertEquals(resultadoCombate.getBichoPerdedor().getOwner().getCurrentExp(), 10d, 0);
			
			List<Turno> turnos = resultadoCombate.getTurnos();
			Assert.assertEquals(turnos.iterator().next().getRetado().getId(), bichoRetadoId);
			Assert.assertEquals(turnos.iterator().next().getRetador().getId(), bichoRetadorId);
			Assert.assertEquals(dojo.getCampeon().getId(), bichoRetadorId);
			
			Assert.assertEquals(resultadoCombate.getEntrenadorGanador().getNombre(), coronacionFeedEntrenador.getEntrenador());
			Assert.assertEquals("Coronacion", coronacionFeedEntrenador.getTipo());
			Assert.assertEquals(nombreUbicacion, coronacionFeedEntrenador.getUbicacion());
			Assert.assertEquals(resultadoCombate.getBichoPerdedor().getOwner().getNombre(), coronacionFeedEntrenador.getEntrenadorDestronado());
			Assert.assertNotNull(coronacionFeedEntrenador.getFecha());
			
			Assert.assertEquals("Vegetal", coronacionFeedUbicacion.getEntrenador());
			Assert.assertEquals("Coronacion", coronacionFeedUbicacion.getTipo());
			Assert.assertEquals(nombreUbicacion, coronacionFeedUbicacion.getUbicacion());
			Assert.assertNotNull(coronacionFeedUbicacion.getFecha());
			Assert.assertEquals(1, feedUbicacion.size());
			
			verify(serviceCache, times(1)).remove("campeones");
			
			// Se pide los campeones entonces como la cache estaba invalidada
			// se hace el hql y la lista de campeones se cache de nuevo.
			List<Entrenador> ls = leaderboardService.campeones();
			
			verify(serviceCache, times(1)).put("campeones",ls);
			
			return null;
		});
	}
	
}
