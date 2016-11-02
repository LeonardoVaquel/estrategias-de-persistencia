package neo4j;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.neo4j.Neo4jMapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.service.mapa.UbicacionMuyLejana;

public class TestNeo4jMapaDAO {

	
	Neo4jMapaDAO mapaDAO;
	
	@Before
	public void setUp() {
		
		this.mapaDAO = new Neo4jMapaDAO();
		
	}
	
	@Test(expected=UbicacionMuyLejana.class)
	public void dadas_dos_ubicaciones_que_no_se_conectan_de_un_salto_se_levanta_una_exepcion() {
		
		this.mapaDAO.getCostoDesdeHasta("Bernal", "F. Varela");
	}
	
	@Test
	public void dadas_dos_ubicaciones_se_obtiene_el_costo() {
		Integer costo = this.mapaDAO.getCostoDesdeHasta("Bernal", "Don Bosco");
		
		Assert.assertEquals(costo, 2, 0);
	}
	
	@Test
	public void se_crea_una_ubicacion_con_nombre_en_neo4j() {
		
		Dojo dojo = new Dojo("Berazategui");
		this.mapaDAO.crearUbicacion(dojo);
		
	}
	
	@Test
	public void se_conecta_una_ubicacion_f_varela_con_otra_ubicacion_bernal() {
		
		this.mapaDAO.conectar("F. Varela", "Bernal", "TERRESTRE");
		
		Integer costo = this.mapaDAO.getCostoDesdeHasta("F. Varela", "Bernal");
		
		Assert.assertEquals(1, costo, 0);

	}

}
