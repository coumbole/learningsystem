package org.kumpulainen.learningsystem;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {

    @EmbeddedId
    private ResultId id;

    private int grade;

    public Result() {}

    public Result(ResultId rid, int grade) {
        this.id = rid;
        this.grade = grade;
    }

    public ResultId getId() {
        return id;
    }


    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("Result(Student %s, Course %s, grade %d)", id.getStudentId(), id.getCourseId(), grade);
    }

}


