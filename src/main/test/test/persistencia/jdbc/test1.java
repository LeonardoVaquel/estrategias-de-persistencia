package test.persistencia.jdbc;

import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

public class test1 {

	EspecieDAO  especieDAO;
	Bicho bichoFort;		
	Especie especie, esp;
	
	@Before
	public void setUp(){
		
		especieDAO = new EspecieDAO();
		Especie especie= new Especie();
		especie.setAltura(1);
		especie.setCantidadBichos(1);
		especie.setEnergiaIncial(10);
		especie.setNombre("especieNva");
		especie.setPeso(0);
		especie.setTipo(null);
		especie.setUrlFoto("url");;
		
		bichoFort = especieDAO.crearBicho("CHOCOLATE", "fort");
		
	}
	
	@Test
	public void testBicho(){
		
		assertTrue(bichoFort.getNombre() == "fort");
	}
	@Test
	public void testEspecie(){
		assertTrue(especieDAO.getEspecie("").getNombre()== "especieNva");
	}
	
	//@After
	//public void drop(){
		//especieDAO.removeEspecie(especie);
		
	//}
}
