package ar.edu.unq.epers.bichomon.backend.service;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;

public class BichoManager implements BichoService {

	private BichoDAO bichoDAO;
	
	public BichoManager(BichoDAO bichoDAO) {
		
		this.bichoDAO = bichoDAO;
	}
	
	public void guardarBicho(Bicho bicho) {
		bichoDAO.guardarBicho(bicho);
	}

	@Override
	public Bicho buscar(String entrenador) {
		return bichoDAO.buscar(entrenador);
	}

	@Override
	public void abandonar(String entrenador, int bicho) {
		bichoDAO.abandonar(entrenador, bicho);
	}

	@Override
	public ResultadoCombate duelo(String entrenador, int bicho) {
		return bichoDAO.duelo(entrenador, bicho);
	}

	@Override
	public boolean puedeEvolucionar(String entrenador, int bicho) {
		return bichoDAO.puedeEvolucionar(entrenador, bicho);
	}

	@Override
	public Bicho evolucionar(String entrenador, int bicho) {
		return bichoDAO.evolucionar(entrenador, bicho);
	}
}
