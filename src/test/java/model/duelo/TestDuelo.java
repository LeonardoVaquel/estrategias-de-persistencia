package model.duelo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Duelo;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import junit.framework.TestCase;

public class TestDuelo extends TestCase{

	private Duelo duelo;
	private Bicho bichoRetador;
	private Bicho bichoRetado;
	private @Mock Especie especie1, especie2;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		bichoRetador 	= new Bicho(especie1);
		bichoRetado		= new Bicho(especie2);
		duelo			= new Duelo(bichoRetador, bichoRetado);
	}
	
	@Test
	public void test_UnBichoRetadorRetaADueloABichoRetadoYElRetadoGanaElDueloPorTenerLaMismaEnergiaEntreLosDos(){
		
		bichoRetado.setEnergia(20);
		bichoRetador.setEnergia(20);
		
		duelo.iniciarDuelo();
		
		assertEquals(duelo.getGanador(), bichoRetado);
	}
	
	@Test
	public void test_UnBichoRetadoGanaUnDueloYSumaUnaVictoriaNueva(){
		
		bichoRetado.setEnergia(20);
		bichoRetador.setEnergia(10);
		
		duelo.iniciarDuelo();
		
		assertEquals(duelo.getGanador().getVictorias(), new Integer(1));
	}
	
	@Test
	public void test_UnBichoRetadorGanaUnDuelo(){
		
		bichoRetador.setEnergia(50);
		bichoRetado.setEnergia(10);
		
		duelo.iniciarDuelo();
		
		assertEquals(duelo.getGanador(), bichoRetador);
		
	}
	
	@Test
	public void test_UnBichoRetadorRetaADueloPeroNoGanaPorQueTieneMenosEnergia(){
		
		bichoRetado.setEnergia(20);
		bichoRetador.setEnergia(10);
		
		duelo.iniciarDuelo();
		
		assertEquals(duelo.getGanador(), bichoRetado);
	}
	
	
	
}
