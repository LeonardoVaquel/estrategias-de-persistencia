package model.bicho;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.EvolutionHandler;

import static org.mockito.Mockito.*;

public class TestBicho {

	private Bicho bicho;
	private @Mock Especie dummyEspecie;
	private @Mock Entrenador dummyEntrenador;
	private @Mock EvolutionHandler dummyHandler;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		bicho = new Bicho(dummyEspecie);
		bicho.setOwner(dummyEntrenador);
		
		when(dummyEspecie.getEnergiaInicial()).thenReturn(100);
	}
	
	@Test
	public void cuando_un_bicho_evoluciona_cambia_su_especie() {
		
		bicho.evolucionar(dummyEspecie);
		Assert.assertEquals(bicho.getEspecie(), dummyEspecie);
	}
	
	@Test
	public void un_bicho_suma_sus_victorias_en_uno() {
		Integer victoriasActuales = bicho.getVictorias();
		bicho.nuevaVictoria();
		Assert.assertEquals(victoriasActuales+1, bicho.getVictorias(), 0);
	}
}
