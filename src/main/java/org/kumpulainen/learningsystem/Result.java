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
}

