package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.TablaDeExperiencia;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeAbandonarEnUbicacionException;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.NoSePuedeRealizarDueloEnUbicacionException;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link BichoDAO} que persiste y recupera información
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateBichoDAO implements BichoDAO {

	public Bicho getBicho(int idBicho) {
		Session session = Runner.getCurrentSession();
		return session.get(Bicho.class, idBicho);
	}
	
	/**
	 * Dada una instancia de {@link Bicho} se guarda en un ambiente persistente
	 */
	@Override
	public void guardarBicho(Bicho bicho) {
		Session session = Runner.getCurrentSession();
		bicho.getEspecie().incrementarCantidad();
		session.save(bicho);
	}
	
	/**
	 * Se borran toda la información referente a bichos
	 */
	@Override
	public void removeAllBichos() {
		Session session = Runner.getCurrentSession();
		
		session.createQuery("delete from Bicho").executeUpdate();
	}

}
