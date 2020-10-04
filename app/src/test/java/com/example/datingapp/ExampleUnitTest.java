package com.example.datingapp;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private ideas ideas;
    @Before
    public void setUp(){
        ideas = new ideas();
    }
    @Test
    public void Validation(){
        //Calling the method
        String id1 = "";
        //Delete the subject.
        boolean status = ideas.check(id1);
        //Check
        assertFalse(status);
    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}
