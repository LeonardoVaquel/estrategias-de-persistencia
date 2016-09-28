package ar.edu.unq.epers.bichomon.backend.dao.impl;


import java.util.List;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link EspecieDAO} que persiste
 * en una base de datos relacional utilizando Hibernate
 * 
 */
public class HibernateEspecieDAO implements EspecieDAO, EspecieService {

	@Override
	public void crearEspecie(Especie especie) {
		Session session = Runner.getCurrentSession();
		session.save(especie);
	}

	@Override
	public Especie getEspecie(String nombreEspecie) {
		Session session = Runner.getCurrentSession();
		return session.get(Especie.class, nombreEspecie);
	}
	
	@Override
	public List<Especie> getAllEspecies() {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO Bicho no tiene mas nombre
	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho) {
		// TODO Auto-generated method stub
		return null;
	}

}
