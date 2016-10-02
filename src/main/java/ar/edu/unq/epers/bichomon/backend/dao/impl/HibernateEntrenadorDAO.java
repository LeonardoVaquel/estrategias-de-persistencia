package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link EntrenadorDAO} que persiste
 * en una base de datos relacional utilizando Hibernate
 * 
 */
public class HibernateEntrenadorDAO implements EntrenadorDAO {

	@Override
	public Entrenador getEntrenador(String nombreEntrenador) {
		Session session = Runner.getCurrentSession();
		return session.get(Entrenador.class, nombreEntrenador);
	}

	@Override
	public void guardarEntrenador(Entrenador entrenador) {
		Session session = Runner.getCurrentSession();
		session.save(entrenador);
		
	}

}
