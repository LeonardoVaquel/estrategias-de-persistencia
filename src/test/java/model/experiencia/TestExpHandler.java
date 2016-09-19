package model.experiencia;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.ExpHandler;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;

public class TestExpHandler {

	private ExpHandler expHandler;
	private @Mock Experiencia configuracion;
	private @Mock Entrenador entrenador;
	
	@Before
	public void setUp() {
		
		this.configuracion = Mockito.mock(Experiencia.class);
		this.entrenador    = Mockito.mock(Entrenador.class);
		MockitoAnnotations.initMocks(this);
		this.expHandler = new ExpHandler(configuracion);
		
		when(entrenador.getTotalExp()).thenReturn(0d);
		when(entrenador.getCurrentExp()).thenReturn(0d);
		when(entrenador.getNivel()).thenReturn(1);
		
		when(configuracion.getBaseExp()).thenReturn(1000d);
		when(configuracion.getExpByLvl(1)).thenReturn(100d);
		when(configuracion.getExpByLvl(2)).thenReturn(300d);
		when(configuracion.getExpByLvl(3)).thenReturn(600d);
	}
	
	@Test
	public void cuando_inicializo_un_exp_handler_se_setean_los_valores_iniciales() {
		
		Assert.assertEquals(configuracion, expHandler.getCfg());
		Assert.assertEquals(1000d, expHandler.getBaseExp(), 1000);
	}
	
	@Test
	public void la_experiencia_de_un_nuevo_entrenador_se_actualiza_en_evaluate_gained_exp() {
		
		expHandler.evaluateGainedExp(50d, entrenador);
		
		verify(entrenador).setCurrentExp(50d);
		verify(entrenador).setTotalExp(50d);

	}

	@Test
	public void la_experiencia_y_nivel_de_un_nuevo_entrenador_se_actualiza_en_evaluate_level_up() {
		
		expHandler.evaluateLevelUp(100d, entrenador.getNivel(), entrenador);
		
		verify(entrenador).setCurrentExp(0d);
		verify(entrenador).setNivel(2);
	}
	
	@Test
	public void un_jugador_gana_suficiente_experiencia_para_subir_mas_de_un_nivel() {
		
		expHandler.evaluateLevelUp(400d, entrenador.getNivel(), entrenador);
		
		verify(entrenador).setCurrentExp(0d);
		verify(entrenador).setNivel(2);
		verify(entrenador).setNivel(3);
	}
	
}
