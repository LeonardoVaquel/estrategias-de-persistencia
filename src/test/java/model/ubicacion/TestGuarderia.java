package model.ubicacion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollection;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoHayBichosEnGuarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeRealizarDueloEnUbicacionException;

import ar.edu.unq.epers.bichomon.backend.model.utils.BichomonRandom;

public class TestGuarderia {
	
	private Guarderia guarderia;
	
	private @Mock Entrenador dummyEntrenador;
	private @Mock Especie especie;
	private @Mock Bicho dummyBicho;
	private @Mock BichoCollection collection;
	private @Mock BichomonRandom dummyRandom;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		this.guarderia = new Guarderia("unNombreDeGuarderia");
		this.guarderia.setBichos(new ArrayList<Bicho>());
		this.guarderia.setRandom(dummyRandom);
	}
	
	@Test
	public void un_entrenador_abandona_un_bicho_en_una_guarderia() {
		
		when(dummyEntrenador.puedeAbandonar()).thenReturn(true);
		
		this.guarderia.abandonar(dummyEntrenador, dummyBicho);
		
		Assert.assertTrue(this.guarderia.getBichos().contains(dummyBicho));
		verify(dummyEntrenador, times(1)).abandonarBicho(dummyBicho);
	}
	
	@Test(expected = NoHayBichosEnGuarderia.class)
	public void se_lanza_una_exepcion_en_guarderia_adoptar_bicho_abandonado() {
		
		this.guarderia.adoptarBichoAbandonado(dummyEntrenador);
	}
	
	@Test
	public void se_retorna_un_bicho_en_guarderia_adoptar_bicho_abandonado() {
		
		this.guarderia.getBichos().add(dummyBicho);
		when(dummyRandom.nextInt(anyInt())).thenReturn(0);
		Bicho bicho = this.guarderia.adoptarBichoAbandonado(dummyEntrenador);
		
		Assert.assertEquals(bicho, dummyBicho);
	}
	
	@Test
	public void se_retorna_un_bicho_en_guarderia_buscar() {
		
		this.guarderia.getBichos().add(dummyBicho);
		when(dummyRandom.nextInt(anyInt())).thenReturn(0);
		Bicho bicho = this.guarderia.buscar(dummyEntrenador);
		
		Assert.assertEquals(bicho, dummyBicho);
		Assert.assertEquals(0, guarderia.getBichos().size());
	}
	
	@Test(expected = NoSePuedeRealizarDueloEnUbicacionException.class)
	public void se_levanta_una_exepcion_cuando_un_entrenador_intenta_realizar_un_duelo() {
		
		this.guarderia.duelo(dummyEntrenador, dummyBicho);
	}

}

