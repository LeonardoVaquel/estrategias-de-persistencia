package ar.edu.unq.epers.bichomon.backend.dao.impl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.buscador.Tupla;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

/**
 * Una implementacion de {@link EspecieDAO} que persiste
 * en una base de datos relacional utilizando Hibernate
 * 
 * @author santiago
 */
public class HibernateEspecieDAO implements EspecieDAO, EspecieService {

	/**
	 * Dada una instancia de {@link Especie} se guarda en un ambiente persistente
	 */
	@Override
	public void crearEspecie(Especie especie) {
		Session session = Runner.getCurrentSession();
		session.save(especie);
	}

	/**
	 * Dado un nombre de especie se retorna una instancia de {@link Especie}
	 */
	@Override
	public Especie getEspecie(String nombreEspecie) {
		Session session = Runner.getCurrentSession();
		return session.get(Especie.class, nombreEspecie);
	}
	
	/**
	 * Se espera retornar una lista de {@link Especie} si existen en una base de datos 
	 */
	@Override
	public List<Especie> getAllEspecies() {
		Session session = Runner.getCurrentSession();
		
		String hql = "from Especie e";
		
		Query<Especie> query = session.createQuery(hql, Especie.class);
		
		return query.getResultList();
	}

	/**
	 * Deprecado al comenzar a utilizar hibernate (los bichos ya no tienen nombre)
	 * Momentaneamente se decide mantener la implementación vacía para respetar
	 * el uso de la interfaz {@link EspecieService}
	 */
	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Se borran todos los registros de {@link Especie} del ambiente persistente
	 */
	public void removeAllEspecies() {
		Session session = Runner.getCurrentSession();
		
		session.createQuery("delete from Especie").executeUpdate();
	}
	
	/**
	 * Dada una instancia de {@link Tupla} se guarda en un ambiente persistente
	 */
	public void guardarTupla(Tupla tupla) {
		Session session = Runner.getCurrentSession();
		
		session.save(tupla);
	}

	@Override
	public List<Especie> populares(){
		Session session = Runner.getCurrentSession();
		
		String hql = "from Especie e "+
					 "order by e.cantidadBichos desc";
		
		Query<Especie> query = session.createQuery(hql, Especie.class);
		
		return query.getResultList();
	}


}
