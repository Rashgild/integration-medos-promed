package ru.integration.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ru.integration.util.HibernateMedosSessionFactory;
import ru.integration.util.HibernateSessionFactory;


public class DaoImpl<Id, Entity> implements Dao<Id, Entity> {

    private Session currentSession;
    private Transaction currentTransaction;

    private Session openCurrentSession() {
        currentSession = HibernateSessionFactory.getSessionFactory().openSession();
        return currentSession;
    }

    private Session openCurrentSessionwithTransaction() {
        currentSession = HibernateSessionFactory.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    private void closeCurrentSession() {
        currentSession.close();
    }

    private void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    public void delete(Entity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void save(Entity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void saveList(List<Entity> entities) {
        try {
            for (Entity entity : entities) {
                save(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Entity entity) {
        Session session = openCurrentSessionwithTransaction();
        session.update(entity);
        closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteById(Id id) {
        Session session = openCurrentSessionwithTransaction();
        session.delete(id);
        closeCurrentSessionwithTransaction();
    }

    @Override
    public Entity getById(Id id) {
        throw new UnsupportedOperationException();
    }

    public List<Entity> getAll(Entity entity, String entityName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Entity> list = session.createQuery("from " + entityName).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<?> getAllE(String entityName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<?> list = session.createQuery("from " + entityName).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<?> getAllE(String entityName, String whereString) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<?> list = session.createQuery("from " + entityName + " where " + whereString).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<Entity> getEntityList(String entity, String whereString) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Entity> list = session.createQuery("from " + entity + " where " + whereString).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Entity getEntity(String entity, String whereString) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Entity> list = session.createQuery("from " + entity + " where " + whereString).list();
        session.getTransaction().commit();
        session.close();

        return list.stream().findFirst().orElse(null);
    }

    public List<Entity> sync(List<Entity> entities) {
        List<Entity> result = new ArrayList<>();
        Iterator iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity o = (Entity) iterator.next();
            if (!result.contains(o)) result.add(o);
        }
        return result;
    }

    public List<Entity> sync(List<Entity> entities, List<Entity> fromBase) {
        List<Entity> result = new ArrayList<>();
        Iterator iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity o = (Entity) iterator.next();
            if (!fromBase.contains(o)) result.add(o);
        }
        return result;
    }

    public int updateRecord(String entityName, Map<String, String> params, String whereString) {
        String setString = "";
        int i = 0;
        setString = getString(params, setString, i);

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int result = session.createQuery("update " + entityName + " set " + setString + " " + whereString).executeUpdate();
        tx.commit();
        session.close();
        return result;
    }

    public List<Entity> getMedosEntityList(String entity) {
        return getEntities((Entity) entity);
    }

    public List<Entity> getMedosEntityList(String entity, String where) {
        return getEntities(entity, where);
    }

    private List<Entity> getEntities(String entity, String where) {
        Session session = HibernateMedosSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Entity> list = session.createQuery("from " + entity + " where " + where).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<Entity> getListEntity(Entity entity) {
        return getEntities((Entity) entity);
    }

    private List<Entity> getEntities(Entity entity) {
        Session session = HibernateMedosSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Entity> list = session.createQuery("from " + entity).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<Entity> getListEntityM(String entity, String whereString) {
        return getEntities(entity, whereString);
    }

    public Entity getEntity(Entity entity) {
        Session session = HibernateMedosSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Entity> list = session.createQuery("from " + entity).list();
        session.getTransaction().commit();
        session.close();
        return list.stream().findFirst().orElse(null);
    }

    public Entity getEntityM(String entity, String whereString) {
        Session session = HibernateMedosSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Entity> list = session.createQuery("from " + entity + " where " + whereString).list();
        session.getTransaction().commit();
        session.close();
        return list.stream().findFirst().orElse(null);
    }

    public int updateRecordM(String entityName, Map<String, String> params, String whereString) {
        String setString = "";
        int i = 0;
        setString = getString(params, setString, i);

        Session session = HibernateMedosSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int result = session.createQuery("update " + entityName + " set " + setString + " " + whereString).executeUpdate();
        tx.commit();
        session.close();
        return result;
    }

    private String getString(Map<String, String> params, String setString, int i) {
        for (Map.Entry entry : params.entrySet()) {
            if (i > 0) setString += ",";
            setString += entry.getKey() + "=" + entry.getValue();
            i++;
        }
        return setString;
    }
}
