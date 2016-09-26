package model.evolucion;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEdad;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEnergia;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionNivel;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionVictorias;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.NotEnoughAgeToEvolve;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.NotEnoughEnergyToEvolve;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.NotEnoughLevelToEvolve;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.exceptions.NotEnoughVictoriesToEvolve;

import static org.mockito.Mockito.*;

import org.joda.time.DateTime;

public class TestCriteriosEvolucion {

	private @Mock Especie especie;
	private @Mock Entrenador entrenador;
	private @Mock Bicho bicho;
	
	private CriterioEvolucion criterioPorEnergia;
	private CriterioEvolucion criterioPorVictorias;
	private CriterioEvolucion criterioPorEdad;
	private CriterioEvolucion criterioPorNivel;

	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		criterioPorEnergia   = new CriterioEvolucionEnergia(especie, 200);
		criterioPorVictorias = new CriterioEvolucionVictorias(especie, 100);
		criterioPorEdad      = new CriterioEvolucionEdad(especie, 100);
		criterioPorNivel     = new CriterioEvolucionNivel(especie, 50);
	}
	
	@After
	public void deleteAll() {
		criterioPorEnergia   = null;
		criterioPorVictorias = null;
		criterioPorEdad 	 = null;
		criterioPorNivel 	 = null;
	}

	@Test(expected=NotEnoughEnergyToEvolve.class)
	public void cuando_no_se_cumple_un_requisito_por_energia_se_levanta_una_excepcion() {

		when(bicho.getEnergia()).thenReturn(100);

		criterioPorEnergia.seCumple(bicho, entrenador);
		
	}
	
	@Test(expected=NotEnoughVictoriesToEvolve.class)
	public void cuando_no_se_cumple_un_requisito_por_victorias_se_levanta_una_excepcion() {

		when(bicho.getVictorias()).thenReturn(99);

		criterioPorVictorias.seCumple(bicho, entrenador);		
	}
	
	@Test(expected=NotEnoughAgeToEvolve.class)
	public void cuando_no_se_cumple_un_requisito_por_edad_se_levanta_una_excepcion() {

		when(bicho.getFechaCaptura()).thenReturn(new DateTime());

		criterioPorEdad.seCumple(bicho, entrenador);
	}
	
	@Test(expected=NotEnoughLevelToEvolve.class)
	public void cuando_no_se_cumple_un_requisito_por_nivel_de_entrenador_se_levanta_una_excepcion() {

		when(bicho.getOwner()).thenReturn(entrenador);				
		when(entrenador.getNivel()).thenReturn(49);

		criterioPorNivel.seCumple(bicho, entrenador);
	}
	
	@Test
	public void dados_un_bicho_y_un_entrenador_los_requisitos_de_evolucion_se_cumplen() {
		
		DateTime fechaDeCaptura = new DateTime(2016, 1, 20, 0, 0);
		when(bicho.getEnergia()).thenReturn(201);
		when(bicho.getVictorias()).thenReturn(101);
		when(bicho.getFechaCaptura()).thenReturn(fechaDeCaptura);
		when(bicho.getOwner()).thenReturn(entrenador);				
		when(entrenador.getNivel()).thenReturn(50);
		
		Assert.assertTrue(criterioPorEnergia.seCumple(bicho, entrenador));
		Assert.assertTrue(criterioPorVictorias.seCumple(bicho, entrenador));
		Assert.assertTrue(criterioPorEdad.seCumple(bicho, entrenador));
		Assert.assertTrue(criterioPorNivel.seCumple(bicho, entrenador));
	}	
	
}
