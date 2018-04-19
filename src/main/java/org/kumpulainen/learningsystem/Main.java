package org.kumpulainen.learningsystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager em = emf.createEntityManager();

        Student student = new Student(
                "abc" + Long.toString(System.currentTimeMillis() % 1000),
                "hunter2",
                "Test Dude",
                "test.dude@school.edu");

        Teacher teacher = new Teacher(
                "xyz" + Long.toString(System.currentTimeMillis() % 1000),
                "qwerty",
                "Test Teacher",
                "test.teacher@school.edu",
                Role.LECTURER);

        Course course = new Course(
                "CCB-9" + Long.toString(System.currentTimeMillis() % 1000),
                "Basics in everything",
                5,
                teacher,
                new Date());

        ResultId rid = new ResultId(student.getCode(), course.getCode());

        Result res = new Result(rid, 5);

        try {
            em.getTransaction().begin();
            em.persist(student);
            em.persist(teacher);
            em.persist(course);
            em.persist(res);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MONTH, 1);
            teacher.createCourse(
                    "FFXX-5" + Long.toString(System.currentTimeMillis() % 1000),
                    "Buzzy buzz course",
                    3,
                    cal.getTime());
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}

