package ar.edu.unq.epers.bichomon.backend.service;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

public class BichoManager {

	private BichoDAO bichoDAO;
	
	public BichoManager(BichoDAO bichoDAO) {
		
		this.bichoDAO = bichoDAO;
	}
	
	public void guardarBicho(Bicho bicho) {
		bichoDAO.guardarBicho(bicho);
	}
}
