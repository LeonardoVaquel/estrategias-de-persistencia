package ar.edu.unq.epers.bichomon.backend.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.evolucion.CriterioEvolucion;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link LeaderboardService} que persiste y recupera información
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateLeaderboardDAO implements LeaderboardService {

	@Override
	public List<Entrenador> campeones() {
		Session session = Runner.getCurrentSession();
		
			
		String hql = "from Campeon c "
				+ "order by c.fechaCoronado asc";
		
		Query<Campeon> query = session.createQuery(hql, Campeon.class);

		return query.getResultList().stream().filter(c -> c.getDerrocado() == null).map(c -> c.getEntrenador()).collect(Collectors.toList());		
	}

	@Override
	public Especie especieLider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entrenador> lideres() {
		// TODO Auto-generated method stub
		return null;
	}


}
