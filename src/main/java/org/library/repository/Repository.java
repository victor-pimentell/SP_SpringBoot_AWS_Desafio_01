package org.library.repository;

import org.library.util.DbConnection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public T getObjById(Class<T> classs, Long id) {
        return entityManager.find(classs, id);
    }

    public List<T> getAll(Class<T> classs) {
        return entityManager.createQuery("FROM " + classs.getName(), classs).getResultList();
    }

    public List<T> getAllAvailable(Class<T> classs) {
        return entityManager.createQuery("FROM " + classs.getName() + " obj WHERE obj.quantity > 0", classs).getResultList();
    }

    public T getObjByName(Class<T> clazz, String name) {
        TypedQuery<T> query = entityManager.createQuery("FROM " + clazz.getName() + " obj WHERE obj.name = :name", clazz);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public T getObjByIsbn(Class<T> clazz, String isbn) {
        TypedQuery<T> query = entityManager.createQuery("FROM " + clazz.getName() + " obj WHERE obj.isbn = :isbn", clazz);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    public T getObjByEmail(Class<T> clazz, String email) {
        TypedQuery<T> query = entityManager.createQuery("FROM " + clazz.getName() + " obj WHERE obj.email = :email", clazz);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
