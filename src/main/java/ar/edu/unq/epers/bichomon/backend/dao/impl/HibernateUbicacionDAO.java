package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link UbicacionDAO} que persiste y recupera información
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateUbicacionDAO implements UbicacionDAO {

	
	// POSIBLEMENTE SE BORRE ESTA CLASE
	
	/**
	 * Dada una instancia de {@link Guarderia} se guarda en un ambiente persistente
	 */
	@Override
	public void guardarGuarderia(Guarderia guarderia) {
		Session session = Runner.getCurrentSession();
		session.save(guarderia);
	}

	
}
