package model.evolucion;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEdad;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEnergia;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionNivel;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionVictorias;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.EvolutionHandler;

/**
 * @author santiago
 */
public class TestEvolutionHandler {
	
	private @Mock Especie dummyEspecieEvolucion;
	private @Mock Entrenador dummyEntrenador;
	private @Mock Bicho dummyBicho;
	
	private List<CriterioEvolucion> criterios = new ArrayList<>();
	private CriterioEvolucion criterioPorEnergia;
	private CriterioEvolucion criterioPorVictorias;
	private CriterioEvolucion criterioPorEdad;
	private CriterioEvolucion criterioPorNivel;
	
	private EvolutionHandler handler;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		handler = new EvolutionHandler();
		handler.setBicho(dummyBicho);
		
		
		criterioPorEnergia   = new CriterioEvolucionEnergia(200);
		criterioPorVictorias = new CriterioEvolucionVictorias(100);
		criterioPorEdad      = new CriterioEvolucionEdad(20);
		criterioPorNivel     = new CriterioEvolucionNivel(50);
		
		criterios.add(criterioPorEnergia);
		criterios.add(criterioPorVictorias);
		criterios.add(criterioPorEdad);
		criterios.add(criterioPorNivel);

		when(dummyBicho.getCriteriosDeEvolucion()).thenReturn(criterios);
		when(dummyBicho.getEvolucion()).thenReturn(dummyEspecieEvolucion);
		when(dummyBicho.getOwner()).thenReturn(dummyEntrenador);
		
	}
	
	@After
	public void deleteAll() {
		handler 		   	 = null;
		criterioPorEnergia 	 = null;
		criterioPorVictorias = null;
		criterioPorEdad 	 = null;
		criterioPorNivel 	 = null;
		criterios			 = null;
	}
	
	@Test
	public void un_handler_devuelve_false_en_handler_puede_evolucionar() {

		LocalDateTime fechaDeCaptura = LocalDateTime.now().minusDays(50);		
		when(dummyBicho.getEnergia()).thenReturn(100);
		when(dummyBicho.getVictorias()).thenReturn(50);
		when(dummyBicho.getFechaCaptura()).thenReturn(fechaDeCaptura);				
		when(dummyEntrenador.getNumeroNivel()).thenReturn(20);
		
		Assert.assertFalse(handler.puedeEvolucionar());
	}
	
	@Test
	public void un_handler_devuelve_true_en_handler_puede_evolucionar() {

		LocalDateTime fechaDeCaptura = LocalDateTime.now().minusDays(50);		
		when(dummyBicho.getEnergia()).thenReturn(201);
		when(dummyBicho.getVictorias()).thenReturn(101);
		when(dummyBicho.getFechaCaptura()).thenReturn(fechaDeCaptura);				
		when(dummyEntrenador.getNumeroNivel()).thenReturn(50);
		
		Assert.assertTrue(handler.puedeEvolucionar());
	}

	@Test
	public void un_handler_evoluciona_un_bicho_apto_para_evolucionar() {
		LocalDateTime fechaDeCaptura = LocalDateTime.now().minusDays(50);		
		when(dummyBicho.getEnergia()).thenReturn(201);
		when(dummyBicho.getVictorias()).thenReturn(101);
		when(dummyBicho.getFechaCaptura()).thenReturn(fechaDeCaptura);				
		when(dummyEntrenador.getNumeroNivel()).thenReturn(50);
		
		handler.evolucionar();
		
		verify(dummyBicho, times(1)).getEvolucion();
		verify(dummyBicho, times(1)).evolucionar(dummyEspecieEvolucion);
	}
	
}
