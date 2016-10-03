package ar.edu.unq.epers.bichomon.backend.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.HistorialDAO;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateHistorialDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEdad;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEnergia;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionNivel;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionVictorias;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Level;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.TablaDeExperiencia;
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
public class DataManager implements DataService {

	private EspecieDAO especieDao 		  = new HibernateEspecieDAO();
	private BichoDAO bichoDAO 			  = new HibernateBichoDAO();
	private EntrenadorDAO entrenadorDAO   = new HibernateEntrenadorDAO();
	private ExperienciaDAO experienciaDAO = new HibernateExperienciaDAO();
	private HistorialDAO historialDAO     = new HibernateHistorialDAO();
	private UbicacionDAO ubicacionDAO     = new HibernateUbicacionDAO();
	
	// TODO
	private ServiceFactory services = new ServiceFactory();
	
	public static Map<String, Especie> DATAEspecies 			  = new HashMap<>();
	public static Map<String, Bicho> DATABichos 				  = new HashMap<>();
	public static Map<String, Entrenador> DATAEntrenadores 		  = new HashMap<>();
	public static Map<String, TablaDeExperiencia> DATAExpEvents   = new HashMap<>();
	public static Map<String, Experiencia> DATAExpConfigs         = new HashMap<>();
	public static Map<String, Campeon> DATACampeones              = new HashMap<>();
	public static Map<String, Tupla> DATAEspeciesTupla            = new HashMap<>();
	public static Map<String, Guarderia> DATAUbicacionesTupla     = new HashMap<>();
	
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
		
		//Creación de Guarderia con bichos
		Bicho bichito1 = new Bicho(frutiMon);
		Bicho bichito2 = new Bicho(frutiMon);
		Bicho bichito3 = new Bicho(frutiMon);
		List<Bicho> listaDeBichosParaGuarderia = new ArrayList<>();
		listaDeBichosParaGuarderia.add(bichito1);
		listaDeBichosParaGuarderia.add(bichito2);
		listaDeBichosParaGuarderia.add(bichito3);
		
		Guarderia guarderiaLas24hrs = new Guarderia("Guarderia las 24 horas!");
		guarderiaLas24hrs.setBichos(listaDeBichosParaGuarderia);
		
		//Creación de Entrenador
		Entrenador santiagoTrainer = new Entrenador("Santiago");
		
		Entrenador jacksonTrainer = new Entrenador("Jackson");
		jacksonTrainer.setNivel(2);

		jacksonTrainer.setUbicacion(guarderiaLas24hrs);
		
		Entrenador majinTrainer = new Entrenador("Majin");
		
		Entrenador vegetalTrainer = new Entrenador("Vegetal");
		
		Entrenador explorer01 = new Entrenador("Explorador1");
		Entrenador explorer02 = new Entrenador("Explorador2");
		explorer02.setNivel(4);
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
		
		//Creacion de lista de Especie
		
		Tupla tupla1 = new Tupla(leoMon, 0.3f);
		Tupla tupla2 = new Tupla(giseMon, 0.6f);
		Tupla tupla3 = new Tupla(leoMonEvolucion, 0.9f);
		Tupla tupla4 = new Tupla(giseMonEvolucion, 1.6f);
		Tupla tupla5 = new Tupla(frutiMon, 2.6f);
		
		List<Tupla> listDeEspeciesNeverland = new ArrayList<>();
		listDeEspeciesNeverland.add(tupla1);
		listDeEspeciesNeverland.add(tupla2);
		listDeEspeciesNeverland.add(tupla3);
		listDeEspeciesNeverland.add(tupla4);
		listDeEspeciesNeverland.add(tupla5);
		
		//Creación de Ubicacion
		
		Dojo torreKarinDojo = new Dojo("Torre Karin");
		torreKarinDojo.setCampeon(bichoFrutiMon);
		
		Dojo varelaDojo = new Dojo("Varela-Dojo");
		varelaDojo.setCampeon(bichoLeomon);
		
		Dojo bernalDojo = new Dojo("Bernal-Dojo");
		bernalDojo.setCampeon(bichoGisemon);
		
		Dojo quilmesDojo = new Dojo("Quilmes-Dojo");
		quilmesDojo.setCampeon(bichoBottimon);
		
		Pueblo neverLand = new Pueblo("Neverland");
		neverLand.setListaDeEspecies(listDeEspeciesNeverland);
		
		santiagoTrainer.setUbicacion(torreKarinDojo);
		
		explorer01.setUbicacion(neverLand);
		explorer02.setUbicacion(neverLand);
		explorer03.setUbicacion(neverLand);
		explorer04.setUbicacion(torreKarinDojo);
		explorer05.setUbicacion(torreKarinDojo);
		explorer06.setUbicacion(torreKarinDojo);
		
