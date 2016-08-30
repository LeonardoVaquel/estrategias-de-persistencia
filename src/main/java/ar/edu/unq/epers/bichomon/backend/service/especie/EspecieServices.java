package ar.edu.unq.epers.bichomon.backend.service.especie;

import java.util.List;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

public class EspecieServices implements EspecieService {

	private EspecieService especieDAO;
	
	public EspecieServices() {
		
		this.especieDAO = new EspecieDAO();
	}

	@Override
	public void crearEspecie(Especie especie) {
		this.especieDAO.crearEspecie(especie);
	}

	@Override
	public Especie getEspecie(String nombreEspecie) {
		return this.especieDAO.getEspecie(nombreEspecie);
	}

	@Override
	public List<Especie> getAllEspecies() {
		return this.especieDAO.getAllEspecies();
	}

	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho) {
		return this.especieDAO.crearBicho(nombreEspecie, nombreBicho);
	}
}
