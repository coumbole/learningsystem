package org.kumpulainen.learningsystem;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {

    @EmbeddedId
    private ResultId id;

    private int grade;

    public Result() {}

    public Result(String studentId, String courseCode, int grade) {
        this.id = new ResultId(studentId, courseCode);
        this.grade = grade;
    }


    public int getGrade() {
        return grade;
    }

}


