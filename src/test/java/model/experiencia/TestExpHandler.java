package model.experiencia;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.ExpHandler;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Level;

public class TestExpHandler {

	private ExpHandler expHandler;
	
	private @Mock Experiencia dummyCfg;
	private @Mock Entrenador dummyEntrenador;
	
	private @Mock Level dummyLevel1;
	private @Mock Level dummyLevel2;
	private @Mock Level dummyLevel3;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		this.expHandler = new ExpHandler(dummyCfg);
		
		when(dummyEntrenador.getTotalExp()).thenReturn(0d);
		when(dummyEntrenador.getCurrentExp()).thenReturn(0d);
		when(dummyEntrenador.getNivel()).thenReturn(dummyLevel1);
		when(dummyEntrenador.getNumeroNivel()).thenReturn(1);
		
		when(dummyLevel1.getNivel()).thenReturn(1);
		when(dummyLevel2.getNivel()).thenReturn(2);
		when(dummyLevel3.getNivel()).thenReturn(3);
		
		when(dummyCfg.getBaseExp()).thenReturn(1000d);
		when(dummyCfg.getExpByLvl(1)).thenReturn(100d);
		when(dummyCfg.getExpByLvl(2)).thenReturn(300d);
		when(dummyCfg.getExpByLvl(3)).thenReturn(600d);
		

		when(dummyCfg.getLevelByNumber(1)).thenReturn(dummyLevel1);
		when(dummyCfg.getLevelByNumber(2)).thenReturn(dummyLevel2);
		when(dummyCfg.getLevelByNumber(3)).thenReturn(dummyLevel3);
	}
	
	@Test
	public void cuando_inicializo_un_exp_handler_se_setean_los_valores_iniciales() {
		
		Assert.assertEquals(dummyCfg, expHandler.getCfg());
		Assert.assertEquals(1000d, expHandler.getBaseExp(), 1000);
	}
	
	@Test
	public void la_experiencia_de_un_nuevo_entrenador_se_actualiza_en_evaluate_gained_exp() {
		
		expHandler.evaluateGainedExp(50d, dummyEntrenador);
		
		verify(dummyEntrenador).setCurrentExp(50d);
		verify(dummyEntrenador).setTotalExp(50d);

	}

	@Test
	public void la_experiencia_y_nivel_de_un_nuevo_entrenador_se_actualiza_en_evaluate_level_up() {
		
		expHandler.evaluateLevelUp(100d, dummyEntrenador);
		
		verify(dummyCfg, times(2)).getExpByLvl(dummyEntrenador.getNumeroNivel());
		verify(dummyEntrenador).setCurrentExp(0d);
	}
	
	@Test
	public void un_jugador_gana_suficiente_experiencia_para_subir_mas_de_un_nivel() {
		
		expHandler.evaluateLevelUp(401d, dummyEntrenador);
		
		verify(dummyEntrenador, times(1)).setCurrentExp(101d);
		verify(dummyEntrenador, times(1)).setCurrentExp(301d);
		verify(dummyEntrenador, times(1)).setCurrentExp(1d);
		
	}
	
}
