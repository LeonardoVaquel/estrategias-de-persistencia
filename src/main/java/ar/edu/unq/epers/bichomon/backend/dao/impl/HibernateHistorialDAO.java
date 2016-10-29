package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.HistorialDAO;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link HistorialDAO} que persiste y recupera información
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateHistorialDAO implements HistorialDAO {

	/**
	 * Dada una instancia de {@link Campeon} se guarda en un ambiente persistente
	 */
	@Override
	public void guardarCampeon(Campeon campeon) {
		Session session = Runner.getCurrentSession();
		session.save(campeon);
	}

	

}
