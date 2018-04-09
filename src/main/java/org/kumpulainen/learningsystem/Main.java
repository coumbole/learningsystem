package org.kumpulainen.learningsystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException err) {
            System.err.println("PostgreSQL jdbc driver not found.");
            err.printStackTrace();
            System.exit(0);
        } catch (SQLException err) {
            System.err.println("Couldn't register postgresql driver");
            err.printStackTrace();
            System.exit(0);
        }

        System.out.println("PostreSQL JDBC driver found.");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/learningsystem",
                    "learning", "FsoN7e9UTI3EpzrjzXz1oqD3Y");
        } catch (SQLException err) {
            err.printStackTrace();
            System.err.println(err.getClass().getName()+": "+err.getMessage());
            System.exit(0);
        }
        if (connection != null) {
            System.out.print("Opened db successfully!");
        } else {
            System.out.println("Failed to make connection.");
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager em = emf.createEntityManager();

        System.out.print("EntityManager is open: " + em.isOpen());

        System.exit(0);
    }
}
