package model.evolucion;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEdad;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEnergia;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionNivel;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionVictorias;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.Evolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.EvolutionHandler;
import ar.edu.unq.epers.bichomon.backend.service.BichoManager;
import ar.edu.unq.epers.bichomon.backend.service.EvolucionManager;

public class TestEvolutionHandler {
	
	private @Mock Especie especie;
	private @Mock Entrenador entrenador;
	private @Mock Bicho bicho;
	private @Mock BichoManager bichoManager;
	//private @Mock Evolucion evolucion;
	private @Mock Especie especieEvolucion;
	
	private List<CriterioEvolucion> criterios = new ArrayList<>();
	private CriterioEvolucion criterioPorEnergia;
	private CriterioEvolucion criterioPorVictorias;
	private CriterioEvolucion criterioPorEdad;
	private CriterioEvolucion criterioPorNivel;
	
	private EvolutionHandler handler;
	
	@Before
	public void setUp() {
		
		handler = new EvolutionHandler();
		
		MockitoAnnotations.initMocks(this);
		
		criterioPorEnergia   = new CriterioEvolucionEnergia(especie, 200);
		criterioPorVictorias = new CriterioEvolucionVictorias(especie, 100);
		criterioPorEdad      = new CriterioEvolucionEdad(especie, 100);
		criterioPorNivel     = new CriterioEvolucionNivel(especie, 50);
		
		criterios.add(criterioPorEnergia);
		criterios.add(criterioPorVictorias);
		criterios.add(criterioPorEdad);
		criterios.add(criterioPorNivel);

		
		handler.setBichoManager(bichoManager);
		handler.setBicho(bicho);
		handler.setEntrenador(entrenador);
		handler.setEspecie(especie);
		
		when(bicho.getEspecie()).thenReturn(especie);
		
		when(especie.getCriteriosDeEvolucion()).thenReturn(criterios);
		when(especie.getNombre()).thenReturn("testNombre");
		
		when(especie.getEvolucion()).thenReturn(especieEvolucion);
		//when(evolutionManager.getEvolucion("testNombre")).thenReturn(evolucion);
	}
	
	@After
	public void deleteAll() {
		handler 		   	 = null;
		criterioPorEnergia 	 = null;
		criterioPorVictorias = null;
		criterioPorEdad 	 = null;
		criterioPorNivel 	 = null;
		criterios			 = null;
	}
	
	@Test
	public void dados_un_bicho_no_apto_para_evolucionar_y_un_entrenador_se_atrapa_una_excepcion_de_energia_insuficiente(){
		
		// TODO por alguna razón no puedo catchear la excepción, "puedeEvolucionar" retorna false.
		
		when(bicho.getEnergia()).thenReturn(201);
		when(bicho.getVictorias()).thenReturn(99);
		when(bicho.getFechaCaptura()).thenReturn(new DateTime(2016, 9, 20, 0, 0));
		when(entrenador.getNivel()).thenReturn(40);
		
		Assert.assertFalse(handler.puedeEvolucionar());
	}

	@Test
	public void dados_un_bicho_y_un_entrenador_un_handler_dispone_es_posible_evolucionarlo() {
		
		when(bicho.getEnergia()).thenReturn(201);
		when(bicho.getVictorias()).thenReturn(101);
		when(bicho.getFechaCaptura()).thenReturn(new DateTime(2016, 1, 20, 0, 0));
		when(entrenador.getNivel()).thenReturn(51);
		
		Assert.assertEquals(4, especie.getCriteriosDeEvolucion().size());
		Assert.assertTrue(handler.puedeEvolucionar());
	}
	
	@Test
	public void un_handler_evoluciona_un_bicho_apto_para_evolucionar() {
		when(bicho.getEnergia()).thenReturn(201);
		when(bicho.getVictorias()).thenReturn(101);
		when(bicho.getFechaCaptura()).thenReturn(new DateTime(2016, 1, 20, 0, 0));
		when(entrenador.getNivel()).thenReturn(51);
		
		handler.evolucionar();
		
		verify(bicho, times(1)).evolucionar(especieEvolucion);
	}
	
}
