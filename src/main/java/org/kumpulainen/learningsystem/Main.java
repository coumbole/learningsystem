package org.kumpulainen.learningsystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager em = emf.createEntityManager();

        System.out.print("EntityManager is open: " + em.isOpen());

        System.exit(0);
    }
}
