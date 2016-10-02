package ar.edu.unq.epers.bichomon.backend.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.ubicacion.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

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
		
		System.out.println(nombreUbicacion);
		
		String hql = "from Entrenador e "
				+ "where e.ubicacion.nombre = :unaUbicacion";
		
		Query<Entrenador> query = session.createQuery(hql, Entrenador.class);
		query.setParameter("unaUbicacion", nombreUbicacion);

//		List<Entrenador> list = query.getResultList().stream()
//                .filter(trainer -> trainer.getUbicacion().getNombre() == nombreUbicacion).toL;
                
		
		return query.getResultList().size(); 
	}

	@Override
	public Bicho campeon(String dojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bicho campeonHistorico(String dojo) {
		// TODO Auto-generated method stub
		return null;
	}

}
