package org.kumpulainen.learningsystem.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.kumpulainen.learningsystem.*;

import java.util.Date;

public class TestStudent extends TestCase {


    private Student s1;
    private Course c1;
    private Teacher t1;
    private Date date = new Date();

    private String studentCode = "abc" + System.currentTimeMillis() % 1000;
    private String teacherCode = "xyz" + System.currentTimeMillis() % 1000;
    private String courseCode = "CSC" + System.currentTimeMillis() % 1000;

    public TestStudent( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestStudent.class );
    }

    protected void setUp() {
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

        c1 = new Course(courseCode, "Basics in stuff", 5, t1, date);
    }

    public void testStudent() {
        assertEquals(studentCode, s1.getCode());
        assertEquals("Test Dude", s1.getName());
        assertEquals("test.dude@school.edu" ,s1.getEmail());
        assertTrue(s1.getCourses().isEmpty());
    }

    public void testLogin() {
        assertTrue(s1.login("hunter2"));
        assertFalse(s1.login("qwerty"));
    }

    public void testAddStudentToCourse() {
        System.out.println("Student: " + s1.toString());
        c1.addStudent(s1);
        assertEquals(1, c1.getStudents().size());
        c1.removeStudent(s1);
        assertTrue(c1.getStudents().isEmpty());

        s1.takeCourse(c1);
        assertEquals(1, s1.getCourses().size());
        assertTrue(s1.getCourses().contains(c1));
        s1.leaveCourse(c1);
        assertTrue(s1.getCourses().isEmpty());
    }

    public void testTakenCredits() {
        assertEquals(0, s1.takenCredits());
        s1.takeCourse(c1);
        assertEquals(5, s1.takenCredits());
    }
}
