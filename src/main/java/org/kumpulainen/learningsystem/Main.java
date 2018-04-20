package org.kumpulainen.learningsystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager em = emf.createEntityManager();

        Student student = new Student(
                "abc" + Long.toString(System.currentTimeMillis() & 1000),
                "hunter2",
                "Test Student",
                "test.student@school.edu");

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


        try {
            em.getTransaction().begin();
            em.persist(student);
            student.takeCourse(course);
            em.persist(teacher);
            em.persist(course);
            course.addStudent(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        Iterator i = student.getCourses().iterator();
        if (i.hasNext()) {
            logger.info("First taken course: " + i.next());
        }

        Iterator a = course.getStudents().iterator();
        if (a.hasNext()) {
            logger.info("Course " + course.getCode() + " has student: " + a.next());
        }

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MONTH, 1);
            teacher.updateCourse(course.getCode(), "name", "pWn3d");
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}

