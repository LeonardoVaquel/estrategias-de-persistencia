package ar.edu.unq.epers.bichomon.backend.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unq.epers.bichomon.backend.dao.MapaDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
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
public class HibernateMapaDAO implements MapaDAO {

	/**
	 * Dado un nombre de {@link Ubicacion} se obtiene la cantidad de entrenadores
	 * que se encuentran actualmente en la ubicación especificada
	 */
	@Override
	public int cantidadEntrenadores(String nombreUbicacion) {
		Session session = Runner.getCurrentSession();
		
		String hql = "select count(elements(u.entrenadores)) from Ubicacion u "
				+ "where u.nombre = :unaUbicacion";
		
		Query<Long> query = session.createQuery(hql, Long.class);
		query.setParameter("unaUbicacion", nombreUbicacion);
		return query.getSingleResult().intValue();
		
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
	 * A partir de una sesion en una base de datos en Hibernate se obtiene una lista de ubicaciones.
	 * @param nombresDeUbicacion - recibe una lista de nombres de {@link Ubicacion}
	 * @return una lista de ubicaciones cuyos nombres se encuentran en la lista pasada como parámetro
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<Ubicacion> getUbicacionesDeNombre(List<String> nombresDeUbicacion) {
		
		Session session = Runner.getCurrentSession();
		
		String hql = 	"FROM Ubicacion u where u.nombre " +
						"IN :nombresDeUbicacion";
		
		Query<Ubicacion> query = session.createQuery(hql, Ubicacion.class);
		query.setParameter("nombresDeUbicacion", nombresDeUbicacion);
		
		return query.list();
	}

}
