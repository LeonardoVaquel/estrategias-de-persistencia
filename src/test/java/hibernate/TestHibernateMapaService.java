package hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateMapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.DataEspecieManager;
import ar.edu.unq.epers.bichomon.backend.service.TestService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.Assert;

public class TestHibernateMapaService {

	
	private MapaSessionService service;
	private MapaService hibernateMapaDAO;
	private DataService dataService;
	private TestService testService;	
	
	@Before
	public void prepare() {
		
		this.hibernateMapaDAO = new HibernateMapaDAO();
		this.service = new MapaSessionService(hibernateMapaDAO);
		this.dataService = new DataSessionService(new DataEspecieManager());
		this.testService = new TestService();
		
		dataService.crearSetDatosIniciales();
	}
	
	
	@After
	public void deleteAll() {

	}
	
	@Test
	public void dado_un_entrenador_y_una_ubicacion_se_mueve_al_entrenador_a_la_ubicacion_especificada() {
		
		Runner.runInSession(() -> {
			
			this.service.mover("Santiago", "Neverland");
			
			Entrenador entrenador = this.testService.recuperarEntidad(Entrenador.class, "Santiago");
			
			Assert.assertEquals(entrenador.getUbicacion().getNombre(), "Neverland");
			
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
	
	
}
