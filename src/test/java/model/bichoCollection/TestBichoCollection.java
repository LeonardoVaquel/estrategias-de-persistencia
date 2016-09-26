package model.bichoCollection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollection;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollectionCantBeEmpty;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollectionReachedMaximumSize;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public class TestBichoCollection {

	private BichoCollection collection;
	private @Mock Entrenador entrenador;
	private @Mock Bicho bicho;
	
	@Rule    
	public ExpectedException fullCollection;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		when(entrenador.getNivel()).thenReturn(1);

		collection = new BichoCollection(entrenador.getNivel());
	
		fullCollection = ExpectedException.none();
	}
	
	@After
	public void destroy() {
		
		collection = null;
		
	}
	
	@Test
	public void cuando_se_crea_una_nueva_coleccion_para_un_entrenador_se_setean_sus_valores_iniciales() {
		
		Assert.assertEquals(3, collection.getCoeficiente(), 0);
		Assert.assertEquals(1, collection.getNivel(), 0);
		Assert.assertEquals(3, collection.getMaxSize(), 0);
	}
	
	@Test
	public void cuando_se_crea_una_nueva_collecion_con_un_coeficiente_dado_se_setea_sus_valores_iniciales() {
		
		collection.setCoeficiente(8);
		
		Assert.assertEquals(8, collection.getCoeficiente(), 0);
		Assert.assertEquals(1, collection.getNivel(), 0);
		Assert.assertEquals(8, collection.getMaxSize(), 0);
	}
	
	@Test
	public void se_agregan_elementos_a_una_lists_de_tamaño_maximo_3_para_obtener_una_coleccion_llena() {
		
		when(entrenador.getNivel()).thenReturn(3);
		collection = new BichoCollection(entrenador.getNivel());
		
		collection.setCoeficiente(1);
		
		collection.add(bicho);
		collection.add(bicho);
		collection.add(bicho);
		
		Assert.assertEquals(3, collection.getMaxSize(), 0);
		Assert.assertEquals(true, collection.isFull());
		
	}
	
	@Test
	public void el_tamaño_maximo_de_una_coleccion_varia_con_el_nivel_del_entrenador() {
		
		when(entrenador.getNivel()).thenReturn(1);
		
		collection.setCoeficiente(4);
		
		Integer initial_size  = collection.getMaxSize();
		when(entrenador.getNivel()).thenReturn(2);
		collection.setNivel(entrenador.getNivel());
		Integer expected_size = collection.getMaxSize();
		
		Assert.assertEquals(4, initial_size, 0);
		Assert.assertEquals(8, expected_size, 0);
		
	}
	
	@Test(expected=BichoCollectionCantBeEmpty.class)
	public void cuando_intento_remover_el_unico_bicho_de_una_coleccion_obtengo_una_excepcion() {
		
		collection.add(bicho);
		collection.remove(bicho);
	}
	
	@Test(expected=BichoCollectionReachedMaximumSize.class)
	public void cuando_intento_agregar_un_bicho_a_una_lista_llena_obtengo_una_excepcion() {
		
		collection.add(bicho);
		collection.add(bicho);
		collection.add(bicho);
		collection.add(bicho);
		
	}
}
