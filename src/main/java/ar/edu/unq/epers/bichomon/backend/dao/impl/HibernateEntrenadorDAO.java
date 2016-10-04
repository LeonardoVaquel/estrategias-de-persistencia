package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link EntrenadorDAO} que persiste
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateEntrenadorDAO implements EntrenadorDAO {

	/**
	 * Dado un nombre se obtiene una instancia de {@link Entrenador}
	 */
	@Override
	public Entrenador getEntrenador(String nombreEntrenador) {
		Session session = Runner.getCurrentSession();
		return session.get(Entrenador.class, nombreEntrenador);
	}

	/**
	 * Dada una instancia de {@link Entrenador} se guarda en un ambiente persistente.
	 */
	@Override
	public void guardarEntrenador(Entrenador entrenador) {
		Session session = Runner.getCurrentSession();
		session.save(entrenador);
		
	}

}
