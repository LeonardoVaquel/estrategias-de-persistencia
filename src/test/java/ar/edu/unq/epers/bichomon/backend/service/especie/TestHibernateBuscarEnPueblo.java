package ar.edu.unq.epers.bichomon.backend.service.especie;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.BuscadorEspecie;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import junit.framework.TestCase;

public class TestHibernateBuscarEnPueblo extends TestCase{

	private BuscadorEspecie buscador;
	private List<Tupla> lsDB;
	private Tupla tupla1;
	private Tupla tupla2;
	private Tupla tupla3; 
	private @Mock Especie especie1;
	private @Mock Especie especie2;
	private @Mock Especie especie3;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
				
		this.tupla1 	= new Tupla(especie1, 0.3f);
		this.tupla2 	= new Tupla(especie2, 0.7f);
		this.tupla3 	= new Tupla(especie3, 1.5f);
		
		this.lsDB		= new ArrayList<>();
		this.lsDB.add(tupla1);
		this.lsDB.add(tupla2);
		this.lsDB.add(tupla3);
		
		this.buscador	= new BuscadorEspecie(lsDB);
	}

	@Test
	public void testSeBuscaUnBichomonDeEspecie1(){
		
		List<Tupla> lsFinal 	= this.buscador.buscarAux();
		List<Integer>ls 		= lsFinal.get(0).getLsValue();
		List<Integer>ls1 		= lsFinal.get(1).getLsValue();
		List<Integer>ls2		= lsFinal.get(2).getLsValue();
		
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
