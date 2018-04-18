package org.kumpulainen.learningsystem;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Path;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Entity
@Table(name = "teacher")
public class Teacher extends User {

    @Id
    private String id;

    private Role role;

    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("learningsystem");
    private EntityManager entityManager = emFactory.createEntityManager();
    private CriteriaBuilder builder = entityManager.getCriteriaBuilder();

    public Teacher() {}

    public Teacher(String code, String pwd, String name, String email, Role role) {
        super(code, pwd, name, email);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }


    /* TODO:
     *  1. Implement validations
     *  2. Allow creating a course for another teacher
     */
    public Course createCourse(String courseCode, String name, int credits, Date startTime) {
        return new Course(courseCode, name, credits, this, startTime);
    }

    /**
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

}
