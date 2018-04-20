package org.kumpulainen.learningsystem;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ResultId implements Serializable {

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "course_id")
    private String courseId;

    public ResultId() {}

    public ResultId(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseId = courseCode;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public String getCourseId() {
        return this.courseId;
    }
}

