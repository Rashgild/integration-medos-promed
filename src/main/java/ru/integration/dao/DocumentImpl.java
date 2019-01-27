package ru.integration.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ru.integration.entities.PersonEntity;
import ru.integration.util.HibernateSessionFactory;

import ru.integration.entities.DocumentEntity;

public class DocumentImpl implements Docmunet{

    public DocumentEntity getDocumentByPerson(PersonEntity aPerson) {
        Session session = null;
        DocumentEntity document = null;
        List<DocumentEntity> documents = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from DocumentEntity where person=:person")
                    .setParameter("person", aPerson.getId());

            documents = query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            if (documents != null && documents.size() > 0) {
                document = documents.get(0);
            }
        }
        return document;
    }
}
