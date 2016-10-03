package ar.edu.unq.epers.bichomon.backend.dao.impl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unq.epers.bichomon.backend.dao.ExperienciaDAO;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Level;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.TablaDeExperiencia;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link ExperienciaDAO} que persiste
 * en una base de datos relacional utilizando JDBC
 * 
 */
public class HibernateExperienciaDAO implements ExperienciaDAO {

	@Override
	public List<Level> getAllLevels() {
		Session session = Runner.getCurrentSession();
		
		String hql = "from Level l";
		
		Query<Level> query = session.createQuery(hql, Level.class);
		
		return query.getResultList();
	}
	
	@Override
	public void guardarLevel(Level level) {
		Session session = Runner.getCurrentSession();
		session.save(level);
	}

	@Override
	public void guardarTablaDeExperiencia(TablaDeExperiencia expTable) {
		Session session = Runner.getCurrentSession();
		session.save(expTable);
	}
	
	@Override
	public int getExperiencieByEvent(String tipoDeEvento) {
		Session session = Runner.getCurrentSession();
		return session.get(TablaDeExperiencia.class, tipoDeEvento).getValor();
	}

	@Override
	public Experiencia getExperienciaConfig(String numeroVersion) {
		Session session = Runner.getCurrentSession();
		return session.get(Experiencia.class, numeroVersion);
	}
	
	@Override
	public void guardarExperienciaConfig(Experiencia expConfig) {
		Session session = Runner.getCurrentSession();
		session.save(expConfig);
	}

}
