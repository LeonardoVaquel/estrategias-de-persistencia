package ar.edu.unq.epers.bichomon.backend.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link LeaderboardService} que persiste y recupera información
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateLeaderboardDAO implements LeaderboardService {

	/**
	 * Se retorna una lista de {@link Entrenador} que contiene a todos aquellos entrenadores
	 * que poseen un {@link Bicho} que es actualmente campeón en un {@link Dojo}
	 * El orden de la lista reflejará primero aquellos entrenadores que hayan salido
	 * victoriosos desde hace mas tiempo. 
	 */
	@Override
	public List<Entrenador> campeones() {
		Session session = Runner.getCurrentSession();
		
		String hql = "from Campeon c "
				+ "where c.fechaDerrocado = null order by c.fechaCoronado asc";
		
		Query<Campeon> query = session.createQuery(hql, Campeon.class);
		return query.getResultList().stream().map(c -> c.getEntrenador()).collect(Collectors.toList());		
	}

	/**
	 * TODO
	 */
	@Override
	public Especie especieLider() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * TODO
	 */
	@Override
	public List<Entrenador> lideres() {
		// TODO Auto-generated method stub
		return null;
	}


}
