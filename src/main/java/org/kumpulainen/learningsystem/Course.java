package org.kumpulainen.learningsystem;

import javax.persistence.*;
import java.util.Date;

/**
 * Hello world!
 *
 */

@Entity
@Table(name = "course")
public class Course
{
    @Id
    private String course_code;

    private String name;
    private int credit;

    @ManyToOne
    private Teacher teacher;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;

    public Course(String code, String name, int credit, Teacher teacher, Date start) {
        this.course_code = code;
        this.name = name;
        this.credit = credit;
        this.teacher = teacher;
        this.start_time = start;
    }
}
