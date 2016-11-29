package hibernate;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.MapaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateMapaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.infinispan.ServiceCache;
import ar.edu.unq.epers.bichomon.backend.dao.mongod.MongoFeedDAO;
import ar.edu.unq.epers.bichomon.backend.dao.neo4j.Neo4jMapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.evento.Arribo;
import ar.edu.unq.epers.bichomon.backend.model.evento.Evento;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.CaminoCosto;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.feed.FeedSessionService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.CaminoMuyCostoso;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaSessionService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.UbicacionMuyLejana;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * TestHibernateLeaderbaordService es una clase para hacer pruebas con servicios relacionados
 * con la {@link Ubicacion} de un {@link Entrenador} en el juego, en un ambiente persistente.
 * 
 * @author santiago
 */
public class TestHibernateMapaService {

	
	private MapaSessionService service;
	private MapaDAO mapaDAO;
	private EntrenadorDAO entrenadorDAO;
	private DataService dataService;
	private GenericService testService;
	private Neo4jMapaDAO neo4jmapaDAO;
	private MongoFeedDAO mongoFeedDAO;
	private FeedService feedService;
	private MapaSessionService mapaService;
	@Mock private ServiceCache dummyMapaCache;
	
	@Before
	public void prepare() {
		
		MockitoAnnotations.initMocks(this);
		
		this.mapaDAO 	   = new HibernateMapaDAO();
		this.entrenadorDAO = new HibernateEntrenadorDAO();
		this.testService   = new GenericService();
		this.neo4jmapaDAO  = new Neo4jMapaDAO();
		this.mongoFeedDAO  = new MongoFeedDAO();
		this.mapaService   = new MapaSessionService();
		this.feedService   = new FeedSessionService(testService, mapaService, mongoFeedDAO);
		this.service 	   = new MapaSessionService(this.mapaDAO, this.entrenadorDAO, this.testService, this.neo4jmapaDAO, this.feedService, this.dummyMapaCache);
		this.dataService   = new DataSessionService(new DataManager());
		
		this.mapaService.setEntrenadorDAO(entrenadorDAO);
		this.mapaService.setFeedService(feedService);
		this.mapaService.setMapaDAO(mapaDAO);
		this.mapaService.setNeo4jMapaDAO(neo4jmapaDAO);
		this.mapaService.setService(testService);
		
		this.dataService.crearSetDatosIniciales();
		this.dataService.crearSetDeUbicaciones();
	}
	
	
	@After
	public void deleteAll() {
		this.dataService.eliminarTablas();
		this.neo4jmapaDAO.eliminarUbicaciones();
		this.mongoFeedDAO.deleteAll();
	}
	
	@Test
	public void dado_un_entrenador_y_una_ubicacion_se_mueve_al_entrenador_a_la_ubicacion_especificada() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			Integer monedas = entrenador.getMonedas();
			
			this.service.mover("Santiago", "Neverland");
			
			List<Evento> feedEntrenador = this.feedService.feedEntrenador(entrenador.getNombre());
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion(entrenador.getNombre());
			
			Arribo arriboFeedEntrenador = (Arribo) feedEntrenador.get(0);
			Arribo arriboFeedUbicacion  = (Arribo) feedUbicacion.get(0);
			
			Assert.assertEquals(entrenador.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador.getMonedas(), monedas - CaminoCosto.MARITIMO.getValue(), 0);
			
			Assert.assertEquals("Santiago", arriboFeedEntrenador.getEntrenador());
			Assert.assertEquals("Neverland", arriboFeedEntrenador.getUbicacion());
			Assert.assertEquals("Arribo", arriboFeedEntrenador.getTipo());
			
			Assert.assertEquals("Santiago", arriboFeedUbicacion.getEntrenador());
			Assert.assertEquals("Neverland", arriboFeedUbicacion.getUbicacion());
			Assert.assertEquals("Arribo", arriboFeedUbicacion.getTipo());
			
			verify(dummyMapaCache, times(1)).incrementValue(arriboFeedEntrenador.getUbicacion(), 1);
			verify(dummyMapaCache, times(1)).decrementValue(arriboFeedEntrenador.getUbicacionOrigen(), 1);
			
