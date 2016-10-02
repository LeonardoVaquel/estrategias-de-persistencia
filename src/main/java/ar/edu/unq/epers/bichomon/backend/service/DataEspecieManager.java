package ar.edu.unq.epers.bichomon.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEdad;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEnergia;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionNivel;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionVictorias;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Pueblo;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;

/**
 * Esta clase mantiene un repositorio con dummyData Especie 
 * para hacer pruebas sobre una Base de Datos.
 * @author santiago
 *
 */
public class DataEspecieManager implements DataService {

	private EspecieDAO especieDao = new HibernateEspecieDAO();
	private BichoDAO bichoDAO = new HibernateBichoDAO();
	private EntrenadorDAO entrenadorDAO = new HibernateEntrenadorDAO();
	
	private ServiceFactory services = new ServiceFactory();
	
	public static Map<String, Especie> DATAEspecies = new HashMap<>();
	public static Map<String, Bicho> DATABichos = new HashMap<>();
	public static Map<String, Entrenador> DATAEntrenadores = new HashMap<>();
	
	static {
		Especie leoMon = new Especie("Leomon", TipoBicho.CHOCOLATE);
		Especie leoMonEvolucion = new Especie("LeomonEvolucion", TipoBicho.CHOCOLATE);
		
		// Leomon
		leoMon.setRaiz(null);
		leoMon.setEvolucion(null);
			List<CriterioEvolucion> criteriosLeomon = new ArrayList<>();
			criteriosLeomon.add(new CriterioEvolucionEnergia(200));
		leoMon.setCriteriosDeEvolucion(criteriosLeomon);
		leoMon.setAltura(1);
		leoMon.setPeso(1);
		leoMon.setEnergiaIncial(100);
		leoMon.setUrlFoto("http://leomon.jpg");
		leoMon.setCantidadBichos(0);
		

		// LeomonEvolucion
		leoMonEvolucion.setRaiz(null);
		leoMonEvolucion.setEvolucion(null);
			List<CriterioEvolucion> criteriosEvolucionLeomon = new ArrayList<>();
			criteriosEvolucionLeomon.add(new CriterioEvolucionNivel(5));
		leoMonEvolucion.setCriteriosDeEvolucion(criteriosEvolucionLeomon);
		leoMonEvolucion.setAltura(10);
		leoMonEvolucion.setPeso(10);
		leoMonEvolucion.setEnergiaIncial(100);
		leoMonEvolucion.setUrlFoto("http://leomonEvolucion.jpg");
		leoMonEvolucion.setCantidadBichos(0);
		
		//Gisemon
		Especie giseMon = new Especie("Gisemon", TipoBicho.FUEGO);
		Especie giseMonEvolucion = new Especie("GisemonEvolucion", TipoBicho.FUEGO);
		
		giseMon.setRaiz(null);
		giseMon.setEvolucion(null);
			List<CriterioEvolucion> criteriosGisemon = new ArrayList<>();
			criteriosGisemon.add(new CriterioEvolucionVictorias(5));
		giseMon.setCriteriosDeEvolucion(criteriosGisemon);		
		giseMon.setAltura(150);
		giseMon.setPeso(80);
		giseMon.setEnergiaIncial(100);
		giseMon.setUrlFoto("http://gisemon.jpg");
		giseMon.setCantidadBichos(0);
		
		//GisemonEvolucion		
		giseMonEvolucion.setRaiz(null);
		giseMonEvolucion.setEvolucion(null);
			List<CriterioEvolucion> criteriosEvolucionGisemon = new ArrayList<>();
			criteriosEvolucionGisemon.add(new CriterioEvolucionNivel(5));
			criteriosEvolucionGisemon.add(new CriterioEvolucionEnergia(200));
		giseMonEvolucion.setCriteriosDeEvolucion(criteriosEvolucionGisemon);		
		giseMonEvolucion.setAltura(150);
		giseMonEvolucion.setPeso(80);
		giseMonEvolucion.setEnergiaIncial(100);
		giseMonEvolucion.setUrlFoto("http://gisemonEvolucion.jpg");
		giseMonEvolucion.setCantidadBichos(0);		
		
		//Bottimon		
		Especie bottiMon = new Especie("Bottimon", TipoBicho.AIRE);
		bottiMon.setRaiz(null);
		bottiMon.setEvolucion(null);
			List<CriterioEvolucion> criteriosBottimon = new ArrayList<>();
			criteriosBottimon.add(new CriterioEvolucionEnergia(200));
			criteriosBottimon.add(new CriterioEvolucionEdad(10));
		bottiMon.setCriteriosDeEvolucion(criteriosBottimon);		
		bottiMon.setAltura(180);
		bottiMon.setPeso(70);
		bottiMon.setEnergiaIncial(100);
		bottiMon.setUrlFoto("http://bottimon.jpg");
		bottiMon.setCantidadBichos(0);
		
		//FrutiMon
		Especie frutiMon = new Especie("FrutiMon", TipoBicho.FUEGO);
		frutiMon.setRaiz(null);
		frutiMon.setEvolucion(null);
			List<CriterioEvolucion> criteriosFrutimon = new ArrayList<>();
			criteriosFrutimon.add(new CriterioEvolucionEnergia(300));
			criteriosFrutimon.add(new CriterioEvolucionEdad(100));
		frutiMon.setCriteriosDeEvolucion(criteriosFrutimon);		
		frutiMon.setAltura(700);
		frutiMon.setPeso(300);
		frutiMon.setEnergiaIncial(180);
		frutiMon.setUrlFoto("http://frutimon.jpg");
		frutiMon.setCantidadBichos(0);		
		
		//Creación de Entrenador
		Entrenador santiagoTrainer = new Entrenador("Santiago");
		
		Entrenador jacksonTrainer = new Entrenador("Jackson");
		jacksonTrainer.setUbicacion(new Guarderia("Guarderia las 24 horas!"));
		
		Entrenador majinTrainer = new Entrenador("Majin");
		
		Entrenador vegetalTrainer = new Entrenador("Vegetal");
		
		Entrenador explorer01 = new Entrenador("Explorador1");
		Entrenador explorer02 = new Entrenador("Explorador2");
		Entrenador explorer03 = new Entrenador("Explorador3");
		Entrenador explorer04 = new Entrenador("Explorador4");
		Entrenador explorer05 = new Entrenador("Explorador5");
		Entrenador explorer06 = new Entrenador("Explorador6");
		
		//Set raíces y evoluciones
		leoMon.setRaiz(leoMon);
		leoMon.setEvolucion(leoMonEvolucion);
		leoMonEvolucion.setRaiz(leoMon);

		giseMon.setRaiz(giseMon);
		giseMon.setEvolucion(giseMonEvolucion);
		giseMonEvolucion.setRaiz(giseMon);
		
		bottiMon.setRaiz(bottiMon);
		
		frutiMon.setRaiz(frutiMon);
		
		//Creación de Bicho
		Bicho bichoLeomon = new Bicho(leoMon);
		bichoLeomon.setEnergia(250);
		
		Bicho bichoLeomonEvolucion = new Bicho(leoMonEvolucion);		
		
		Bicho bichoGisemon = new Bicho(giseMon);
		bichoGisemon.setVictorias(10);
		
		Bicho bichoGisemonEvolucion = new Bicho(giseMonEvolucion);
		bichoGisemonEvolucion.setVictorias(100);
		bichoGisemonEvolucion.setEnergia(200);
		
		Bicho bichoBottimon = new Bicho(bottiMon);
		Bicho bichoBottimon2 = new Bicho(bottiMon);
		
		Bicho bichoFrutiMon = new Bicho(frutiMon);
		bichoFrutiMon.setEnergia(180);
		bichoFrutiMon.setVictorias(5);
		
		//Entrenador obtiene bichos
		santiagoTrainer.obtenerBicho(bichoLeomon);
		santiagoTrainer.obtenerBicho(bichoLeomonEvolucion);
		
		jacksonTrainer.obtenerBicho(bichoBottimon);
		jacksonTrainer.obtenerBicho(bichoGisemon);
		
		vegetalTrainer.obtenerBicho(bichoGisemonEvolucion);
		
		majinTrainer.obtenerBicho(bichoFrutiMon);
		
		//Creación de Ubicacion
		
		Dojo torreKarinDojo = new Dojo("Torre Karin");
		torreKarinDojo.setCampeon(bichoFrutiMon);
		
		Pueblo neverLand = new Pueblo("Neverland");
		
		santiagoTrainer.setUbicacion(torreKarinDojo);
		
		explorer01.setUbicacion(neverLand);
		explorer02.setUbicacion(neverLand);
		explorer03.setUbicacion(neverLand);
		explorer04.setUbicacion(torreKarinDojo);
		explorer05.setUbicacion(torreKarinDojo);
		explorer06.setUbicacion(torreKarinDojo);
		
		vegetalTrainer.setUbicacion(torreKarinDojo);
		
		// Hashmap assignation
		DATAEspecies.put(leoMon.getNombre(), leoMon);
		DATAEspecies.put(leoMonEvolucion.getNombre(), leoMonEvolucion);
		DATAEspecies.put(giseMon.getNombre(), giseMon);
		DATAEspecies.put(giseMonEvolucion.getNombre(), giseMonEvolucion);
		DATAEspecies.put(bottiMon.getNombre(), bottiMon);
		
		DATAEntrenadores.put(santiagoTrainer.getNombre(), santiagoTrainer);
		DATAEntrenadores.put(jacksonTrainer.getNombre(), jacksonTrainer);
		DATAEntrenadores.put(explorer01.getNombre(), explorer01);
		DATAEntrenadores.put(explorer02.getNombre(), explorer02);
		DATAEntrenadores.put(explorer03.getNombre(), explorer03);
		DATAEntrenadores.put(explorer04.getNombre(), explorer04);
		DATAEntrenadores.put(explorer05.getNombre(), explorer05);
		DATAEntrenadores.put(explorer06.getNombre(), explorer06);
		
		DATABichos.put("BichoLeomon", bichoLeomon);
		DATABichos.put("BichoGisemon", bichoGisemon);
		DATABichos.put("BichoGisemonEvolucion", bichoGisemonEvolucion);
		DATABichos.put("BichoBottimon", bichoBottimon);
		DATABichos.put("BichoBottimon2", bichoBottimon2);
		DATABichos.put("FrutiMon", bichoFrutiMon);
		
		
	}
	
