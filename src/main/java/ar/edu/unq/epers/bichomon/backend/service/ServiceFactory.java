package ar.edu.unq.epers.bichomon.backend.service;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.jdbc.DataEspecieManagerJDBC;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;

/**
 * Esta clase es un singleton, el cual sera utilizado por equipo de frontend
 * para hacerse con implementaciones a los servicios.
 * 
 * @author Steve Frontend
 * 
 */
public class ServiceFactory {
	
	/**
	 * @return un objeto que implementa {@link EspecieService}
	 */
	public EspecieService getEspecieService() {
		return new EspecieManager(new HibernateEspecieDAO());
	}
	
	/**
	 * @return un objeto que implementa {@link DataService}
	 */
	public DataService getDataService() {
		return new DataEspecieManagerJDBC();
	}

}