			return null;
		});
	}
	
	@Test(expected=CaminoMuyCostoso.class)
	public void dada_una_ubicacion_el_entrenador_no_puede_mover_por_monedas_insuficientes() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			entrenador.setMonedas(1);
			
			this.service.mover("Santiago", "Neverland");
			
			return null;
		});
	}
	
	@Test(expected=UbicacionMuyLejana.class)
	public void dada_una_ubicacion_muy_lejana_el_entrenador_no_puede_mover() {
		
		Runner.runInSession(() -> {
			
			this.service.mover("Santiago", "Quilmes-Dojo");
			
			return null;
		});
	}
	
	@Test
	public void una_ubicacion_nerverland_se_conecta_con_otras_dos_ubicaciones_por_un_camino_maritimo() {
		
		Runner.runInSession(() -> {
			
			List<Ubicacion> listaDeUbicaciones = this.service.conectados("Neverland", "MARITIMO");
			
			Assert.assertEquals(2, listaDeUbicaciones.size(), 0);
			Assert.assertEquals(listaDeUbicaciones.get(0).getNombre(), "Bernal-Dojo");
			Assert.assertEquals(listaDeUbicaciones.get(1).getNombre(), "Quilmes-Dojo");
			
			return null;
		});
	}
	
	@Test
	public void dado_un_entrenador_y_una_ubicacion_no_lindante_se_mueve_al_entrenador_a_dicha_ubicacion_y_se_restan_sus_monedas(){
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			Integer monedas = entrenador.getMonedas();
			
			this.service.moverMasCorto("Santiago", "Quilmes-Dojo");
			
			Assert.assertEquals(entrenador.getUbicacion().getNombre(), "Quilmes-Dojo");
			Assert.assertEquals(entrenador.getMonedas(), monedas - 4, 0);
			
			return null;
		});
		
	}
	
	@Test
	public void dada_una_ubicacion_se_obtiene_la_cantidad_de_entrenadores_que_se_encuentran_inicialmente_en_la_ubicacion_especificada_y_se_aloja_en_una_cache() {
	
		Runner.runInSession(() -> {
			
			when(dummyMapaCache.get("Neverland")).thenReturn(null);
			
			Entrenador entrenador1 = this.testService.recuperarEntidad(Entrenador.class, "Explorador1");
			Entrenador entrenador2 = this.testService.recuperarEntidad(Entrenador.class, "Explorador2");
			Entrenador entrenador3 = this.testService.recuperarEntidad(Entrenador.class, "Explorador3");
			
			int cantidadDeEntrenadores = this.service.cantidadEntrenadores("Neverland");
			
			Assert.assertEquals(3, cantidadDeEntrenadores);
			Assert.assertEquals(entrenador1.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador2.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador3.getUbicacion().getNombre(), "Neverland");

			verify(dummyMapaCache, times(1)).get("Neverland");
			verify(dummyMapaCache, times(1)).put("Neverland", cantidadDeEntrenadores);
			
			return null;
		});
	}

	@Test
	public void dada_una_ubicacion_se_obtiene_la_cantidad_de_entrenadores_de_una_cache() {	
		Runner.runInSession(() -> {
			
			when(dummyMapaCache.get("Neverland")).thenReturn(3);
			
			Entrenador entrenador1 = this.testService.recuperarEntidad(Entrenador.class, "Explorador1");
			Entrenador entrenador2 = this.testService.recuperarEntidad(Entrenador.class, "Explorador2");
			Entrenador entrenador3 = this.testService.recuperarEntidad(Entrenador.class, "Explorador3");
			
			int cantidadDeEntrenadores = this.service.cantidadEntrenadores("Neverland");
			
			Assert.assertEquals(3, cantidadDeEntrenadores);
			Assert.assertEquals(entrenador1.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador2.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador3.getUbicacion().getNombre(), "Neverland");

			verify(dummyMapaCache, times(0)).put(anyString(), anyInt());

			return null;
		});
	}	
	
	@Test
	public void dado_un_dojo_se_obtiene_el_campeon_actual() {
		
		Runner.runInSession(() -> {
			
			Bicho bicho = this.mapaDAO.campeon("Torre Karin");
			Dojo dojo = this.testService.recuperarEntidad(Dojo.class, "Torre Karin");
			
			Assert.assertEquals(bicho.getId(), 9);
			Assert.assertEquals(dojo.getCampeon().getId(), bicho.getId());
			Assert.assertEquals(dojo.getCampeon().getId(), 9);
			
			return null;
		});	
	}
	
	@Test
	public void dada_una_lista_de_string_se_obtiene_una_lista_de_ubicaciones() {

		List<String> nombresDeUbicacion = new ArrayList<>();
		nombresDeUbicacion.add("Neverland");
		nombresDeUbicacion.add("Torre Karin");
		nombresDeUbicacion.add("Quilmes-Dojo");
		
		Runner.runInSession(() -> {
			
			List<Ubicacion> ubicaciones = this.mapaDAO.getUbicacionesDeNombre(nombresDeUbicacion);
			
			Assert.assertEquals(3, ubicaciones.size());
			Assert.assertEquals("Neverland", 	ubicaciones.get(0).getNombre());
			Assert.assertEquals("Quilmes-Dojo",	ubicaciones.get(1).getNombre());
			Assert.assertEquals("Torre Karin",  ubicaciones.get(2).getNombre());
			
			return null;
		});
	}
}
