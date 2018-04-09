package org.kumpulainen.learningsystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "result")
public class Result {

    // TODO: Needs two primary keys
    @Id
    private String student_id, course_id;

    private int grade;

    public Result(String student, String course, int grade) {
        this.student_id = student;
        this.course_id = course;
        this.grade = grade;
    }
}

