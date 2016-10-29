package model.ubicacion;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeAbandonarEnUbicacionException;

public class TestDojo {

	private Dojo dojo;

	private @Mock Entrenador dummyEntrenador; 
	private @Mock Especie dummyEspecie;
	private @Mock Bicho dummyBicho;
	private @Mock Bicho dummyBichoRetador;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		this.dojo = new Dojo("UnNombreDeDojo");
		this.dojo.setHistorial(new ArrayList<Campeon>());
		this.dojo.setCampeon(dummyBicho);
		
		when(dummyBicho.getEspecieRaiz()).thenReturn(dummyEspecie);
		when(dummyEspecie.getEnergiaInicial()).thenReturn(100);
		when(dummyBichoRetador.getEnergia()).thenReturn(1000);
		when(dummyBichoRetador.getOwner()).thenReturn(dummyEntrenador);
	}
	
	@Test
	public void se_retorna_un_nuevo_bicho_con_la_especie_de_campeon_en_dojo_buscar() {
		
		Bicho bicho = this.dojo.buscar(dummyEntrenador);
		
		Assert.assertEquals(bicho.getEspecie(), dummyEspecie);
		Assert.assertEquals(bicho.getEnergia(), dummyEspecie.getEnergiaInicial());
	}
	
	
	@Test(expected = NoSePuedeAbandonarEnUbicacionException.class)
	public void se_levanta_una_excepcion_cuando_un_entrenador_quiere_abandonar_en_un_dojo(){
		
		this.dojo.abandonar(dummyEntrenador, dummyBicho);
	}
	
	@Test
	public void se_devuelve_un_resultado_combate_en_dojo_duelo() {
		
		ResultadoCombate combate = this.dojo.duelo(dummyEntrenador, dummyBichoRetador);
		
		Assert.assertEquals(combate.getBichoGanador(), dummyBichoRetador);
		Assert.assertEquals(combate.getBichoPerdedor(), dummyBicho);
		Assert.assertEquals(combate.getEntrenadorGanador(), dummyBichoRetador.getOwner());
		Assert.assertEquals(this.dojo.getCampeon(), dummyBichoRetador);
		Assert.assertEquals(this.dojo.getHistorial().size(), 1);
	}
	
}
