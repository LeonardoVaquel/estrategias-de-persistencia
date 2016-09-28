package model.ubicacion;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.collection.BichoCollection;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.ExpHandler;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;

public class TestDojo {
	private Dojo dojo;
	private Entrenador entrenador;
	private @Mock ExpHandler handler; 
	private @Mock Especie especie;
	private @Mock Bicho bicho1, bicho2, bicho3;
	private @Mock BichoCollection collection;
	Ubicacion ubicacion;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.entrenador= new Entrenador("Pedro",handler, dojo, collection);

		this.entrenador.obtenerBicho(bicho1);
		this.entrenador.obtenerBicho(bicho2);
		this.entrenador.obtenerBicho(bicho3);
	}
	@Test
	public void buscarEnDojo(){
		Random myRandom = mock(Random.class);
		when(myRandom.nextInt(3)).thenReturn(1);

		this.dojo= new Dojo("dojo", myRandom);
		this.dojo.buscar(entrenador);
		Ubicacion ubicacionMock= mock(Ubicacion.class);
		Bicho bichoSeleccionado = mock(Bicho.class);
		when(bichoSeleccionado.getOwner()).thenReturn(entrenador);
		List<Bicho> miListaDeBichos= mock(List.class);
		when(miListaDeBichos.get(1)).thenReturn(bichoSeleccionado);
		when(miListaDeBichos.size()).thenReturn(3);
		dojo.setBicho(miListaDeBichos);
		
		Entrenador expected = this.entrenador;
		assertSame(expected,bichoSeleccionado.getOwner());
		
		assertTrue(miListaDeBichos.get(1)== bichoSeleccionado);
		assertTrue (bichoSeleccionado.getOwner()== entrenador);

		
	}
}
