package org.kumpulainen.learningsystem.model;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.kumpulainen.learningsystem.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Unit test for simple App.
 */
public class TestTeacher extends TestCase {

    private Student s1;
    private Teacher t1;
    private Course c1;
    private ResultId rid;
    private Result r1;
    private Date st;
    private Calendar calendar = Calendar.getInstance();

    private String courseCode = "CSC" + System.currentTimeMillis() % 1000;
    private String studentCode = "abc" + System.currentTimeMillis() % 1000;
    private String teacherCode = "xyz" + System.currentTimeMillis() % 1000;

    public TestTeacher(String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestTeacher.class );
    }

    protected void setUp() {
        st = new Date();
        s1 = new Student(
                studentCode,
                "hunter2",
                "Test Dude",
                "test.dude@school.edu");

        t1 = new Teacher(
                teacherCode,
                "qwerty",
                "Test Teacher",
                "test.teacher@school.edu",
                Role.LECTURER);

        c1 = new Course(
                courseCode,
                "Service Design Basics",
                5,
                t1,
                st);

        rid = new ResultId(s1.getCode(), c1.getCode());
        r1 = new Result(rid, 5);

        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 15);
    }

    public void testTeacher() {
        assertEquals(teacherCode, t1.getCode());
        assertEquals("Test Teacher", t1.getName());
        assertEquals("test.teacher@school.edu", t1.getEmail());
    }

    public void testLogin() {
        assertFalse(t1.login("abcdefg"));
        assertTrue(t1.login("qwerty"));
    }

    public void testCheckStudentsInCourse() {
        c1.addStudent(s1);
        HashSet<Student> studentSet = new HashSet<>();
        studentSet.add(s1);
        assertEquals(studentSet, c1.getStudents());
    }
}

