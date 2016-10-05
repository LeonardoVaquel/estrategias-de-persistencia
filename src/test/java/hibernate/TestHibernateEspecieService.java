package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.BichomonFactory;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.TestService;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * TestHibernateEspecieService es una clase para hacer pruebas con servicios relacionados
 * con la {@link Especie} del juego, en un ambiente persistente.
 * @author santiago
 *
 */
public class TestHibernateEspecieService {
	
	private EspecieSessionService service;
	private TestService testService;
	private BichomonFactory bichoFactory;
	private EspecieDAO especieDAO;
	
	private Especie nuevaEspecie,nuevaEspecie2;
	
	@Before
	public void prepare() {
		this.especieDAO = new HibernateEspecieDAO();
		this.bichoFactory = new BichomonFactory();
		this.service = new EspecieSessionService(especieDAO);
		this.testService = new TestService();
		
		
		nuevaEspecie = this.bichoFactory.crearEspecie( "Fortmon",
																null,
																null,
																null,
						  										180,
						  										90,
						  										"CHOCOLATE",
										  						100,
										  						"url",
										  						0);
		nuevaEspecie.setRaiz(nuevaEspecie);
		this.service.crearEspecie(nuevaEspecie);
		
		nuevaEspecie2 = this.bichoFactory.crearEspecie( "Bottimon",
				null,
				null,
				null,
				180,
				90,
				"FUEGO",
				100,
				"url",
				0);

		nuevaEspecie2.setRaiz(nuevaEspecie2);
		this.service.crearEspecie(nuevaEspecie2);


	}
	
	@After
	public void limpiarTodo() {
		
		Runner.runInSession( () -> {


			Session session = Runner.getCurrentSession();
			List<String> nombreDeTablas = session.createNativeQuery("show tables").getResultList();
			session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
			nombreDeTablas.forEach(tabla -> {
				session.createNativeQuery("truncate table " + tabla).executeUpdate();
			});
			session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
			return null;
		});
	}
	
	@Test
	public void test_se_obtiene_una_especie_ya_persistida_con_todos_sus_atributos() {
		
		// this.service.getEspecie("Fortmon"); 
		
		Runner.runInSession(() -> {
			Especie especie = testService.recuperarEntidad(Especie.class, "Fortmon");
			Assert.assertEquals("Fortmon", especie.getNombre());
			
			Assert.assertEquals(180, 		 		 especie.getAltura());
			Assert.assertEquals(90, 		 		 especie.getPeso());
			Assert.assertEquals(TipoBicho.CHOCOLATE, especie.getTipo());
			Assert.assertEquals(100, 		 		 especie.getEnergiaInicial());
			Assert.assertEquals("url", 		 		 especie.getUrlFoto());
			Assert.assertEquals(0, 			 		 especie.getCantidadBichos());
			
			return null;
		});
	}

	@Test
	public void test_se_obtienen_todas_las_especies_existentes() {
		
		this.service.getAllEspecies();
	
		Runner.runInSession(() -> {
			
			List<Especie> especies = this.especieDAO.getAllEspecies();
			
			Especie expectedEspecie = especies.get(1);
			
			Assert.assertEquals(expectedEspecie.getNombre(), "Fortmon");
			
			return null;
		});
		
	}
	@Test 
	public void  retorna_las_especies_mas_populares(){
		this.service.getAllEspecies();
		
		Runner.runInSession(() -> {
			List<Especie> especiesMasPopulares = this.especieDAO.populares();	
			Assert.assertEquals(especiesMasPopulares.size(),2);
		return null;
		});
	}
	
	@Test
	public void test_busco_la_especie_mas_popular_(){
		Runner.runInSession(() ->{
			List<Especie> especies = this.especieDAO.populares();
			Especie expected = especies.get(1);
			
			Assert.assertEquals(expected.getTipo(), TipoBicho.FUEGO);
		return null;		
		});
	}



}
