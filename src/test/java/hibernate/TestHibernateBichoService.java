package hibernate;

import static org.mockito.ArgumentMatchers.anyInt;
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
import ar.edu.unq.epers.bichomon.backend.dao.mongod.MongoFeedDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Turno;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
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
	
	@Mock private BichomonRandom dummyRandom;
	
	@Before
	public void prepare() {
		
		MockitoAnnotations.initMocks(this);
		
		this.bichoDAO = new HibernateBichoDAO();
		this.entrenadorDAO = new HibernateEntrenadorDAO();
		this.experienciaDAO = new HibernateExperienciaDAO();
		this.mongoFeedDAO = new MongoFeedDAO();
		this.feedService = new FeedSessionService(mongoFeedDAO);
		this.service = new BichoSessionService(bichoDAO, entrenadorDAO, experienciaDAO, feedService);
		this.dataService = new DataSessionService(new DataManager());
		this.testService = new GenericService();
		
		dataService.crearSetDatosIniciales();
		
	}
	@After
	public void deleteAll() {
		this.dataService.eliminarTablas();
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
			
			Assert.assertEquals(guarderia.getBichos().contains(bicho), true);
			Assert.assertEquals(bicho.getOwner(), null);

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

			Assert.assertEquals(bicho.getOwner().getNombre(), entrenador.getNombre());
			Assert.assertEquals(bicho.getEspecie().getNombre(), "Leomon");
			Assert.assertEquals(bicho.getEspecie().getTipo(), TipoBicho.CHOCOLATE);
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			
			return null;
		});
	}
	
	
	@Test
	public void test_un_entrenador_realiza_una_busqueda_exitosa_en_un_dojo() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			Bicho bicho = this.service.buscar("Santiago");

			String nombreUbicacion = entrenador.getUbicacion().getNombre();
			List<Evento> feedEntrenador = this.feedService.feedEntrenador("Santiago");
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion(nombreUbicacion);
			
			Assert.assertEquals(bicho.getOwner().getNombre(), entrenador.getNombre());
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			Assert.assertEquals("Santiago", feedEntrenador.get(0).getEntrenador());
			Assert.assertEquals("Captura", feedEntrenador.get(0).getTipo());
			Assert.assertEquals(nombreUbicacion, feedUbicacion.get(0).getUbicacion());
			Assert.assertEquals(bicho.getEspecie().getNombre(), feedEntrenador.get(0).getExtraProperty());
			
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
			List<Evento> feedEntrenador = this.feedService.feedEntrenador("Jackson");
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion(nombreUbicacion);
			
			Assert.assertEquals(bicho.getOwner().getNombre(), entrenador.getNombre());
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			
			Assert.assertEquals(entrenador.getNombre(), feedEntrenador.get(0).getEntrenador());
			Assert.assertEquals("Captura", feedEntrenador.get(0).getTipo());
			Assert.assertEquals(nombreUbicacion, feedUbicacion.get(0).getUbicacion());
			Assert.assertEquals(bicho.getEspecie().getNombre(), feedEntrenador.get(0).getExtraProperty());
			
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
			
			String nombreUbicacion = resultadoCombate.getEntrenadorGanador().getUbicacion().getNombre();
			List<Evento> feedEntrenador = this.feedService.feedEntrenador("Vegetal");
			List<Evento> feedUbicacion  = this.feedService.feedUbicacion(nombreUbicacion);
			
			Assert.assertEquals(resultadoCombate.getEntrenadorGanador().getNombre(), feedEntrenador.get(0).getEntrenador());
			Assert.assertEquals("Coronacion", feedEntrenador.get(0).getTipo());
			Assert.assertEquals(nombreUbicacion, feedUbicacion.get(0).getUbicacion());
			Assert.assertEquals(resultadoCombate.getBichoPerdedor().getOwner().getNombre(), feedEntrenador.get(0).getExtraProperty());
			
			return null;
		});
	}
	
}
