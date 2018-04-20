package org.kumpulainen.learningsystem;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "teacher")
public class Teacher extends User implements Serializable {

    @Id
    private String code;

    private String password, name, email;

    private Role role;

    public Teacher() {}

    public Teacher(String code_, String pwd, String name, String email, Role role) {
        this.code = code_;
        this.password = this.hasher.hash(pwd);
        this.name = name;
        this.email = email;
        this.role = role;
    }

    @Override
    String getCode() {
        return this.code;
    }

    @Override
    String getName() {
        return this.name;
    }

    @Override
    String getEmail() {
        return this.email;
    }

    @Override
    boolean login(String password) {
        return this.hasher.verifyHash(password, this.password);
    }


    public Role getRole() {
        return role;
    }


    public boolean createCourse(String courseCode, String name, int credits, Date startTime) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager entityManager = emFactory.createEntityManager();

        if (!Utils.validateCourse(courseCode, name, credits, startTime)) {
            throw new IllegalArgumentException("Course parameters are invalid.");
        }


        Course newCourse = new Course(courseCode, name, credits, this, startTime);
        logger.info(this.toString() + " created a new course called " + name + " with code " + courseCode);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newCourse);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.severe("Failed writing new course to database.");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * @param courseCode    - the course id, used to fetch the course object from the DB
     * @param field         - the db field that will be mutated
     * @param value         - the new value for the field determined above
     * @return              - the mutated course object if successfull, the rollbacked old course object if not
     */
    public void updateCourse(String courseCode, String field, String value) {

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager entityManager = emFactory.createEntityManager();
        Course course = entityManager.find(Course.class, courseCode);
        logger.info(course.toString());
        entityManager.getTransaction().begin();
        switch (field) {
            case "credit":
                course.setCredit(Integer.parseInt(value));
                break;
            case "name":
                course.setName(value);
                break;
            case "teacher":
                course.setTeacher(entityManager.find(Teacher.class, value));
                break;
            case "startTime":
                course.setStartTime(Utils.parseDate(value));
                break;
            default:
                logger.info(String.format("Field %s didn't match, nothing updated.", field));
                break;
        }
        entityManager.getTransaction().commit();
        logger.info(String.format("The %s of course %s was updated to %s", field, course.getCode(), value));
    }

    public void giveGrade(Course course, Student student, int grade) {

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager entityManager = emFactory.createEntityManager();

        ResultId rid = new ResultId(student.getCode(), course.getCode());
        Result result = new Result(rid, grade);
        logger.info(String.format("Teacher %s created a new %s", this.getCode(), result.toString()));

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(result);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            logger.severe(String.format("Failed writing result %s to database.", result.toString()));
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        return String.format("Teacher(%s, %s, %s, %s)",
                this.getName(),
                this.getEmail(),
                this.getCode(),
                this.getRole());
    }

}
