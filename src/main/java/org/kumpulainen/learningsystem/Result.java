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

}


