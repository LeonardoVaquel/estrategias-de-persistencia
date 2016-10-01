package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.eventos.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link BichoDAO} que persiste
 * en una base de datos relacional utilizando Hibernate
 * 
 */
public class HibernateBichoDAO implements BichoDAO {

	public Bicho getBicho(String nombreBicho) {
		Session session = Runner.getCurrentSession();
		return session.get(Bicho.class, nombreBicho);
	}
	
	@Override
	public Bicho buscar(String entrenador) {
		Session session = Runner.getCurrentSession();
		return session.get(Bicho.class, entrenador);
	}

	// TODO
	@Override
	public void abandonar(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		Bicho bicho = session.get(Bicho.class, idBicho);
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		
		entrenador.abandonarBicho(bicho);
		
		session.save(entrenador);
		session.save(bicho);
		
		
		
	}

	@Override
	public ResultadoCombate duelo(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		Bicho bicho = session.get(Bicho.class, idBicho);
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		
		// historial
		
		return entrenador.duelo(bicho);
	}

	@Override
	public boolean puedeEvolucionar(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		Bicho bicho = session.get(Bicho.class, idBicho);
		return bicho.puedeEvolucionar();
	}

	@Override
	public Bicho evolucionar(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		Bicho bicho = session.get(Bicho.class, idBicho);
		return bicho.evolucionar();
	}

	@Override
	public void guardarBicho(Bicho bicho) {
		Session session = Runner.getCurrentSession();
		session.save(bicho);
	}

}
