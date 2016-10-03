package hibernate;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollectionReachedMaximumSize;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Turno;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeAbandonarEnUbicacionException;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.TestService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoSessionService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * TestHibernateBichoService es una clase para hacer pruebas con servicios relacionados
 * con la clase {@link Bicho} del juego, en un ambiente persistente.
 * @author santiago
 *
 */
public class TestHibernateBichoService {

	
	private BichoSessionService service;
	private BichoDAO hibernateBichoDAO;
	private DataService dataService;
	private TestService testService;

	
	@Before
	public void prepare() {
		
		this.hibernateBichoDAO = new HibernateBichoDAO();
		this.service = new BichoSessionService(hibernateBichoDAO);
		this.dataService = new DataSessionService(new DataManager());
		this.testService = new TestService();
		
		dataService.crearSetDatosIniciales();
		
	}
	@After
	public void deleteAll() {
		this.dataService.eliminarDatos();
		// solamente se eliminan las especies
//		Runner.runInSession( () -> {
//
//
//			Session session = Runner.getCurrentSession();
//			List<String> nombreDeTablas = session.createNativeQuery("show tables").getResultList();
//			session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
//			nombreDeTablas.forEach(tabla -> {
//				session.createNativeQuery("truncate table " + tabla).executeUpdate();
//			});
//			session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
//			return null;
//		});
	}
	
	@Test
	public void dado_un_bicho_este_puede_evolucionar() {
		
		Bicho bicho = this.testService.recuperarEntidad(Bicho.class, 8);
		
		Runner.runInSession(() -> {
			
			Assert.assertEquals(bicho.getId(), 8);
			Assert.assertEquals(bicho.getEnergia(), 250);

			// El entrenador no es necesario
			boolean puedeEvolucionar = this.service.puedeEvolucionar(null, bicho.getId());
			
			Assert.assertTrue(puedeEvolucionar);
			
			return null;
		});
		
	}
	
	@Test
	public void dado_un_bicho_evoluciona_en_otra_especie() {
		
		// Todavía es un misterio la asignación de id 8 ! ...
		Bicho bicho = this.testService.recuperarEntidad(Bicho.class, 8);
		
		Especie especieAEvolucionar = this.testService.recuperarEntidad(Especie.class, "LeomonEvolucion");
		
		Runner.runInSession(() -> {
			
			Assert.assertEquals(bicho.getId(), 8);
			Assert.assertEquals(bicho.getEspecie().getNombre(), "Leomon");

			// El entrenador no es necesario
			Bicho nuevoBicho = this.service.evolucionar(null, bicho.getId());
			
			Assert.assertEquals(nuevoBicho.getEspecie().getNombre(), especieAEvolucionar.getNombre());
			
			return null;
		});
	}
	
	@Test
	public void un_entrenador_abandona_un_bicho_en_una_guarderia() {
		
		Bicho bicho = this.testService.recuperarEntidad(Bicho.class, 10);
		
		Runner.runInSession(() -> {

			this.service.abandonar("Jackson", 10);
			
			Guarderia guarderia = this.testService.recuperarEntidad(Guarderia.class, "Guarderia las 24 horas!");
			
			Assert.assertEquals(guarderia.getBichos().iterator().next().getId(), bicho.getId());

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
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			
			return null;
		});
		
	}
	
	@Test(expected=BichoCollectionReachedMaximumSize.class)
	public void un_entrenador_no_puede_realizar_una_busqueda_en_un_pueblo() {
		
		Runner.runInSession(() -> {

			Bicho bicho = this.service.buscar("Explorador1");

			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Explorador1");
			
			return null;
		});
		
	}
	
	@Test
	public void test_un_entrenador_realiza_una_busqueda_exitosa_en_un_dojo() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			Bicho bicho = this.service.buscar("Santiago");

			Assert.assertEquals(bicho.getOwner().getNombre(), entrenador.getNombre());
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			
			return null;
		});
	}
	
	@Test
	public void un_entrenador_realiza_una_busqueda_exitosa_en_una_guarderia() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Jackson");
			
			Bicho bicho = this.service.buscar("Jackson");

			Assert.assertEquals(bicho.getOwner().getNombre(), entrenador.getNombre());
			Assert.assertTrue(entrenador.getBichos().contains(bicho));
			
			return null;
		});
		
	}
	
	@Test
	public void un_entrenador_desafia_a_duelo_a_un_campeon_y_se_obtiene_un_resultado_del_combate_donde_el_bicho_retador_es_campeon() {
		
		Runner.runInSession(() -> {
			
			ResultadoCombate resultadoCombate = this.service.duelo("Vegetal", 12);
			
			Assert.assertEquals(resultadoCombate.getEntrenadorGanador().getNombre(), "Vegetal");
			Assert.assertEquals(resultadoCombate.getBichoGanador().getId(), 12);
			Assert.assertEquals(resultadoCombate.getBichoGanador().getVictorias(), 101, 0);
			Assert.assertEquals(resultadoCombate.getBichoPerdedor().getId(), 13);
			
			Dojo dojo = this.testService.recuperarEntidad(Dojo.class, "Torre Karin");
			
			Assert.assertEquals(resultadoCombate.getEntrenadorGanador().getCurrentExp(), 10d, 0);
			Assert.assertEquals(resultadoCombate.getBichoPerdedor().getOwner().getCurrentExp(), 10d, 0);
			
			List<Turno> turnos = resultadoCombate.getTurnos();
			
			Assert.assertEquals(turnos.iterator().next().getRetado().getId(), 13);
			Assert.assertEquals(turnos.iterator().next().getRetador().getId(), 12);
			
			Assert.assertEquals(dojo.getCampeon().getId(), 12);
			
			return null;
		});
	}
	
}
