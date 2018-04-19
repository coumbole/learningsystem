package org.kumpulainen.learningsystem;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Path;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


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

    /*
     * TODO:
     *  1. Actually query the input coursecode instead of all courses
     *  2. Implement validations for changes
     *  3. Implement mutation
     *
     * @param courseCode    - the course id, used to fetch the course object from the DB
     * @param field         - the db field that will be mutated
     * @param value         - the new value for the field determined above
     * @return              - the mutated course object if successfull, the rollbacked old course object if not
     */
    /*
    public void updateCourse(String courseCode, String field, String value) {

        CriteriaQuery<String> courseQuery = this.builder.createQuery(String.class);
        Root<Course> course = courseQuery.from(Course.class);
        Path<String> queryCourseCode = course.get("courseCode");
        courseQuery.select(queryCourseCode);
        courseQuery.orderBy(this.builder.asc(queryCourseCode));
        List<String> result = this.entityManager.createQuery(courseQuery).getResultList();
        System.out.println("Course found:");
        Iterator resultIter = result.iterator();
        while (resultIter.hasNext()) {
            System.out.println(resultIter.next());
        }
    }
    */


    @Override
    public String toString() {
        return String.format("Teacher(%s, %s, %s, %s)",
                this.getName(),
                this.getEmail(),
                this.getCode(),
                this.getRole());
    }

}
