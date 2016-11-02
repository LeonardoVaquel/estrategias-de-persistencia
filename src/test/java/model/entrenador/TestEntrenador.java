package model.entrenador;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

public class TestEntrenador {

	private Entrenador entrenador;
	
	private @Mock Ubicacion dummyUbicacion;
	private @Mock Bicho dummyBicho;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		this.entrenador = new Entrenador();
		this.entrenador.setMonedas(1000);
	}
	
	@Test
	public void un_bicho_evoluciona_en_entrenador_evolucionar() {
		
		entrenador.evolucionar(dummyBicho);
		verify(dummyBicho, times(1)).evolucionar();
	}
	
	@Test
	public void una_ubicacion_abandona_un_bicho_para_un_entrenador_en_entrenador_abandonar() {
		
		entrenador.setUbicacion(dummyUbicacion);
		entrenador.abandonar(dummyBicho);
		verify(dummyUbicacion, times(1)).abandonar(entrenador, dummyBicho);
	}
	
	@Test
	public void una_ubicacion_realiza_un_duelo_en_entrenador_duelo() {
		
		entrenador.setUbicacion(dummyUbicacion);
		entrenador.duelo(dummyBicho);
		verify(dummyUbicacion, times(1)).duelo(entrenador, dummyBicho);
	}
	
	@Test
	public void un_entrenador_cambia_su_ubicacion_en_entrenador_mover() {
		entrenador.mover(dummyUbicacion, 0);
		Assert.assertEquals(entrenador.getUbicacion(), dummyUbicacion);
	}

}
