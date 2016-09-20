package model.bichoCollection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollection;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;

public class TestBichoCollection {

	private BichoCollection collection;
	private @Mock Entrenador entrenador;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		
	}
	
	@Test
	public void cuando_se_crea_una_nueva_coleccion_para_un_entrenador_se_setean_sus_valores_iniciales() {
		
		
		when(entrenador.getNivel()).thenReturn(1);
		
		collection = new BichoCollection(entrenador.getNivel());
		
		Assert.assertEquals(3, collection.getCoeficiente(), 0);
		Assert.assertEquals(1, collection.getNivel(), 0);
		Assert.assertEquals(3, collection.getMaxSize(), 0);
	}
	
	@Test
	public void cuando_se_crea_una_nueva_collecion_con_un_coeficiente_dado_se_setea_sus_valores_iniciales() {
		
		when(entrenador.getNivel()).thenReturn(1);
		
		collection = new BichoCollection(entrenador.getNivel(), 8);
		
		Assert.assertEquals(8, collection.getCoeficiente(), 0);
		Assert.assertEquals(1, collection.getNivel(), 0);
		Assert.assertEquals(8, collection.getMaxSize(), 0);
	}
	
}
