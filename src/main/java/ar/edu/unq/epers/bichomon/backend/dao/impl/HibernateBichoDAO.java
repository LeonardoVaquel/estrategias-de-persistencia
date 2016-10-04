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
	 * Dado un nombre de {@link Entrenador} se realiza una búsqueda de {@link Bicho} en la 
	 * {@link Ubicacion} actual de dicho entrenador, se actualiza su experiencia y devuelve 
	 * una instancia de {@link Bicho} si la búsqueda fue exitosa.  
	 */
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

	/**
	 * Dado un nombre de {@link Entrenador} y un id de {@link Bicho} se intenta abandonar el bicho en la
	 * ubicación actual de dicho entrenador.
	 * En caso que la {@link Ubicación} no permita realizar abandonos se arrojará una 
	 * excepción {@link NoSePuedeAbandonarEnUbicacionException}  
	 */
	@Override
	public void abandonar(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		Bicho bicho = session.get(Bicho.class, idBicho);
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		
		// el entrenador no es necesario
		//entrenador.abandonar(bicho);
		bicho.getOwner().abandonar(bicho);
		
	}

	/**
	 * Dado un nombre de {@link Entrenador} y un id de {@link Bicho} se realiza un duelo en la
	 * {@link Ubicacion} actual de dicho entrenador y se le asigna la cantidad de experiencia 
	 * correspondiente.
	 * Se espera retornar una instancia {@link ResultadoCombate} con los detalles del duelo y
	 * el actual campeón de un {@link Dojo}
	 * En caso que la {link Ubicacion} no permita realizar duelos se arrojará una
	 * excepción {@link NoSePuedeRealizarDueloEnUbicacionException}
	 */
	@Override
	public ResultadoCombate duelo(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		Bicho bicho = session.get(Bicho.class, idBicho);
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		
		ResultadoCombate resultadoCombate = entrenador.duelo(bicho);
		
		TablaDeExperiencia expTable = session.get(TablaDeExperiencia.class, "Combatir"); 
		Experiencia expCfg = session.get(Experiencia.class, "v1.0.0");
		
		double experienciaPorCombate = expTable.getValor();
		
		System.out.println(resultadoCombate);
		resultadoCombate.getBichoPerdedor().getOwner().gainsExp(experienciaPorCombate, expCfg);
		entrenador.gainsExp(experienciaPorCombate, expCfg);
		
		return resultadoCombate;
	}

	/**
	 * Dado un nombre de {@link Entrenador} y un id de {@link Bicho} se evalúa si el bicho
	 * especificado está en condiciones de evolucionar.
	 */
	@Override
	public boolean puedeEvolucionar(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();

		Bicho bicho = session.get(Bicho.class, idBicho);

		boolean result = bicho.puedeEvolucionar();
		
		return result;
	}

	/**
	 * Dado un nombre de {@link Entrenador} y un id de {@link Bicho} se evoluciona el bicho
	 * especificado y se le asigna la cantidad de experiencia correspondiente al entrenador.
	 * Se retorna un {@link Bicho} con su especie modificada. 
	 */
	@Override
	public Bicho evolucionar(String nombreEntrenador, int idBicho) {
		Session session = Runner.getCurrentSession();
		
		// Aclaración: no es necesario utilizar el Entrenador, ya que es posible
		// mandarle mensajes a éste mediante el owner del bicho
		Entrenador entrenador = session.get(Entrenador.class, nombreEntrenador);
		Bicho bicho = session.get(Bicho.class, idBicho);
		TablaDeExperiencia expTable = session.get(TablaDeExperiencia.class, "Evolucionar"); 
		Experiencia expCfg = session.get(Experiencia.class, "v1.0.0");
		double experienciaPorCombate = expTable.getValor();
		
		entrenador.evolucionar(bicho);
		entrenador.gainsExp(experienciaPorCombate, expCfg);
		return bicho;
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
