package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.TablaDeExperiencia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Guarderia;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link BichoDAO} que persiste y recupera información
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateUbicacionDAO implements UbicacionDAO {

	@Override
	public void guardarGuarderia(Guarderia guarderia) {
		Session session = Runner.getCurrentSession();
		session.save(guarderia);
	}

	
}
