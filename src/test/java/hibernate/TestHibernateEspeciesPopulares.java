package hibernate;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class TestHibernateEspeciesPopulares {

	private HibernateEspecieDAO especieDAO;
	private DataService dataService;
	
	@Before
	public void prepare() {
		
		this.especieDAO = new HibernateEspecieDAO();
		this.dataService    = new DataSessionService(new DataManager());
		
		this.dataService.crearSetDatosIniciales();
	}
	
	@After
	public void deleteAll() {
		this.dataService.eliminarTablas();
	}
	
	
	@Test
	public void se_obtienen_las_especies_mas_populares(){
		Runner.runInSession(() ->{
			List<Especie> especies = this.especieDAO.populares();
			Especie especie1 = especies.get(0);
			Especie especie2 = especies.get(1);
			Especie especie3 = especies.get(2);
			Especie especie4 = especies.get(3);
			Especie especie5 = especies.get(4);
			Especie especie6 = especies.get(5);
			
			Assert.assertEquals(especie1.getNombre(), "Bottimon");
			Assert.assertEquals(especie2.getNombre(), "FrutiMon");
			Assert.assertEquals(especie3.getNombre(), "Gisemon");
			Assert.assertEquals(especie4.getNombre(), "GisemonEvolucion");
			Assert.assertEquals(especie5.getNombre(), "Leomon");
			Assert.assertEquals(especie6.getNombre(), "LeomonEvolucion");
			
			Assert.assertEquals(2, especie1.getCantidadBichos());
			Assert.assertEquals(1, especie2.getCantidadBichos());
			Assert.assertEquals(1, especie3.getCantidadBichos());
			Assert.assertEquals(1, especie4.getCantidadBichos());
			Assert.assertEquals(1, especie5.getCantidadBichos());
			Assert.assertEquals(0, especie6.getCantidadBichos());
			
		return null;		
		});
	}
	
}
