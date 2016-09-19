package model.entrenador;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.ExpHandler;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

public class TestEntrenador {

	private Entrenador entrenador;
	private @Mock ExpHandler handler; 
	private @Mock Ubicacion ubicacion;
	
	@Before
	public void setUp() {
		
		entrenador = new Entrenador("TestTrainer", handler, ubicacion);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void cuando_se_crea_un_entrenador_se_setean_sus_valores_iniciales() {
		
		Assert.assertEquals("TestTrainer", entrenador.getNombre());
		Assert.assertEquals(1, entrenador.getNivel());
		Assert.assertEquals(0, entrenador.getCurrentExp(), 0);
		Assert.assertEquals(0, entrenador.getTotalExp(), 0);
	}
	
}
