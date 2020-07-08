package org.hiber.covid.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionFactoryManger {
	
	private static SessionFactory factory;

	private SessionFactoryManger() {}
	
	public static SessionFactory getFactory() {
		if(factory==null) {
			factory = new Configuration()
					.configure()
					.buildSessionFactory();
		}
		return factory;	
	}
	
	public static void close() {
		factory.close();
	}
	

}
