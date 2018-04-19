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
    private String code;

    private String name;
    private int credit;

    @ManyToOne
    private Teacher teacher;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;

    public Course() {}

    public Course(String code, String name, int credit, Teacher teacher, Date start) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.teacher = teacher;
        this.start_time = start;
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
