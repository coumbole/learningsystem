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
    private Teacher teacher;
    private Date start_time;
}
