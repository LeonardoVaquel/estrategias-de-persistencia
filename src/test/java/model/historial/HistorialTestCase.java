package model.historial;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Historial;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import junit.framework.TestCase;

public class HistorialTestCase extends TestCase {

	private Historial historial;
	private List<Campeon> listaDeCampeones;
	private @Mock Campeon dummycampeon1,dummycampeon2,dummycampeon3;
	private @Mock Entrenador dummyEntrenador;
	private @Mock Bicho dummyBicho;
	private @Mock Dojo dummyDojo;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		listaDeCampeones = new ArrayList<>();
		listaDeCampeones.add(dummycampeon1);
		listaDeCampeones.add(dummycampeon2);
		listaDeCampeones.add(dummycampeon3);
		historial = new Historial();
		historial.setCampeones(listaDeCampeones);
	}
	
	@Test
	public void test1_ElHistorialSeIniciaCon3Campeones(){
		
		assertEquals(historial.totalDeCampeones(), new Integer(3));
	}
		
	@Test
	public void test2_SeAgregaAlHistorialUnNuevoCampeon(){
		when(dummyBicho.getOwner()).thenReturn(dummyEntrenador);
		
		historial.agregar(dummyBicho, LocalDateTime.now(), dummyBicho.getOwner(), dummyDojo);
	
		assertEquals(historial.totalDeCampeones(), new Integer(4));
	}
	
	@Test
	public void test3_SeDerrocoAUnCampeonYSuFechaDeDerrocadoCambio(){
		LocalDateTime fechaNueva = LocalDateTime.now();
		when(dummycampeon3.getCampeon()).thenReturn(dummyBicho);
		historial.actualizarCampeon(dummyBicho, fechaNueva);
	
		verify(dummycampeon3).setDerrocado(fechaNueva);
	}
	
}
