package ar.edu.unq.epers.bichomon.backend.dao.impl;


import java.util.HashMap;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.dao.jdbc.JDBCEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link JDBCEspecieDAO} que persiste
 * en una base de datos relacional utilizando JDBC
 * 
 */
public class HibernateExperienciaDAO implements ExperienciaDAO {

	@Override
	public HashMap<Integer, Double> getAllLevels() {
		Session session = Runner.getCurrentSession();
		return null; //session.get(List.class, "");
	}

}
