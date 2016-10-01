package hibernate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEnergia;
import ar.edu.unq.epers.bichomon.backend.service.DataEspecieManager;
import ar.edu.unq.epers.bichomon.backend.service.TestService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoSessionService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class TestHibernateBichoService {

	
	private BichoSessionService service;
	private BichoDAO hibernateBichoDAO;
	private DataService dataService;
	private TestService testService;
	
	private Entrenador entrenador;
	
	@Before
	public void prepare() {
		
		//MockitoAnnotations.initMocks(this);
		
		this.hibernateBichoDAO = new HibernateBichoDAO();
		this.service = new BichoSessionService(hibernateBichoDAO);
		this.dataService = new DataSessionService(new DataEspecieManager());
		this.testService = new TestService();
		
		//dataService.crearSetDatosIniciales();
		
		Especie leoMon = new Especie("Leomon", TipoBicho.CHOCOLATE);
		leoMon.setRaiz(leoMon);
		leoMon.setEvolucion(null);
			List<CriterioEvolucion> criteriosLeomon = new ArrayList<>();
			criteriosLeomon.add(new CriterioEvolucionEnergia(200));
		leoMon.setCriteriosDeEvolucion(criteriosLeomon);
		leoMon.setAltura(1);
		leoMon.setPeso(1);
		leoMon.setEnergiaIncial(100);
		leoMon.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/leomon.jpg");
		leoMon.setCantidadBichos(0);
		
		
		Bicho bichoLeoMon = new Bicho(leoMon);
		bichoLeoMon.setEnergia(201);
		
		
		entrenador = new Entrenador("Santiago");
		
		entrenador.obtenerBicho(bichoLeoMon);
		
		testService.crearEntidad(leoMon);
		testService.crearEntidad(entrenador);
		
		testService.crearEntidad(bichoLeoMon);
		
		
		
	}
	//@After
	public void deleteAll() {
		this.dataService.eliminarDatos();
		// solamente se eliminan las especies
	}
	
	@Test
	public void dado_un_bicho_este_puede_evolucionar() {
		
		Bicho bicho = this.testService.recuperarEntidad(Bicho.class, 2);
		
		System.out.println(bicho);
		
		Runner.runInSession(() -> {
			
			Assert.assertEquals(bicho.getId(), 2);
			Assert.assertEquals(bicho.getEnergia(), 201);

			boolean puedeEvolucionar = this.service.puedeEvolucionar(entrenador.getNombre(), bicho.getId());
			
			Assert.assertTrue(puedeEvolucionar);
			
			return null;
		});
		
		
	}
	
}
