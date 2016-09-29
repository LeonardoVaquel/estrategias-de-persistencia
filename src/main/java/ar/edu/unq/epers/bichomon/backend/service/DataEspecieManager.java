package ar.edu.unq.epers.bichomon.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEdad;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionEnergia;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionNivel;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucionVictorias;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;

/**
 * Esta clase mantiene un repositorio con dummyData Especie 
 * para hacer pruebas sobre una Base de Datos.
 * @author santiago
 *
 */
public class DataEspecieManager implements DataService {

	public EspecieDAO dao = new HibernateEspecieDAO();
	public BichoDAO bichoDAO = new HibernateBichoDAO();
	
	public static Map<String, Especie> DATAEspecies = new HashMap<>();
	public static Map<String, Bicho> DATABichos = new HashMap<>();
	
	static {
		Especie leoMon = new Especie("Leomon", TipoBicho.CHOCOLATE);
		Especie leoMonEvolucion = new Especie("EvolucionLeomon", TipoBicho.CHOCOLATE);
		
		// Leomon
		leoMon.setRaiz(leoMon);
		leoMon.setEvolucion(leoMonEvolucion);
			List<CriterioEvolucion> criteriosLeomon = new ArrayList<>();
			criteriosLeomon.add(new CriterioEvolucionEnergia(200));
		leoMon.setCriteriosDeEvolucion(criteriosLeomon);
		leoMon.setAltura(1);
		leoMon.setPeso(1);
		leoMon.setEnergiaIncial(100);
		leoMon.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/leomon.jpg");
		leoMon.setCantidadBichos(0);
		DATAEspecies.put(leoMon.getNombre(), leoMon);

		// LeomonEvolucion
		leoMonEvolucion.setRaiz(leoMon);
		leoMonEvolucion.setEvolucion(null);
			List<CriterioEvolucion> criteriosEvolucionLeomon = new ArrayList<>();
			criteriosEvolucionLeomon.add(new CriterioEvolucionNivel(5));
		leoMonEvolucion.setCriteriosDeEvolucion(criteriosEvolucionLeomon);
		leoMonEvolucion.setAltura(10);
		leoMonEvolucion.setPeso(10);
		leoMonEvolucion.setEnergiaIncial(100);
		leoMonEvolucion.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/leomonEvolucion.jpg");
		leoMonEvolucion.setCantidadBichos(0);
		DATAEspecies.put(leoMonEvolucion.getNombre(), leoMonEvolucion);
		
		//Gisemon
		Especie giseMon = new Especie("Gisemon", TipoBicho.FUEGO);
		Especie giseMonEvolucion = new Especie("GisemonEvolucion", TipoBicho.FUEGO);
		
		giseMon.setRaiz(giseMon);
		giseMon.setEvolucion(giseMonEvolucion);
			List<CriterioEvolucion> criteriosGisemon = new ArrayList<>();
			criteriosGisemon.add(new CriterioEvolucionVictorias(5));
		giseMon.setCriteriosDeEvolucion(criteriosGisemon);		
		giseMon.setAltura(150);
		giseMon.setPeso(80);
		giseMon.setEnergiaIncial(100);
		giseMon.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/gisemon.jpg");
		giseMon.setCantidadBichos(0);
		DATAEspecies.put(giseMon.getNombre(), giseMon);
		
		//GisemonEvolucion		
		giseMonEvolucion.setRaiz(giseMon );
		giseMonEvolucion.setEvolucion(null);
			List<CriterioEvolucion> criteriosEvolucionGisemon = new ArrayList<>();
			criteriosEvolucionGisemon.add(new CriterioEvolucionNivel(5));
			criteriosEvolucionGisemon.add(new CriterioEvolucionEnergia(200));
		giseMonEvolucion.setCriteriosDeEvolucion(criteriosEvolucionGisemon);		
		giseMonEvolucion.setAltura(150);
		giseMonEvolucion.setPeso(80);
		giseMonEvolucion.setEnergiaIncial(100);
		giseMonEvolucion.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/gisemonEvolucion.jpg");
		giseMonEvolucion.setCantidadBichos(0);
		DATAEspecies.put(giseMonEvolucion.getNombre(), giseMonEvolucion);		
		
		//Bottimon		
		Especie bottiMon = new Especie("Bottimon", TipoBicho.AIRE);
		bottiMon.setRaiz(bottiMon);
		bottiMon.setEvolucion(null);
			List<CriterioEvolucion> criteriosBottimon = new ArrayList<>();
			criteriosBottimon.add(new CriterioEvolucionEnergia(200));
			criteriosBottimon.add(new CriterioEvolucionEdad(10));
		bottiMon.setCriteriosDeEvolucion(criteriosBottimon);		
		bottiMon.setAltura(180);
		bottiMon.setPeso(70);
		bottiMon.setEnergiaIncial(100);
		bottiMon.setUrlFoto("http://bichomongo/var/www/html/static/bichomon/bottimon.jpg");
		bottiMon.setCantidadBichos(0);
		DATAEspecies.put(bottiMon.getNombre(), bottiMon);
		
		Bicho bichoLeomon = new Bicho(leoMon);
		bichoLeomon.setEnergia(250);
		DATABichos.put("bichoLeomon", bichoLeomon);
		
		Bicho bichoGisemon = new Bicho(giseMon);
		bichoGisemon.setVictorias(10);
		DATABichos.put("bichoGisemon", bichoGisemon);
	}
	
	@Override
	public void eliminarDatos() {
		dao.removeAllEspecies();
	}

	@Override
	public void crearSetDatosIniciales() {

		dao.crearEspecie(DATAEspecies.get("Leomon"));
		dao.crearEspecie(DATAEspecies.get("LeomonEvolucion"));
		dao.crearEspecie(DATAEspecies.get("Gisemon"));
		dao.crearEspecie(DATAEspecies.get("GisemonEvolucion"));
		dao.crearEspecie(DATAEspecies.get("Bottimon"));
		
		bichoDAO.guardarBicho(DATABichos.get("bichoLeomon"));
		bichoDAO.guardarBicho(DATABichos.get("bichoGisemon"));
	}
	
}