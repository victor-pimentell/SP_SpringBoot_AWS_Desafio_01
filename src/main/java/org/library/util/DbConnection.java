package org.library.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConnection {

    public static EntityManager getConnection() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Library");
        return entityManagerFactory.createEntityManager();
    }
}
