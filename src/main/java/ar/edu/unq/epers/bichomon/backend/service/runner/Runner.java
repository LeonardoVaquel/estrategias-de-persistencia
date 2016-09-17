package ar.edu.unq.epers.bichomon.backend.service.runner;

import java.util.function.Supplier;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
	
	private static final ThreadLocal<Session> CONTEXTO = new ThreadLocal<>();
	
	public static <T> T runInSession(Supplier<T> bloque) {
		// permite anidar llamadas a Runner sin abrir una nueva
		// Session cada vez (usa la que abrio la primera vez)
		if (CONTEXTO.get() != null) {
			return bloque.get();
		}
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = SessionFactoryProvider.getInstance().createSession();
			tx = session.beginTransaction();
			
			CONTEXTO.set(session);
			
			//codigo de negocio
			T resultado = bloque.get();
			
			tx.commit();
			return resultado;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			if (session != null) {
				CONTEXTO.set(null);
				session.close();
			}
		}
	}

	public static Session getCurrentSession() {
		Session session = CONTEXTO.get();
		if (session == null) {
			throw new RuntimeException("No hay ninguna session en el contexto");
		}
		return session;
	}	
}
