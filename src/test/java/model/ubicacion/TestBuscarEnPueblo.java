package model.ubicacion;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Buscador;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.buscador.TuplaEspecieLista;
import ar.edu.unq.epers.bichomon.backend.model.buscador.TuplaEspecieProbabilidad;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import junit.framework.TestCase;

public class TestBuscarEnPueblo extends TestCase{

	private Buscador buscador;
	private List<TuplaEspecieProbabilidad> lsDB;
	private TuplaEspecieProbabilidad tupla1;
	private TuplaEspecieProbabilidad tupla2;
	private TuplaEspecieProbabilidad tupla3; 
	private Especie especie1;
	private Especie especie2;
	private Especie especie3;

	@Before
	public void setUp(){
//		MockitoAnnotations.initMocks(this);
		this.especie1	= new Especie("Especie1",TipoBicho.TIERRA);
		this.especie2	= new Especie("Especie2",TipoBicho.AGUA);
		this.especie3	= new Especie("Especie3", TipoBicho.FUEGO);
		System.out.println(especie1.getNombre());
				
		this.tupla1 	= new TuplaEspecieProbabilidad(especie1, Float.valueOf(0.3f));
		this.tupla2 	= new TuplaEspecieProbabilidad(especie2, Float.valueOf(0.7f));
		this.tupla3 	= new TuplaEspecieProbabilidad(especie3, Float.valueOf(1.5f));
		
//		System.out.println("("+tupla1.getKey()+", "+tupla1.getValue()+")");
//		System.out.println("("+tupla2.getKey()+", "+tupla2.getValue()+")");
//		System.out.println("("+tupla3.getKey()+", "+tupla3.getValue()+")");
//		
		
		this.lsDB		= new ArrayList<>();
		this.lsDB.add(tupla1);
		this.lsDB.add(tupla2);
		this.lsDB.add(tupla3);
		
//		System.out.println(lsDB);
		this.buscador	= new Buscador(lsDB);
	}

	@Test
	public void test_SeConvierteUnaListaDeProbabilidadAUnaDeTuplaLista(){
		
		List<TuplaEspecieLista> lsFinal 	= buscador.convertList(lsDB);
		List<Integer>ls 		= lsFinal.get(0).getValue();
		List<Integer>ls1 		= lsFinal.get(1).getValue();
		List<Integer>ls2		= lsFinal.get(2).getValue();
		
		assertTrue(ls.contains(29));
		assertTrue(ls1.contains(60));
		assertTrue(ls2.contains(100));
		
	}
	
	@Test
	public void test_BuscoUnBichoYMeSaleUnoDeEspecie1(){
		
		Bicho bichoEncontrado = buscador.buscar(1);
		
		assertEquals(bichoEncontrado.getEspecie(), especie1);
	}
	
	@Test
	public void test_BuscoUnBichoYMeSaleUnoDeEspecie2(){
		
		Bicho bichoEncontrado = buscador.buscar(31);
		
		assertEquals(bichoEncontrado.getEspecie(), especie2);
	
	}
	
	@Test
	public void test_BuscoUnBichoYMeSaleUnoDeEspecie3(){
		
		Bicho bichoEncontrado = buscador.buscar(71);
		
		assertEquals(bichoEncontrado.getEspecie(), especie3);
	
	}
}
