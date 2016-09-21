package hibernate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.epers.bichomon.backend.model.buscador.Buscador;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

public class TestHibernateBuscarEnPueblo{

	private Buscador buscador;
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
				
		this.tupla1 	= new Tupla(especie1, 0.3);
		this.tupla2 	= new Tupla(especie2, 0.7);
		this.tupla3 	= new Tupla(especie3, 1.5);
		
		this.lsDB		= new ArrayList<>();
		this.lsDB.add(tupla1);lsDB.add(tupla2);lsDB.add(tupla3);
		
		this.buscador	= new Buscador(lsDB, 100);
	}

	@Test
	public void testSeBuscaUnBichomonDeEspecie1(){
		
		List<Tupla> result = this.buscador.buscar();
		List<Integer>ls = result.get(1).getLsValue();
		assertEquals(result.size(),3);
		
	}
}
