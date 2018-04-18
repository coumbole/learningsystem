package org.kumpulainen.learningsystem;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.kumpulainen.learningsystem.Role;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class ModelTest
    extends TestCase
{

    private Student s1;
    private Teacher t1;
    private Course c1;
    private ResultId rid;
    private Result r1;
    private Date st;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ModelTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ModelTest.class );
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


    /**
     * Rigourous Test :-)
     */
    public void testStudent() {
        assertTrue(s1.getCode().equals("abc123"));
        assertTrue(s1.getName().equals("Test Dude"));
        assertTrue(s1.getEmail().equals("test.dude@school.edu"));
    }

    public void testTeacer() {
        assertTrue(t1.getCode().equals("xyz456"));
        assertTrue(t1.getName().equals("Test Teacher"));
        assertTrue(t1.getEmail().equals("test.teacher@school.edu"));
    }

    public void testCourse() {
        assertTrue(c1.getCode().equals("CCF-9000"));
        assertTrue(c1.getName().equals("Service Design Basics"));
        assertTrue(c1.getCredit() == 5);
        assertTrue(c1.getTeacher().equals(t1));
        assertTrue(c1.getStartTime().equals(st));
    }

    public void testResultId() {
        assertTrue(r1.getId().equals(rid));
    }

    public void testResult() {
        assertTrue(r1.getGrade() == 5);
    }

    public void testLogin() {
        assertTrue(s1.login("hunter2"));
        assertFalse(t1.login("abcdefg"));
    }
}
