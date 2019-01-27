package ru.integration.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateMedosSessionFactory {

    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        Configuration medosConfig = new Configuration();
        medosConfig.configure("hibernateMedos.cfg.xml");
        sessionFactory = medosConfig.buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
