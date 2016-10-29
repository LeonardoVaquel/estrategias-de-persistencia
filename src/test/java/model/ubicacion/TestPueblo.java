package model.ubicacion;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeAbandonarEnUbicacionException;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeRealizarDueloEnUbicacionException;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Pueblo;

public class TestPueblo {

	private Pueblo pueblo;
	
	@Mock Entrenador dummyEntrenador;
	@Mock Bicho dummyBicho;
	@Mock Especie dummyEspecie1;
	@Mock Tupla dummyTupla;
	@Mock List<Tupla> dummyEspecieList;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		this.pueblo = new Pueblo("unNombreDePueblo");
		
		this.dummyEspecieList.add(dummyTupla);
		
		this.pueblo.setListaDeEspecies(dummyEspecieList);
	}
	
//	@Test
	public void se_retorna_un_bicho_en_pueblo_buscar() {
		
		Bicho bicho = this.pueblo.buscar(dummyEntrenador);
		
		Assert.assertEquals(bicho.getEspecie(), dummyEspecie1);
		
	}
	
	@Test(expected = NoSePuedeAbandonarEnUbicacionException.class)
	public void se_levanta_una_exepcion_cuando_se_quiere_abandonar_un_bicho() {
		
		this.pueblo.abandonar(dummyEntrenador, dummyBicho);
	}
	
	@Test(expected = NoSePuedeRealizarDueloEnUbicacionException.class)
	public void se_levanta_una_exepcion_cuando_se_quiere_realizar_un_duelo() {
		
		this.pueblo.duelo(dummyEntrenador, dummyBicho);
	}
	
}
