package ar.edu.unq.epers.bichomon.backend.dao.impl;


import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;

/**
 * Una implementacion de {@link ExperienciaDAO} que persiste
 * en una base de datos relacional utilizando JDBC
 * 
 */
public class HibernateExperienciaDAO implements ExperienciaDAO {

	@Override
	public HashMap<Integer, Double> getAllLevels() {
//		Session session = Runner.getCurrentSession();
//		
//		Query<Level> = session.createQuery("From Level ", Level.class);
//		
//		return query..getResultList();
		return null; //session.get(List.class, "");
	}

}
