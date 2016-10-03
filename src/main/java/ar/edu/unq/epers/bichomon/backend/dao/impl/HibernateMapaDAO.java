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

	@Override
	public void mover(String nombreEntrenador, String nombreUbicacion) {
		Session session = Runner.getCurrentSession();
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		Ubicacion ubicacion = session.get(Ubicacion.class, nombreUbicacion);
		entrenador.mover(ubicacion);
	}

	@Override
	public int cantidadEntrenadores(String nombreUbicacion) {
		Session session = Runner.getCurrentSession();
		
		String hql = "from Entrenador e "
				+ "where e.ubicacion.nombre = :unaUbicacion";
		
		Query<Entrenador> query = session.createQuery(hql, Entrenador.class);
		query.setParameter("unaUbicacion", nombreUbicacion);

		return query.getResultList().size(); 
	}

	@Override
	public Bicho campeon(String dojo) {
		Session session = Runner.getCurrentSession();
		Dojo ubicacion = session.get(Dojo.class, dojo);
		return ubicacion.getCampeon();
	}

	@Override
	public Bicho campeonHistorico(String dojo) {
		Session session = Runner.getCurrentSession();

		return null;
	}

}
