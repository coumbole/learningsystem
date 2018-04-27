package org.kumpulainen.learningsystem.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.kumpulainen.learningsystem.*;

import java.util.Date;


public class TestResult extends TestCase {

    private Student s1;
    private Teacher t1;
    private Course c1;
    private ResultId rid;
    private Result r1;
    private Date st;

    public TestResult( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestResult.class );
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

    public void testResultId() {
        assertEquals(rid, r1.getId());
    }

    public void testResult() {
        assertEquals(5, r1.getGrade());
    }

}
