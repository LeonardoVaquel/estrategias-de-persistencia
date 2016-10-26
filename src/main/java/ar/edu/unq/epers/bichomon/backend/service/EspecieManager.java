package ar.edu.unq.epers.bichomon.backend.service;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;

/**
 * Clase que implementa la interfaz provista por el equipo de Frontend
 * para obtener los servicios de especie requeridos sobre un DAO.
 * 
 * @author santiago
 */
public class EspecieManager implements EspecieService {

	private EspecieDAO especieDAO;
	
	public EspecieManager(EspecieDAO especieDAO) {
		
		this.especieDAO = especieDAO;
	}

	@Override
	public void crearEspecie(Especie especie) {
		especieDAO.crearEspecie(especie);
	}

	@Override
	public Especie getEspecie(String nombreEspecie) {
		return especieDAO.getEspecie(nombreEspecie);
	}

	@Override
	public List<Especie> getAllEspecies() {
		return especieDAO.getAllEspecies();
	}

	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho) {
		return especieDAO.crearBicho(nombreEspecie, nombreBicho);
	}
	
	public void removeAllEspecies() {
		especieDAO.removeAllEspecies();
	}
	
	public List<Especie> populares() {
		return especieDAO.populares();
	}


}
