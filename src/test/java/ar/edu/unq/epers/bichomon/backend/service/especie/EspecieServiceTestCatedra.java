package ar.edu.unq.epers.bichomon.backend.service.especie;


import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.ServiceFactory;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;

public class EspecieServiceTestCatedra {

	private EspecieService service;
	private DataService dataService;
	
	private Especie especie1;
	private Especie especie2;
	private Especie especie3;
	private Especie especie4;

	@Before
	public void prepare() {
		ServiceFactory factory = new ServiceFactory();
		this.dataService = factory.getDataService();
		this.service = factory.getEspecieService();
		
		this.especie1 = this.crearEspeciePrueba("EspecieTest1", 1);
		this.especie2 = this.crearEspeciePrueba("EspecieTest2", 2);
		this.especie3 = this.crearEspeciePrueba("EspecieTest3", 3);
		this.especie4 = this.crearEspeciePrueba("A-EspecieTest3", 3);
	}
	
	@After
	public void cleanup() {
		this.dataService.eliminarDatos();
	}
	
	@Test
	public void al_crear_y_recuperar_una_especie_obtengo_los_mismos_atributos() {
		this.assertEspecie(this.especie1,  this.service.getEspecie("EspecieTest1"));
		this.assertEspecie(this.especie2, this.service.getEspecie("EspecieTest2"));
		this.assertEspecie(this.especie3, this.service.getEspecie("EspecieTest3"));
	}
	
	@Test(expected=RuntimeException.class)
	public void el_nombre_de_una_especie_debe_ser_unico() {
		this.crearEspeciePrueba("EspecieTest1", 4);
	}

	@Test(expected=EspecieNoExistente.class)
	public void al_buscar_una_especie_no_existente_obtengo_la_excepcion_deseada() {
		this.service.getEspecie("EspecieFruta");
	}

	@Test
	public void al_recuperar_especies_deben_traerse_en_orden_alfabetico() {
		List<Especie> all = this.service.getAllEspecies();
		
		Assert.assertEquals(4, all.size());
		this.assertEspecie(this.especie4, all.get(0));
		this.assertEspecie(this.especie1, all.get(1));
		this.assertEspecie(this.especie2, all.get(2));
		this.assertEspecie(this.especie3, all.get(3));
	}
	
	@Test
	public void al_crear_un_bicho_la_especie_del_nuevo_bicho_debe_coincidir() {
		Bicho bicho = this.service.crearBicho("EspecieTest1", "Bicho1");
		
		Assert.assertEquals("El nombre del bicho no es el esperado", "Bicho1", bicho.getNombre());
		this.assertEspecie(this.especie1, bicho.getEspecie(), true);
	}
	
	
	@Test
	public void al_crear_un_bicho_el_contador_de_especie_debe_incrementarse_en_1() {
		Bicho bicho = this.service.crearBicho("EspecieTest1", "Bicho1");
		Assert.assertEquals("El nombre del bicho no es el esperado", "Bicho1", bicho.getNombre());
		
		Assert.assertEquals(1 + 1, this.service.getEspecie("EspecieTest1").getCantidadBichos());
		
		this.service.crearBicho("EspecieTest1", "Bicho1");
		this.service.crearBicho("EspecieTest1", "Bicho2");
		this.service.crearBicho("EspecieTest1", "Bicho3");
		
		//Diff especie, no deberia afectar
		this.service.crearBicho("EspecieTest2", "Bicho4");
		
		Assert.assertEquals(1 + 1  + 3, this.service.getEspecie("EspecieTest1").getCantidadBichos());
	}
	
	@Test
//	@Ignore //No estaba demarcado de forma explicita en el enunciado.
	public void al_crear_un_bicho_el_mismo_debe_tener_su_energia_igual_a_la_energia_incial_de_la_especie() {
		Bicho bicho = this.service.crearBicho("EspecieTest1", "Bicho1");
		
		Assert.assertEquals("El nombre del bicho no es el esperado", "Bicho1", bicho.getNombre());
		Assert.assertEquals("La energia inicial no es la correcta", this.especie1.getEnergiaInicial(), bicho.getEnergia());
	}

	private Especie crearEspeciePrueba(String nombre, int i) {
		Especie especie = new Especie();
		especie.setAltura(i * 100);
		especie.setCantidadBichos(i);
		especie.setEnergiaIncial(i * 1000);
		especie.setNombre(nombre);
		especie.setPeso(i * 10);
		especie.setTipo(TipoBicho.values()[i]);
		especie.setUrlFoto("http://testurl" + i);
		
		this.service.crearEspecie(especie);
		return especie;
	}
	
	private void assertEspecie(Especie especie2, Especie especie) {
		this.assertEspecie(especie2, especie, false);
	}
	
	private void assertEspecie(Especie especie2, Especie especie, boolean exclueCantidadBichos) {
		Assert.assertEquals("El nombre no coincide", especie2.getNombre(), especie.getNombre());
		Assert.assertEquals("La altura no coincide", especie2.getAltura(), especie.getAltura());
		Assert.assertEquals("La energia inicial no coincide", especie2.getEnergiaInicial(), especie.getEnergiaInicial());
		Assert.assertEquals("El peso no coincide", especie2.getPeso(), especie.getPeso());
		Assert.assertEquals("El tipo no coincide", especie2.getTipo(), especie.getTipo());
		Assert.assertEquals("La foto no coincide", especie2.getUrlFoto(), especie.getUrlFoto());
		
		if (!exclueCantidadBichos) {
			Assert.assertEquals("La cantidad de bichos no coincide", especie2.getCantidadBichos(), especie.getCantidadBichos());
		}
	}

}
