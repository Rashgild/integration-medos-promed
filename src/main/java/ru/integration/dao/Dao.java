package ru.integration.dao;

import java.util.List;
import java.util.Map;


public interface Dao<Id, Entity> {

    Entity getById(Id id);

    List<Entity> getAll(Entity entity, String entityName);

    void save(Entity entity);

    void saveList(List<Entity> entities);

    void update(Entity entity);

    void delete(Entity entity);

    void deleteById(Id id);

    List<?> getAllE(String entityName);

    List<?> getAllE(String entityName, String whereString);

    List<Entity> getEntityList(String entity, String whereString);

    Entity getEntity(String entity, String whereString);

    int updateRecord(String entityName, Map<String, String> params, String whereString);

    Entity getEntity(Entity entity);

    Entity getEntityM(String entity, String whereString);

    List<Entity> getListEntityM(String entity, String whereString);

    List<Entity> getListEntity(Entity entity);

    int updateRecordM(String entityName, Map<String, String> params, String whereString);
}
