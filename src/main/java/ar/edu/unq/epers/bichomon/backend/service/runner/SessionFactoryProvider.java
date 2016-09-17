package ar.edu.unq.epers.bichomon.backend.service.runner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
	
	private static final SessionFactoryProvider INSTANCE = new SessionFactoryProvider();

	private SessionFactory sessionFactory;
	
	public static SessionFactoryProvider getInstance() {
		return INSTANCE;
	}
	
	private SessionFactoryProvider() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		this.sessionFactory = configuration.buildSessionFactory();
	}
	
	public Session createSession() {
		return this.sessionFactory.openSession();
	}

}