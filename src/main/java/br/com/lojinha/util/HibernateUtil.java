package br.com.lojinha.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import org.hibernate.Session;

public class HibernateUtil {
	private static Configuration configuration;
	private static SessionFactory sessionFactory;

	public static void createSessionFactory() {
		cfgDatabase();
	}

	public static void closeSessionFactory() {
		sessionFactory.close();
	}


	private static void cfgDatabase() {
		// configuracao via arquivo xml
		configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			createSessionFactory();
		}
		return sessionFactory;
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

	public static void closeSession(Session pSession) {
		if (pSession.isOpen()) pSession.close();
	}

}