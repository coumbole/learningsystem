package org.kumpulainen.learningsystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager em = emf.createEntityManager();

        Student student = new Student(
                "abc123",
                "hunter2",
                "Test Dude",
                "test.dude@school.edu");

        System.out.println("Created: " + student.toString());

        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}
