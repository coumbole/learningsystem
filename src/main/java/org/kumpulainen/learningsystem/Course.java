package org.kumpulainen.learningsystem;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "course")
public class Course
{
    @Id
    private String code;

    private String name;
    private int credit;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;

    public Course() {}

    public Course(String code, String name, int credit, Teacher teacher, Date start) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.teacher = teacher;
        this.start_time = start;
        this.students = new HashSet<>();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Date getStartTime() {
        return start_time;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setCredit(int credit) {
        if (credit > 0 && credit <= 8) {
            this.credit = credit;
        }
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStartTime(Date start_time) {
        this.start_time = start_time;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    @Override
    public String toString() {
        return String.format("Course(%s, %s, %d, %s, %s)",
                this.getCode(),
                this.getName(),
                this.getCredit(),
                this.getTeacher().toString(),
                this.getStartTime().toString());
    }
}
