package org.library.repository;

import org.library.util.DbConnection;

import javax.persistence.EntityManager;

public class Repository<T> {

    private final EntityManager entityManager;

    public Repository() {
        entityManager = DbConnection.getConnection();
    }

    public void insertObj(T obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }

    public T getObjById(Class<T> clazz, Long id) {
        return entityManager.find(clazz, id);
    }
}
