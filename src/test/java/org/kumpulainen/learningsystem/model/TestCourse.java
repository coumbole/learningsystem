package org.kumpulainen.learningsystem.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.kumpulainen.learningsystem.*;

import java.util.Date;


public class TestCourse extends TestCase {

    private Student s1;
    private Teacher t1;
    private Course c1;
    private ResultId rid;
    private Result r1;
    private Date st;

     public TestCourse( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( TestCourse.class );
    }


    protected void setUp() {
        st = new Date();
        s1 = new Student(
                "abc123",
                "hunter2",
                "Test Dude",
                "test.dude@school.edu");

        t1 = new Teacher(
                "xyz456",
                "qwerty",
                "Test Teacher",
                "test.teacher@school.edu",
                Role.LECTURER);

        c1 = new Course(
                "CCF-9000",
                "Service Design Basics",
                5,
                t1,
                st);

        rid = new ResultId(s1.getCode(), c1.getCode());
        r1 = new Result(rid, 5);
    }

    public void testCourse() {
        assertEquals("CCF-9000", c1.getCode());
        assertEquals("Service Design Basics", c1.getName());
        assertEquals(5, c1.getCredit());
        assertEquals(t1, c1.getTeacher());
        assertEquals(st, c1.getStartTime());
        assertTrue(c1.getStudents().isEmpty());
    }
}
