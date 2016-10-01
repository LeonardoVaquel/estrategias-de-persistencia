package model.duelo;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Duelo;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.UbicacionException;
import junit.framework.TestCase;

public class TestDuelo extends TestCase{

	private Duelo duelo;
	private Bicho bichoRetador;
	private Bicho bichoRetado;
	private @Mock Especie especie1, especie2;
	private @Mock Entrenador entrenador1, entrenador2;
	private @Mock Dojo dojo;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		bichoRetador 	= new Bicho(especie1);
		bichoRetado		= new Bicho(especie2);
		duelo			= new Duelo(bichoRetador, bichoRetado);
		bichoRetador.setOwner(entrenador1);
		bichoRetado.setOwner(entrenador2);
		when(entrenador1.getUbicacion()).thenReturn(dojo);
		when(entrenador2.getUbicacion()).thenReturn(dojo);
		when(dojo.getNombre()).thenReturn("Dojo");
	}
	
	@Test
	public void test_UnBichoRetadorRetaADueloABichoRetadoYElRetadoGanaElDueloPorTenerLaMismaEnergiaEntreLosDos(){
		
		bichoRetado.setEnergia(20);
		bichoRetador.setEnergia(20);
		
		ResultadoCombate result = duelo.iniciarDuelo();
		
		assertEquals(duelo.getGanador(), bichoRetado);
		assertEquals(result.getBichoGanador(), bichoRetado);
		assertEquals(result.getBichoPerdedor(), bichoRetador);
	}
	
	@Test
	public void test_UnBichoRetadoGanaUnDueloYSumaUnaVictoriaNueva(){
		
		bichoRetado.setEnergia(20);
		bichoRetador.setEnergia(10);
		
		ResultadoCombate result = duelo.iniciarDuelo();
		
		assertEquals(duelo.getGanador().getVictorias(), new Integer(1));
		assertEquals(result.getBichoGanador().getVictorias(), new Integer(1));
		assertEquals(result.getBichoPerdedor().getVictorias(), new Integer(0));
	}
	
	@Test
	public void test_UnBichoRetadorGanaUnDuelo(){
		
		bichoRetador.setEnergia(50);
		bichoRetado.setEnergia(10);
		
		ResultadoCombate result = duelo.iniciarDuelo();
		
		assertEquals(duelo.getGanador(), bichoRetador);
		assertEquals(result.getBichoGanador(), bichoRetador);
		assertEquals(result.getBichoPerdedor(), bichoRetado);
		
	}
	
	@Test
	public void test_UnBichoRetadorRetaADueloPeroNoGanaPorQueTieneMenosEnergia(){
		
		bichoRetado.setEnergia(20);
		bichoRetador.setEnergia(10);
		
		ResultadoCombate result = duelo.iniciarDuelo();
		
		assertEquals(duelo.getGanador(), bichoRetado);
		assertEquals(result.getBichoGanador(), bichoRetado);
		assertEquals(result.getBichoPerdedor(), bichoRetador);
	}
	
	@Test(expected = UbicacionException.class)
	public void test_UnBichoQueNoEstaEnUnDojoNoPuedeLuchar(){
		when(dojo.getNombre()).thenReturn("Guarderia");
		duelo.iniciarDuelo();
	}
	
}
