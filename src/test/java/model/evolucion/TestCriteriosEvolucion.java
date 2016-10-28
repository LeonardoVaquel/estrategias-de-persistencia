package model.evolucion;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEdad;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEnergia;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionNivel;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionVictorias;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

public class TestCriteriosEvolucion {

	private @Mock Entrenador dummyEntrenador;
	private @Mock Bicho dummyBicho;
	
	private CriterioEvolucion criterioPorEnergia;
	private CriterioEvolucion criterioPorVictorias;
	private CriterioEvolucion criterioPorEdad;
	private CriterioEvolucion criterioPorNivel;

	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		criterioPorEnergia   = new CriterioEvolucionEnergia(200);
		criterioPorVictorias = new CriterioEvolucionVictorias(100);
		criterioPorEdad      = new CriterioEvolucionEdad(20);
		criterioPorNivel     = new CriterioEvolucionNivel(50);
	}
	
	@After
	public void deleteAll() {
		criterioPorEnergia   = null;
		criterioPorVictorias = null;
		criterioPorEdad 	 = null;
		criterioPorNivel 	 = null;
	}

	@Test
	public void un_bicho_con_energia_menor_a_200_cumple_un_criterio_por_energia() {

		when(dummyBicho.getEnergia()).thenReturn(100);

		Assert.assertFalse(criterioPorEnergia.seCumple(dummyBicho, dummyEntrenador));
	}
	
	@Test
	public void un_bicho_con_energia_mayor_a_200_cumple_un_criterio_por_energia() {
		
		when(dummyBicho.getEnergia()).thenReturn(201);

		Assert.assertTrue(criterioPorEnergia.seCumple(dummyBicho, dummyEntrenador));
	}
	
	@Test
	public void un_bicho_con_victorias_no_mayores_a_100_cumple_un_criterio_por_victorias() {

		when(dummyBicho.getVictorias()).thenReturn(99);

		Assert.assertFalse(criterioPorVictorias.seCumple(dummyBicho, dummyEntrenador));		
	}
	
	@Test
	public void un_bicho_con_victorias_mayores_a_100_puede_cumple_un_criterio_por_victorias() {
		
		when(dummyBicho.getVictorias()).thenReturn(101);

		Assert.assertTrue(criterioPorVictorias.seCumple(dummyBicho, dummyEntrenador));		
	}
	
	@Test
	public void un_bicho_capturado_hace_no_mas_de_20_dias_no_cumple_un_criterio_por_edad() {
		
		LocalDateTime hace15Dias = LocalDateTime.now().minusDays(15);
		when(dummyBicho.getFechaCaptura()).thenReturn(hace15Dias);
		
		Assert.assertFalse(criterioPorEdad.seCumple(dummyBicho, dummyEntrenador));
	}
	
	@Test
	public void un_bicho_capturado_hace_mas_de_20_dias_cumple_un_criterio_por_edad() {
		
		LocalDateTime hace21Dias = LocalDateTime.now().minusDays(21);
		when(dummyBicho.getFechaCaptura()).thenReturn(hace21Dias);
		
		Assert.assertTrue(criterioPorEdad.seCumple(dummyBicho, dummyEntrenador));
	}
	
	@Test
	public void un_entrenador_de_nivel_no_mayor_a_50_no_cumple_un_criterio_por_nivel() {

		when(dummyEntrenador.getNumeroNivel()).thenReturn(49);

		Assert.assertFalse(criterioPorNivel.seCumple(dummyBicho, dummyEntrenador));
	}
	
	@Test
	public void un_entrenador_de_al_menos_50_cumple_un_criterio_por_nivel() {

		when(dummyEntrenador.getNumeroNivel()).thenReturn(50);

		Assert.assertTrue(criterioPorNivel.seCumple(dummyBicho, dummyEntrenador));
	}	
	
	@Test
	public void dados_un_bicho_y_un_entrenador_los_requisitos_de_evolucion_se_cumplen() {
		
		LocalDateTime fechaDeCaptura = LocalDateTime.now().minusDays(50);
		when(dummyBicho.getEnergia()).thenReturn(201);
		when(dummyBicho.getVictorias()).thenReturn(101);
		when(dummyBicho.getFechaCaptura()).thenReturn(fechaDeCaptura);				
		when(dummyEntrenador.getNumeroNivel()).thenReturn(50);
		
		Assert.assertTrue(criterioPorEnergia.seCumple(dummyBicho, dummyEntrenador));
		Assert.assertTrue(criterioPorVictorias.seCumple(dummyBicho, dummyEntrenador));
		Assert.assertTrue(criterioPorEdad.seCumple(dummyBicho, dummyEntrenador));
		Assert.assertTrue(criterioPorNivel.seCumple(dummyBicho, dummyEntrenador));
	}	
	
}
