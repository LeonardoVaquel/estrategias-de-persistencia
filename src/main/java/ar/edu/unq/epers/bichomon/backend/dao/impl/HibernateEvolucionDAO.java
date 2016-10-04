package ar.edu.unq.epers.bichomon.backend.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unq.epers.bichomon.backend.dao.EvolucionDAO;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link EvolucionDAO} que persiste
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateEvolucionDAO implements EvolucionDAO {

	
	/**
	 * TODO
	 */
	@Override
	public List<CriterioEvolucion> getCriteriosDeEvolucion(String nombreEspecie) {
		
		Session session = Runner.getCurrentSession();
		
		String hql = "from CriterioEvolucion c "
				+ "where c.especie == :unaEspecie";
		
		Query<CriterioEvolucion> query = session.createQuery(hql,  CriterioEvolucion.class);
		query.setParameter("unaEspecie", nombreEspecie);

		return query.getResultList();
	}

}
