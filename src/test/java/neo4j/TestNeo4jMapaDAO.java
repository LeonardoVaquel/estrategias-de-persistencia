package neo4j;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.neo4j.Neo4jMapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.mapa.UbicacionMuyLejana;

public class TestNeo4jMapaDAO {

	
	private Neo4jMapaDAO mapaDAO;
	private DataManager dataManager;
	
	@Before
	public void setUp() {
		
		this.mapaDAO 	 = new Neo4jMapaDAO();
		this.dataManager = new DataManager();
//		this.dataManager.crearSetDeUbicacionesNeo4j();
	}
	
	@After
	public void deleteUbicaciones() {
//		this.dataManager.eliminarUbicaciones();
	}
	
	@Test
	public void dadas_dos_ubicaciones_no_lindantes_se_obtiene_el_costo_total_entre_ambas() {
		
		Integer costo = this.mapaDAO.getCostoEntreUbicaciones("Quilmes", "Bernal");
		
		Assert.assertEquals(1, costo, 0);		
	}
	
	@Test(expected=UbicacionMuyLejana.class)
	public void dadas_dos_ubicaciones_que_no_se_conectan_de_un_salto_se_levanta_una_exepcion() {
		
		this.mapaDAO.getCostoLindantes("Quilmes", "Don Bosco");
	}
	
	@Test
	public void dadas_dos_ubicaciones_se_obtiene_el_costo() {
		Integer costo = this.mapaDAO.getCostoLindantes("Bernal", "Don Bosco");
		
		Assert.assertEquals(costo, 2, 0);
	}
	
	@Test
	public void se_conecta_una_ubicacion_f_varela_con_otra_ubicacion_quilmes() {
		
		this.mapaDAO.conectar("F. Varela", "Quilmes", "TERRESTRE");
		
		Integer costo = this.mapaDAO.getCostoLindantes("F. Varela", "Quilmes");
		
		Assert.assertEquals(1, costo, 0);
	}
	
	@Test
	public void todasLasUbicacionesConectadasMedianteUnTipoDeCaminoSeObtiene(){
		
		List<String> result = this.mapaDAO.conectados("Bernal", "MARITIMO");
		
		Assert.assertEquals(result.size(),1,0);
		Assert.assertEquals(result.get(0),"Don Bosco");
	}
	
}
