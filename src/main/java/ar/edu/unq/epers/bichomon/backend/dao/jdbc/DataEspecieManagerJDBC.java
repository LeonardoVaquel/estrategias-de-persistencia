package ar.edu.unq.epers.bichomon.backend.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Esta clase mantiene un repositorio con dummyData Especie 
 * para hacer pruebas sobre una Base de Datos.
 * @author santiago
 *
 */
public class DataEspecieManagerJDBC implements DataService {

	public JDBCEspecieDAO dao = new JDBCEspecieDAO(); 
	
	public static Map<String, Especie> DATAEspecies = new HashMap<>();
	
	static {
		Especie leoMon = new Especie("Leomon", TipoBicho.CHOCOLATE);
		leoMon.setAltura(1);
		leoMon.setPeso(1);
		leoMon.setEnergiaIncial(100);
		leoMon.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/leomon.jpg");
		leoMon.setCantidadBichos(0);
		DATAEspecies.put(leoMon.getNombre(), leoMon);
		
		Especie giseMon = new Especie("Gisemon", TipoBicho.FUEGO);
		giseMon.setAltura(150);
		giseMon.setPeso(80);
		giseMon.setEnergiaIncial(100);
		giseMon.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/gisemon.jpg");
		giseMon.setCantidadBichos(0);
		DATAEspecies.put(giseMon.getNombre(), giseMon);
		
		Especie bottiMon = new Especie("Bottimon", TipoBicho.AIRE);
		bottiMon.setAltura(180);
		bottiMon.setPeso(70);
		bottiMon.setEnergiaIncial(100);
		bottiMon.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/bottimon.jpg");
		bottiMon.setCantidadBichos(0);
		DATAEspecies.put(bottiMon.getNombre(), bottiMon);
		
		
	}
	
	@Override
	public void eliminarDatos() {
		dao.removeAllEspecies();
	}

	@Override
	public void crearSetDatosIniciales() {

		dao.crearEspecie(DATAEspecies.get("Leomon"));
		dao.crearEspecie(DATAEspecies.get("Gisemon"));
		dao.crearEspecie(DATAEspecies.get("Bottimon"));		
	}

	@Override
	public void eliminarTablas() {
		Runner.runInSession( () -> {


			Session session = Runner.getCurrentSession();
			List<String> nombreDeTablas = session.createNativeQuery("show tables").getResultList();
			session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
			nombreDeTablas.forEach(tabla -> {
				session.createNativeQuery("truncate table " + tabla).executeUpdate();
			});
			session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
			return null;
		});
		
	}

	@Override
	public void crearTestSetDeUbicacionesNeo4j() {
		// TODO Auto-generated method stub
	}

	@Override
	public void crearSetDeUbicaciones() {
		// TODO Auto-generated method stub		
	}
	
}