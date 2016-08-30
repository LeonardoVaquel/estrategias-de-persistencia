package ar.edu.unq.epers.bichomon.backend.service.data;

import java.util.HashMap;
import java.util.Map;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;

/**
 * Esta clase mantiene un repositorio con dummyData Especie 
 * para hacer pruebas sobre una Base de Datos.
 * @author santiago
 *
 */
public class DataServiceEspecie implements DataService {

	public EspecieDAO dao = new EspecieDAO(); 
	
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
	
}