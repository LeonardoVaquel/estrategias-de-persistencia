package ar.edu.unq.epers.bichomon.backend.service.especie;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * EspecieSessionService es una clase que modela el comportamiento
 * de una Especie al conectarse a una sesion en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir
 * información en una base de datos y delegar comportamiento 
 * sobre las instancias Especie que sean necesarias.
 * 
 * @author santiago
 */
public class EspecieSessionService {
	
	private EspecieDAO especieDAO;
	
	/**
	 * Crea una instancia de EspecieSessionService que utilizará un dao,
	 * a quien delegará los pedidos necesarios sobre una base de datos.
	 * @param especieDAO
	 */
	public EspecieSessionService(EspecieDAO especieDAO) {
		this.especieDAO = especieDAO;
	}

	/**
	 * Se persisten los atributos de una instancia de Especie durante una
	 * sesión en una base de datos.
	 * @param especie
	 */
	public void crearEspecie(Especie especie) {
		Runner.runInSession(() -> {
			especieDAO.crearEspecie(especie);
			return null;
		});
	}
	
	/**
	 * Dado un nombre de una especie, se obtiene una instancia de Especie del nombre dado
	 * durante una sesión en una base de datos.
	 * @param nombreEspecie
	 */
	public void getEspecie(String nombreEspecie) {
		Runner.runInSession(() -> {
			especieDAO.getEspecie(nombreEspecie);
			
			return null;
		});
	}
	
	/**
	 * Se obtienen todas las especies existentes durante una sesión 
	 * en una base bases de datos
	 */
	public void getAllEspecies() {
		Runner.runInSession(() -> {
			especieDAO.getAllEspecies();
			return null;
		});
	}

}

