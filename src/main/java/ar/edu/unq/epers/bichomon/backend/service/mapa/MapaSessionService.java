package ar.edu.unq.epers.bichomon.backend.service.mapa;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * MapaSessionService 
 * 
 * @author santiago
 */
public class MapaSessionService implements MapaService {

	
	private GenericService service;
	private EntrenadorDAO entrenadorDAO;
	
	/**
	 * Crea una instancia de MapaSessionService que utilizará un dao,
	 * a quien delegará los pedidos necesarios sobre una base de datos.
	 * @param mapaDAO
	 */
	public MapaSessionService(EntrenadorDAO entrenadorDAO, GenericService service) {
		this.service 	   = service;
		this.entrenadorDAO = entrenadorDAO;
	}
	
	/**
	 * Dado un nombre de {@link Entrenador} y un nombre de {@link Ubicacion} se mueve al entrenador
	 * a la ubicación especificada.
	 * Se espera que la {@link Ubicacion} de un entrenador se modifique por la del nombre pasado
	 * como parámetro
	 */
	@Override
	public void mover(String nombreEntrenador, String nombreUbicacion) {
		Runner.runInSession(() -> {
		
		Entrenador entrenador = this.entrenadorDAO.getEntrenador(nombreEntrenador);
		Ubicacion ubicacion   = this.service.recuperarEntidad(Ubicacion.class, nombreUbicacion);
		entrenador.mover(ubicacion);
		return null;
		});		
	}
	
	
}
