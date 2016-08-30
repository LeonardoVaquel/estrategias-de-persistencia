package ar.edu.unq.epers.bichomon.backend.service;

import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceEspecie;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieServices;
import ar.edu.unq.epers.bichomon.frontend.mock.EspecieServiceMock;

/**
 * Esta clase es un singleton, el cual sera utilizado por equipo de frontend
 * para hacerse con implementaciones a los servicios.
 * 
 * @author Steve Frontend
 * 
 * TODO: Gente de backend, una vez que tengan las implementaciones de sus
 * servicios propiamente realizadas apunten a ellas en los metodos provistos
 * debajo. Gracias!
 */
public class ServiceFactory {
	
	/**
	 * @return un objeto que implementa {@link EspecieService}
	 */
	public EspecieService getEspecieService() {
		return new EspecieServices();
		//throw new RuntimeException("Todavia no se ha implementado este metodo");
		
		// ¿Cómo devolver otra clase de tipo EspecieService cuando EspecieDAO ya implementa la interfaz
		// EspecieService y tiene los servicios implementados? Habrá que replantearse la responsabilidad
		// que tiene que tener un DAO?
		// Santi B.
	}
	
	/**
	 * @return un objeto que implementa {@link DataService}
	 */
	public DataService getDataService() {
		return new DataServiceEspecie();
		//throw new RuntimeException("Todavia no se ha implementado este metodo");
	}

}
