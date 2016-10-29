package hibernate;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateLeaderboardDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.DataManager;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoSessionService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataSessionService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardSessionService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * TestHibernateLeaderbaordService es una clase para hacer pruebas con servicios
 * relacionados con instancias de {@link Entrenador} y {@link Ubicacion} del
 * juego, en un ambiente persistente.
 * 
 * @author santiago
 */
public class TestHibernateLeaderboardService {

	private LeaderboardSessionService service;
	private LeaderboardService hibernateLeaderboardDAO;
	private DataService dataService;
	private GenericService testService;
	private BichoSessionService bichoService;
	private BichoDAO bichoDAO;
	private EntrenadorDAO entrenadorDAO;
	private ExperienciaDAO experienciaDAO;

	@Before
	public void prepare() {

		this.hibernateLeaderboardDAO = new HibernateLeaderboardDAO();
		this.service 	 = new LeaderboardSessionService(hibernateLeaderboardDAO);
		this.dataService = new DataSessionService(new DataManager());
		this.testService = new GenericService();

		this.bichoDAO 		= new HibernateBichoDAO();
		this.entrenadorDAO 	= new HibernateEntrenadorDAO();
		this.experienciaDAO = new HibernateExperienciaDAO();
		this.bichoService 	= new BichoSessionService(bichoDAO, entrenadorDAO, experienciaDAO);

		dataService.crearSetDatosIniciales();
	}

	@After
	public void deleteAll() {
		this.dataService.eliminarTablas();
	}

	@Test
	public void se_obtienen_aquellos_entrenadores_que_poseen_un_bicho_que_actualmente_es_campeon_en_orden_descendente_por_fecha_de_victoria() {

		Runner.runInSession(() -> {

			Entrenador entrenador1 = this.testService.recuperarEntidad(Entrenador.class, "Vegetal");

			Bicho bicho1 = this.testService.recuperarEntidad(Bicho.class, 8);

			this.bichoService.duelo(entrenador1.getNombre(), bicho1.getId());

			List<Entrenador> entrenadores = this.service.campeones();

			Assert.assertEquals(4, entrenadores.size());
			Assert.assertEquals(entrenadores.get(0).getNombre(), "Santiago");
			Assert.assertEquals(entrenadores.get(1).getNombre(), "Jackson");
			Assert.assertEquals(entrenadores.get(2).getNombre(), "Jackson");
			Assert.assertEquals(entrenadores.get(3).getNombre(), "Vegetal");

			return null;
		});

	}

}
