package ru.integration.dao;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

abstract class AbstractDao {

    private SessionFactory sessionFactory = buildSessionFactory();

    protected Client client;

    @Autowired
    protected Environment environment;

    @PostConstruct
    public void init() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
    }

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Connection getConnection() {
        Connection connection = null;
        String url = environment.getRequiredProperty("jdbc.url");
        String name = environment.getRequiredProperty("jdbc.username");
        String password = environment.getRequiredProperty("jdbc.password");

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("sql exception");
        }
        return connection;
    }

    private SessionFactory buildSessionFactory() {
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

    protected void shutdown() {
        getSession().close();
    }
}
