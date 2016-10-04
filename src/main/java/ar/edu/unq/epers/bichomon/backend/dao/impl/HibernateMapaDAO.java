package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.Campeon;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link MapaService} que persiste y recupera inforamción
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateMapaDAO implements MapaService {

	/**
	 * Dado un nombre de {@link Entrenador} y un nombre de {@link Ubicacion} se mueve al entrenador
	 * a la ubicación especificada.
	 * Se espera que la {@link Ubicacion} de un entrenador se modifique por la del nombre pasado
	 * como parámetro
	 */
	@Override
	public void mover(String nombreEntrenador, String nombreUbicacion) {
		Session session = Runner.getCurrentSession();
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		Ubicacion ubicacion = session.get(Ubicacion.class, nombreUbicacion);
		entrenador.mover(ubicacion);
	}

	/**
	 * Dado un nombre de {@link Ubicacion} se obtiene la cantidad de entrenadores
	 * que se encuentran actualmente en la ubicación especificada
	 */
	@Override
	public int cantidadEntrenadores(String nombreUbicacion) {
		Session session = Runner.getCurrentSession();
		
		String hql = "from Entrenador e "
				+ "where e.ubicacion.nombre = :unaUbicacion";
		
		Query<Entrenador> query = session.createQuery(hql, Entrenador.class);
		query.setParameter("unaUbicacion", nombreUbicacion);

		return query.getResultList().size(); 
	}

	/**
	 * Dada una instancia de {@link Dojo} se obtiene una instancia de {@link Bicho}
	 * que representa al actual campeón de dicha {@link Ubicación}.
	 */
	@Override
	public Bicho campeon(String dojo) {
		Session session = Runner.getCurrentSession();
		Dojo ubicacion = session.get(Dojo.class, dojo);
		return ubicacion.getCampeon();
	}

	/**
	 * TODO
	 */
	@Override
	public Bicho campeonHistorico(String dojo) {
		Session session = Runner.getCurrentSession();

		return null;
	}

}
