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
 */
public class HibernateEspecieDAO implements EspecieDAO, EspecieService {

	@Override
	public void crearEspecie(Especie especie) {
		Session session = Runner.getCurrentSession();
		session.save(especie);
	}

	@Override
	public Especie getEspecie(String nombreEspecie) {
		Session session = Runner.getCurrentSession();
		return session.get(Especie.class, nombreEspecie);
	}
	
	@Override
	public List<Especie> getAllEspecies() {
		Session session = Runner.getCurrentSession();
		
		String hql = "from Especie e";
		
		Query<Especie> query = session.createQuery(hql, Especie.class);
		
		return query.getResultList();
	}

	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void removeAllEspecies() {
		Session session = Runner.getCurrentSession();
		
		Query query = session.createQuery("delete from Especie");
		query.executeUpdate();
		
		session.delete(Especie.class);
		
	}
	
	public void guardarTupla(Tupla tupla) {
		Session session = Runner.getCurrentSession();
		
		session.save(tupla);
	}

}
