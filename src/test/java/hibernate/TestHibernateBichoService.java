package hibernate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.DataEspecieManager;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoSessionService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;

public class TestHibernateBichoService {

	
	private BichoSessionService service;
	private BichoDAO hibernateBichoDAO;
	private DataService dataService;
	
	private Entrenador entrenador;
	
	@Before
	public void prepare() {
		
		MockitoAnnotations.initMocks(this);
		
		this.hibernateBichoDAO = new HibernateBichoDAO();
		this.service = new BichoSessionService(hibernateBichoDAO);
		this.dataService = new DataSessionService(new DataEspecieManager());
		
		dataService.crearSetDatosIniciales();
		
		entrenador = new Entrenador("Santiago");
		
	}
	
	@After
	public void deleteAll() {
		this.dataService.eliminarDatos();
		// solamente se eliminan las especies
	}
	
	@Test
	public void dado_un_bicho_este_puede_evolucionar() {
		
		Bicho bicho = this.service.getBicho("Leomon");
		bicho.setOwner(entrenador);
		
		this.service.puedeEvolucionar(entrenador.getNombre(), bicho.getId());
	}
	
}
