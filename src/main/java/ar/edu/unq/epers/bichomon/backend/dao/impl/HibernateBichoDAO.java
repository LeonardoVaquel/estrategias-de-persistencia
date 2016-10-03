package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.duelo.ResultadoCombate;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.Experiencia;
import ar.edu.unq.epers.bichomon.backend.model.experiencia.TablaDeExperiencia;
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
	
	@Override
	public Bicho buscar(String nombreEntrenador) {
		Session session = Runner.getCurrentSession();
		
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador); 
		
		Bicho bicho = entrenador.buscar();
		
		TablaDeExperiencia expTable = session.get(TablaDeExperiencia.class, "Capturar"); 
		Experiencia expCfg = session.get(Experiencia.class, "v1.0.0");
		
		double experienciaPorCaptura = expTable.getValor();
		
		entrenador.gainsExp(experienciaPorCaptura, expCfg);
		entrenador.obtenerBicho(bicho);
		bicho.getEspecie().incrementarCantidad();
		
		return bicho;
	}

	@Override
	public void abandonar(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		Bicho bicho = session.get(Bicho.class, idBicho);
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		
		// el entrenador no es necesario
		entrenador.abandonar(bicho);
		
	}

	@Override
	public ResultadoCombate duelo(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		Bicho bicho = session.get(Bicho.class, idBicho);
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		
		ResultadoCombate resultadoCombate = entrenador.duelo(bicho);
		
		TablaDeExperiencia expTable = session.get(TablaDeExperiencia.class, "Combatir"); 
		Experiencia expCfg = session.get(Experiencia.class, "v1.0.0");
		
		double experienciaPorCombate = expTable.getValor();
		
		resultadoCombate.getBichoPerdedor().getOwner().gainsExp(experienciaPorCombate, expCfg);
		entrenador.gainsExp(experienciaPorCombate, expCfg);
		
		return resultadoCombate;
	}

	@Override
	public boolean puedeEvolucionar(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();

		Bicho bicho = session.get(Bicho.class, idBicho);

		boolean result = bicho.puedeEvolucionar();
		
		return result;
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
		bicho.getEspecie().incrementarCantidad();
		session.save(bicho);
	}

}