	@Override
	public void eliminarDatos() {
		especieDao.removeAllEspecies();
	}

	@Override
	public void crearSetDatosIniciales() {

		especieDao.crearEspecie(DATAEspecies.get("Leomon"));
		especieDao.crearEspecie(DATAEspecies.get("LeomonEvolucion"));
		especieDao.crearEspecie(DATAEspecies.get("Gisemon"));
		especieDao.crearEspecie(DATAEspecies.get("GisemonEvolucion"));
		especieDao.crearEspecie(DATAEspecies.get("Bottimon"));
		
		bichoDAO.guardarBicho(DATABichos.get("BichoLeomon"));
		bichoDAO.guardarBicho(DATABichos.get("BichoGisemon"));
		bichoDAO.guardarBicho(DATABichos.get("BichoGisemonEvolucion"));
		bichoDAO.guardarBicho(DATABichos.get("BichoBottimon"));
		bichoDAO.guardarBicho(DATABichos.get("FrutiMon"));
		bichoDAO.guardarBicho(DATABichos.get("BichoBottimon2"));
		
		entrenadorDAO.guardarEntrenador(DATAEntrenadores.get("Explorador1"));
		entrenadorDAO.guardarEntrenador(DATAEntrenadores.get("Explorador2"));
		entrenadorDAO.guardarEntrenador(DATAEntrenadores.get("Explorador3"));
		entrenadorDAO.guardarEntrenador(DATAEntrenadores.get("Explorador4"));
		entrenadorDAO.guardarEntrenador(DATAEntrenadores.get("Explorador5"));
		entrenadorDAO.guardarEntrenador(DATAEntrenadores.get("Explorador6"));
		
	}
	
}