package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Se crea "SessionFactory" desde hibernate.cfg.xml
 *
 * El c�digo esta hecho en ingles, la interfaz gr�fica tiene las etiquetas en
 * espa�ol y los comentarios se dejan en espa�ol para facilidad de revisi�n
 *
 * @author Cristian Felipe Ramos Lopez
 * @version 1.0
 * @since 2018-12-14
 */

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}
