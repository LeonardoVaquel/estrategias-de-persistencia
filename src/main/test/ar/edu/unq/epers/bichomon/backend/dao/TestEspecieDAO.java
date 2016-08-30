package ar.edu.unq.epers.bichomon.backend.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.ServiceFactory;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceEspecie;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;

public class TestEspecieDAO {

	EspecieService especieService;
	DataService dataServiceEspecie;
	
	@Before
	public void setUp(){
		
		ServiceFactory services = new ServiceFactory();
		especieService 		    = services.getEspecieService();
		dataServiceEspecie      = services.getDataService();
		
		dataServiceEspecie.crearSetDatosIniciales();
	}
	
	@Test
	public void testCuandoEspecieDAOCreaUnaNuevaEspecieSePuedeObtenerPersistidaConTodosSusAtributos() {
		
		String nombreEspecie 	  = "NuevaEspecie";
		TipoBicho tipoEspecie 	  = TipoBicho.CHOCOLATE;
		int alturaEspecie 		  = 4500;
		int pesoEspecie			  = 3000;
		int energiaEspecie 		  = 1000;
		String urlEspecie 		  = "http://bichomongo/var/www/html/static/bichomon/nuevaespecie.jpg";
		int cantidadBichosEspecie = 0;
		
		// Se crea una especie dummy
		Especie especieNueva = new Especie(nombreEspecie, tipoEspecie);
				especieNueva.setAltura(alturaEspecie);
				especieNueva.setPeso(pesoEspecie);
				especieNueva.setEnergiaIncial(energiaEspecie);
				especieNueva.setUrlFoto(urlEspecie);
				especieNueva.setCantidadBichos(cantidadBichosEspecie);
		
		// Exersice en DB
		especieService.crearEspecie(especieNueva);
		
		// Se espera recibir la información introducida en el exersice
		Especie retrievedEspecie = especieService.getEspecie(nombreEspecie);
		
		assertEquals(retrievedEspecie.getNombre(), 		   nombreEspecie);
		assertEquals(retrievedEspecie.getTipo(), 		   tipoEspecie);
		assertEquals(retrievedEspecie.getAltura(), 		   alturaEspecie);
		assertEquals(retrievedEspecie.getPeso(), 		   pesoEspecie);
		assertEquals(retrievedEspecie.getEnergiaInicial(), energiaEspecie);
		assertEquals(retrievedEspecie.getUrlFoto(), 	   urlEspecie);
		assertEquals(retrievedEspecie.getCantidadBichos(), cantidadBichosEspecie);
	}
	
	@Test
	public void testSeObtieneUnaEspecieConSusAtributosEnGetEspecieDeEspecieDAO() {
		
		// Exersice en DB
		String nombreEspecie     = "Gisemon";
		Especie retrievedEspecie = especieService.getEspecie(nombreEspecie);

		// Se espera recibir la misma información que en el dummy data
		Especie expectedData = DataServiceEspecie.DATAEspecies.get(nombreEspecie);
		
		assertEquals(retrievedEspecie.getNombre(), 		   expectedData.getNombre());
		assertEquals(retrievedEspecie.getTipo(), 		   expectedData.getTipo());
		assertEquals(retrievedEspecie.getAltura(), 		   expectedData.getAltura());
		assertEquals(retrievedEspecie.getPeso(), 		   expectedData.getPeso());
		assertEquals(retrievedEspecie.getEnergiaInicial(), expectedData.getEnergiaInicial());
		assertEquals(retrievedEspecie.getUrlFoto(), 	   expectedData.getUrlFoto());
		assertEquals(retrievedEspecie.getCantidadBichos(), expectedData.getCantidadBichos());
	}	
	
	@Test
	public void testCuandoEspecieDAOObtieneTodasLasEspeciesRetornaUnaListaOrdenadaAlfabeticamente() {
				
		List<Especie> todasLasEspecies = especieService.getAllEspecies();
		
		assertEquals(todasLasEspecies.size(), DataServiceEspecie.DATAEspecies.size());

		assertEquals(todasLasEspecies.get(0).getNombre(), DataServiceEspecie.DATAEspecies.get("Bottimon").getNombre());
		assertEquals(todasLasEspecies.get(1).getNombre(), DataServiceEspecie.DATAEspecies.get("Gisemon").getNombre());
		assertEquals(todasLasEspecies.get(2).getNombre(), DataServiceEspecie.DATAEspecies.get("Leomon").getNombre());
		
		// Asumimos que ya conocemos la información en un DATAService,
		// dónde existen solamente 3 Especies con los nombres utilizados en el test 
	}
	
	@Test
	public void testCuandoCreoUnBichoLoRetornoConLosAtributosNombreYEspecie(){

		// Se crea un dummyBicho
		String especieNuevoBicho  = "Leomon";
		String nombreNuevoBicho   = "fort";
		Bicho nuevoBicho 	      = especieService.crearBicho(especieNuevoBicho, nombreNuevoBicho);
		
		int cantidadDeBichosInicial = especieService.getEspecie(especieNuevoBicho).getCantidadBichos();
		
		// Excersice
		nuevoBicho = especieService.crearBicho(especieNuevoBicho, nombreNuevoBicho);
		
		int cantidadDeBichosFinal = especieService.getEspecie(especieNuevoBicho).getCantidadBichos();
		 
		// Se espera obtener nombre y especie:
		String expectedNombre  = nuevoBicho.getNombre();
		String expectedEspecie = nuevoBicho.getEspecie().getNombre();		
		
		assertEquals(expectedNombre, nombreNuevoBicho);
		assertEquals(expectedEspecie, especieNuevoBicho);
		assertEquals(cantidadDeBichosInicial+1, cantidadDeBichosFinal);
	}
	
	@After
	public void dropEspecie() {
		dataServiceEspecie.eliminarDatos();
	}
}
