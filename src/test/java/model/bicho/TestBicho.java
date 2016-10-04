package model.bicho;

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
		bicho.setHandler(dummyHandler);
		
		when(dummyEspecie.getEnergiaInicial()).thenReturn(100);
	}
	
	// Al momento de persistir el modelo un handler ya no se instanciaba
	// en la construcción de de Bicho, por lo tanto éste test ya no es válido TODO ...
	
//	@Test
//	public void cuando_un_bicho_desea_evolucionar_un_handler_interviene() {
//		
//		bicho.evolucionar();
//		verify(dummyHandler, times(1)).evolucionar();
//	}
//	
//	@Test
//	public void un_bicho_consulta_si_puede_evolucionar() {
//		bicho.puedeEvolucionar();
//		verify(dummyHandler, times(1)).puedeEvolucionar();
//	}
}