		vegetalTrainer.setUbicacion(torreKarinDojo);
		
		
		//Experiencia Configuration
		TablaDeExperiencia combateExpTable = new TablaDeExperiencia("Combatir", 10);
		TablaDeExperiencia capturaExpTable = new TablaDeExperiencia("Capturar", 10);
		TablaDeExperiencia evolucionExpTable = new TablaDeExperiencia("Evolucionar", 5);
		
		List<Level> levelList = new ArrayList<>();
		levelList.add(new Level(1, 0.1));
		levelList.add(new Level(2, 0.3));
		levelList.add(new Level(3, 0.6));
		levelList.add(new Level(4, 1.0));
		levelList.add(new Level(5, 1.0));
		levelList.add(new Level(6, 1.0));
		levelList.add(new Level(7, 1.0));
		levelList.add(new Level(8, 1.0));
		levelList.add(new Level(9, 1.0));
		levelList.add(new Level(10,1.0));
		
		Experiencia expConfig = new Experiencia();
		expConfig.setVersion("v1.0.0");
		expConfig.setLevelList(levelList);
		expConfig.setBaseExp(1000d);
		
		// Campeones
		
		
		Campeon campeon01 = new Campeon();
		campeon01.setCampeon(bichoLeomon);
		campeon01.setCoronado(LocalDateTime.of(2016, Month.JANUARY, 1, 1, 1)); 
		campeon01.setDerrocado(null);
		campeon01.setDojo(varelaDojo);
		campeon01.setEntrenador(santiagoTrainer);
		
		Campeon campeon02 = new Campeon();
		campeon02.setCampeon(bichoGisemon);
		campeon02.setCoronado(LocalDateTime.of(2016, Month.JANUARY, 5, 1, 1)); 
		campeon02.setDerrocado(null);
		campeon02.setDojo(bernalDojo);
		campeon02.setEntrenador(jacksonTrainer);
		
		Campeon campeon03 = new Campeon();
		campeon03.setCampeon(bichoBottimon);
		campeon03.setCoronado(LocalDateTime.of(2016, Month.JANUARY, 10, 1, 1)); 
		campeon03.setDerrocado(null);
		campeon03.setDojo(quilmesDojo);
		campeon03.setEntrenador(jacksonTrainer);
		
		Campeon campeon04 = new Campeon();
		campeon04.setCampeon(bichoBottimon);
		campeon04.setCoronado(LocalDateTime.of(2015, Month.JANUARY, 15, 1, 1)); 
		campeon04.setDerrocado(LocalDateTime.of(2015, Month.JANUARY, 16, 1, 1));
		campeon04.setDojo(quilmesDojo);
		campeon04.setEntrenador(jacksonTrainer);


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
		
		DATAExpEvents.put("CombateExpTable", combateExpTable);
		DATAExpEvents.put("CapturaExpTable", capturaExpTable);
		DATAExpEvents.put("EvolucionExpTable", evolucionExpTable);
		
		DATAExpConfigs.put("v1.0.0", expConfig);
		
		DATACampeones.put("Varela-Dojo", campeon01);
		DATACampeones.put("Bernal-Dojo", campeon02);
		DATACampeones.put("Quilmes-Dojo", campeon03);
		DATACampeones.put("Quilmes-Dojo2", campeon04);
		
		DATAEspeciesTupla.put("Tupla1", tupla1);
		DATAEspeciesTupla.put("Tupla2", tupla2);
		DATAEspeciesTupla.put("Tupla3", tupla3);
		DATAEspeciesTupla.put("Tupla4", tupla4);
		DATAEspeciesTupla.put("Tupla5", tupla5);
		
		DATAUbicacionesTupla.put("GuarderiaLas24Hrs", guarderiaLas24hrs);
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
		
		experienciaDAO.guardarTablaDeExperiencia(DATAExpEvents.get("CombateExpTable"));
		experienciaDAO.guardarTablaDeExperiencia(DATAExpEvents.get("CapturaExpTable"));
		experienciaDAO.guardarTablaDeExperiencia(DATAExpEvents.get("EvolucionExpTable"));
		experienciaDAO.guardarExperienciaConfig(DATAExpConfigs.get("v1.0.0"));
		
		historialDAO.guardarCampeon(DATACampeones.get("Quilmes-Dojo2"));
		historialDAO.guardarCampeon(DATACampeones.get("Quilmes-Dojo"));
		historialDAO.guardarCampeon(DATACampeones.get("Bernal-Dojo"));
		historialDAO.guardarCampeon(DATACampeones.get("Varela-Dojo"));
		
		especieDao.guardarTupla(DATAEspeciesTupla.get("Tupla1"));
		especieDao.guardarTupla(DATAEspeciesTupla.get("Tupla2"));
		especieDao.guardarTupla(DATAEspeciesTupla.get("Tupla3"));
		especieDao.guardarTupla(DATAEspeciesTupla.get("Tupla4"));
		especieDao.guardarTupla(DATAEspeciesTupla.get("Tupla5"));
		
		ubicacionDAO.guardarGuarderia(DATAUbicacionesTupla.get("GuarderiaLas24Hrs"));
	}
	
}