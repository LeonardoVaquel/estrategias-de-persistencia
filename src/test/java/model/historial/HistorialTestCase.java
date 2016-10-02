package model.historial;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Historial;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import junit.framework.TestCase;

public class HistorialTestCase extends TestCase {

	private Historial historial;
	private List<Campeon> listaDeCampeones;
	private @Mock Campeon Dummycampeon1,Dummycampeon2,Dummycampeon3;
	private @Mock Entrenador DummyEntrenador;
	private @Mock Bicho DummyBicho;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		listaDeCampeones = new ArrayList<>();
		listaDeCampeones.add(Dummycampeon1);
		listaDeCampeones.add(Dummycampeon2);
		listaDeCampeones.add(Dummycampeon3);
		historial = new Historial();
		historial.setCampeones(listaDeCampeones);
	}
	
	@Test
	public void test1_ElHistorialSeIniciaCon3Campeones(){
		
		assertEquals(historial.totalDeCampeones(), new Integer(3));
	}
		
	@Test
	public void test2_SeAgregaAlHistorialUnNuevoCampeon(){
		when(DummyBicho.getOwner()).thenReturn(DummyEntrenador);
		
		historial.agregar(DummyBicho, new LocalDateTime(), DummyBicho.getOwner(), "Bernal-Dojo");
	
		assertEquals(historial.totalDeCampeones(), new Integer(4));
	}
	
	@Test
	public void test3_SeDerrocoAUnCampeonYSuFechaDeDerrocadoCambio(){
		LocalDateTime fechaNueva = new LocalDateTime();
		when(Dummycampeon3.getCampeon()).thenReturn(DummyBicho);
		historial.actualizarCampeon(DummyBicho, fechaNueva);
	
		verify(Dummycampeon3).setDerrocado(fechaNueva);
	}
	
}
