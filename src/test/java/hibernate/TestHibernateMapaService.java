package hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.MapaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateMapaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.neo4j.Neo4jMapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.CaminoCosto;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.Assert;

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
	
	@Before
	public void prepare() {
		
		this.mapaDAO 	   = new HibernateMapaDAO();
		this.entrenadorDAO = new HibernateEntrenadorDAO();
		this.testService   = new GenericService();
		this.neo4jmapaDAO  = new Neo4jMapaDAO();
		this.service 	   = new MapaSessionService(this.mapaDAO, this.entrenadorDAO, this.testService, this.neo4jmapaDAO);
		this.dataService   = new DataSessionService(new DataManager());
		
		
		this.dataService.crearSetDatosIniciales();
	}
	
	
	@After
	public void deleteAll() {
		this.dataService.eliminarTablas();
		this.neo4jmapaDAO.eliminarUbicaciones();
	}
	
	@Test
	public void dado_un_entrenador_y_una_ubicacion_se_mueve_al_entrenador_a_la_ubicacion_especificada() {
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			Integer monedas = entrenador.getMonedas();
			
			this.service.mover("Santiago", "Neverland");
			
			Assert.assertEquals(entrenador.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador.getMonedas(), monedas - CaminoCosto.MARITIMO.getValue(), 0);
			
			return null;
		});
	}
	
//	@Test
	public void dado_un_entrenador_y_una_ubicacion_no_lindante_se_mueve_al_entrenador_a_dicha_ubicacion_y_se_restan_sus_monedas(){
		
		Runner.runInSession(() -> {
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			Integer monedas = entrenador.getMonedas();
			
			this.service.mover("Santiago", "Neverland");
			
			Assert.assertEquals(entrenador.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador.getMonedas(), monedas - CaminoCosto.MARITIMO.getValue(), 0);
			
			return null;
		});
		
	}
	
	@Test
	public void dada_una_ubicacion_se_obtiene_la_cantidad_de_entrenadores_que_se_encuentran_inicialmente_en_la_ubicacion_especificada() {
	
		Runner.runInSession(() -> {
			
			Entrenador entrenador1 = this.testService.recuperarEntidad(Entrenador.class, "Explorador1");
			Entrenador entrenador2 = this.testService.recuperarEntidad(Entrenador.class, "Explorador2");
			Entrenador entrenador3 = this.testService.recuperarEntidad(Entrenador.class, "Explorador3");
			
			int cantidadDeEntrenadores = this.service.cantidadEntrenadores("Neverland");
			
			Assert.assertEquals(3, cantidadDeEntrenadores);
			Assert.assertEquals(entrenador1.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador2.getUbicacion().getNombre(), "Neverland");
			Assert.assertEquals(entrenador3.getUbicacion().getNombre(), "Neverland");
			
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
}
