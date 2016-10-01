package model.ubicacion;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollection;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.ExpHandler;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

public class TestGuarderia {
	private Guarderia guarderiaSut;
	private Entrenador entrenador,entrenador2;
	private @Mock ExpHandler handler; 
	private @Mock Especie especie;
	private @Mock Bicho bicho1, bicho2;
	private @Mock BichoCollection collection;
	Ubicacion ubicacion;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.entrenador= new Entrenador("Pedro",handler, guarderiaSut);
		this.entrenador2= new Entrenador("Juan",handler, guarderiaSut);
		Random myRandom = mock(Random.class);
		when(myRandom.nextInt(3)).thenReturn(1);
		this.guarderiaSut= new Guarderia("guarderia", myRandom);

	
	}
	@Test 
	public void abandonarBicho(){
		this.entrenador.obtenerBicho(bicho1);
		this.entrenador.obtenerBicho(bicho2);
		
		guarderiaSut.abandonar(entrenador, bicho1);
		
		assertTrue(guarderiaSut.cantidadDeBichosAbandonados()==1);
		
	}
	@Test
	public void adoptarBicho(){
		
		this.guarderiaSut.buscar(entrenador2);
		
		Ubicacion ubicacionMock= mock(Ubicacion.class);
		Bicho bichoSeleccionado = mock(Bicho.class);
		Bicho bicho2=mock(Bicho.class);
		
		when(bicho2.getOwner()).thenReturn(entrenador2);
		when(bichoSeleccionado.getOwner()).thenReturn(entrenador);
		
		List<Bicho> miListaDeBichos= mock(List.class);
		when (miListaDeBichos.get(0)).thenReturn(bicho2);
		when(miListaDeBichos.get(1)).thenReturn(bichoSeleccionado);
		when(miListaDeBichos.size()).thenReturn(3);
		guarderiaSut.setBicho(miListaDeBichos);
			
		
		Entrenador expected = this.entrenador;
		//Verifica que el entrenador del bicho seleccionado sea el mismo que el
		// del entrenador esperado 
		assertSame(expected,bichoSeleccionado.getOwner());
		//Verifica que se cargue correctamente en la posicion 1 el bicho
		assertTrue(miListaDeBichos.get(1)== bichoSeleccionado);
		//Verifica que el bicho fue adoptado correctamente 
		assertSame(guarderiaSut.adoptarBichoAbandonado(),bichoSeleccionado);
		
	}
	
}

