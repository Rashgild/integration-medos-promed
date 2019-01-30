package ru.integration.dao;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

abstract class AbstractDao {

    private SessionFactory sessionFactory = buildSessionFactory();

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Client client;

    @PostConstruct
    public void init() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
    }

    private SessionFactory buildSessionFactory() {
        /*final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
        return sessionFactory;*/

        if (sessionFactory != null) {
            return sessionFactory;
        }
        Configuration medosConfig = new Configuration();
        medosConfig.configure("hibernate.cfg.xml");
        sessionFactory = medosConfig.buildSessionFactory();
        return sessionFactory;
    }

    void persist(Object entity) {
        Session session = getSession();
        Transaction trans = session.beginTransaction();
        session.save(entity);
        trans.commit();
    }

    void delete(Object entity) {
        Session session = getSession();
        Transaction trans = session.beginTransaction();
        session.delete(entity);
        trans.commit();
    }

    void update(Object entity) {
        Session session = getSession();
        Transaction trans = session.beginTransaction();
        session.update(entity);
        trans.commit();
    }

    public void shutdown() {
        getSession().close();
    }
}
