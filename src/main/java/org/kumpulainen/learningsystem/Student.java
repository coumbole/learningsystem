package org.kumpulainen.learningsystem;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student extends User implements Serializable {

    @Id
    private String code;

    private String password, name, email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Student() {}

    public Student(String code_, String pwd, String name, String email) {
        this.code = code_;
        this.password = this.hasher.hash(pwd);
        this.name = name;
        this.email = email;
        this.courses = new HashSet<>();
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean login(String password) {
        return this.hasher.verifyHash(password, this.password);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void takeCourse(Course course) {
        this.courses.add(course);
    }

    public void leaveCourse(Course course) {
        this.courses.remove(course);
    }

    public int getGrade(String courseCode) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("learningsystem");
        EntityManager entityManager = emFactory.createEntityManager();
        ResultId rid = new ResultId(this.getCode(), courseCode);
        return entityManager.find(Result.class, rid).getGrade();
    }

    public int takenCredits() {
        Iterator i = courses.iterator();
        int sum = 0;
        while (i.hasNext()) {
            Course current = (Course)i.next();
            sum += current.getCredit();
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("Student(%s, %s, %s)",
                this.getCode(),
                        this.getName(),
                        this.getEmail());
    }
}
