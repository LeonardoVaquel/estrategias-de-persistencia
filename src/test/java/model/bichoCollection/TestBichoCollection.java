package model.bichoCollection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollection;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollectionCantBeEmpty;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollectionReachedMaximumSize;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Level;

public class TestBichoCollection {

	private BichoCollection collection;
	
	private @Mock Level dummyLevel;
	private @Mock Entrenador dummyEntrenador;
	private @Mock Bicho dummyBicho;
	private @Mock List<Bicho> dummyBichoList;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);

		collection = new BichoCollection();
	}
	
	@After
	public void destroy() {
		
		collection = null;
		
	}
	
	@Test
	public void una_bicho_collection_determina_que_una_lista_de_bichos_esta_llena() {
			
		when(dummyBichoList.size()).thenReturn(3);
		when(dummyLevel.getCoeficienteBichos()).thenReturn(2);
		
		Assert.assertTrue(collection.isFull(dummyBichoList, dummyLevel));
	}

	@Test
	public void una_bicho_collection_determina_que_tiene_un_solo_elemento() {
			
		when(dummyBichoList.size()).thenReturn(1);
		
		Assert.assertTrue(collection.isSingleton(dummyBichoList));
	}
	
	
	@Test
	public void se_agrega_un_bicho_para_un_entrenador_y_cambia_el_owner_del_bicho_en_collection_add() {
		
		when(dummyBichoList.size()).thenReturn(1);
		when(dummyLevel.getCoeficienteBichos()).thenReturn(3);
		when(dummyEntrenador.getNivel()).thenReturn(dummyLevel);
		when(dummyEntrenador.getBichos()).thenReturn(dummyBichoList);
		
		collection.add(dummyBicho, dummyEntrenador);
		
		verify(dummyBichoList, times(1)).add(dummyBicho);
		verify(dummyBicho, times(1)).setOwner(dummyEntrenador);
	}
	
	@Test
	public void se_borra_un_bicho_para_un_entrenador_y_cambia_el_owner_del_bicho_en_collection_remove() {
		
		when(dummyBichoList.size()).thenReturn(2);
		when(dummyLevel.getCoeficienteBichos()).thenReturn(3);
		
		collection.remove(dummyBicho, dummyBichoList);
		
		verify(dummyBichoList, times(1)).remove(dummyBicho);
		verify(dummyBicho, times(1)).setOwner(null);
	}	
	
	@Test(expected=BichoCollectionCantBeEmpty.class)
	public void cuando_intento_remover_el_unico_bicho_de_una_coleccion_obtengo_una_excepcion() {
		
		when(dummyBichoList.size()).thenReturn(1);
		
		collection.remove(dummyBicho, dummyBichoList);
	}
	
	@Test(expected=BichoCollectionReachedMaximumSize.class)
	public void cuando_intento_agregar_un_bicho_a_una_lista_llena_obtengo_una_excepcion() {
		
		when(dummyBichoList.size()).thenReturn(8);
		when(dummyLevel.getCoeficienteBichos()).thenReturn(8);
		when(dummyEntrenador.getNivel()).thenReturn(dummyLevel);
		when(dummyEntrenador.getBichos()).thenReturn(dummyBichoList);
		
		collection.add(dummyBicho, dummyEntrenador);
	}
}
