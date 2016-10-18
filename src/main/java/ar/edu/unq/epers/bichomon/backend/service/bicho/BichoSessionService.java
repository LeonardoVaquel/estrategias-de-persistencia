package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * BichoSessionService es una clase que modela el comportamiento de un {@link Bicho} al conectarse a 
 * una sesión en una base de datos.
 * Los métodos tendrán la responsabilidad de solicitar o persistir información en una base de datos y 
 * delegar comportamiento sobre las instancias {@link Bicho} que sean necesarias.
 * 
 * @author santiago
 */
public class BichoSessionService {

	
	private BichoDAO bichoDAO;
	
	public BichoSessionService(BichoDAO bichoDAO) {
		this.bichoDAO = bichoDAO;
	}
	
	public Bicho getBicho(int idBicho) {
		return Runner.runInSession(() -> {
			return bichoDAO.getBicho(idBicho);
		});
	}
	
	public Bicho buscar(String nombreEntrenador) {
		return Runner.runInSession(() -> {
			return bichoDAO.buscar(nombreEntrenador);
		});
	}
	
	public void guardarBicho(Bicho bicho) {
		Runner.runInSession(() -> {
			bichoDAO.guardarBicho(bicho);
			return null;
		});
	}
	
	public boolean puedeEvolucionar(String nombreEntrenador, int idBicho) {
		return Runner.runInSession(() -> {
			
			return bichoDAO.getBicho(idBicho).puedeEvolucionar();
		});
	}
	
	public Bicho evolucionar(String entrenador, int bicho) {
		return Runner.runInSession(() -> {
			return bichoDAO.evolucionar(entrenador, bicho);
		});
	}
	
	public void abandonar(String entrenador, int bicho) {
		Runner.runInSession(() -> {
			bichoDAO.abandonar(entrenador, bicho);
			return null;
		});
	}
	
	public ResultadoCombate duelo(String entrenador, int bicho) {
		return Runner.runInSession(()-> {
			return bichoDAO.duelo(entrenador, bicho);
		});
	}
}
