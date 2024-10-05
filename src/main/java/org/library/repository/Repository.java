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

    public T getObjByName(Class<T> classs, String name) {
        TypedQuery<T> query = entityManager.createQuery("FROM " + classs.getName() + " obj WHERE obj.name = :name", classs);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public T getObjByIsbn(Class<T> classs, String isbn) {
        TypedQuery<T> query = entityManager.createQuery("FROM " + classs.getName() + " obj WHERE obj.isbn = :isbn", classs);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    public T getObjByEmail(Class<T> classs, String email) {
        TypedQuery<T> query = entityManager.createQuery("FROM " + classs.getName() + " obj WHERE obj.email = :email", classs);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
