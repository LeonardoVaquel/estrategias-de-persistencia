package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * BichoSessionService es una clase que modela el comportamiento de un {@link Bicho} al conectarse a 
 * una sesión en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir información en una base de datos y 
 * delegar comportamiento sobre las instancias {@link Bicho} que sean necesarias.
 * 
 * @author santiago
 */
public class DataSessionService implements DataService {

	
	private DataService dataService;
	
	public DataSessionService(DataService dataService) {
		this.dataService = dataService;
	}
	
	@Override
	public void eliminarDatos() {
		Runner.runInSession(() -> {
			dataService.eliminarDatos();
			return null;
		});
		
	}

	@Override
	public void crearSetDatosIniciales() {
		Runner.runInSession(() -> {
			dataService.crearSetDatosIniciales();
			return null;
		});
		
	}

	@Override
	public void eliminarTablas() {
		Runner.runInSession(() -> {
			dataService.eliminarTablas();
			return null;
		});
		
	}
}
