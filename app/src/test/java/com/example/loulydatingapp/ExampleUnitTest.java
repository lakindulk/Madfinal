package com.example.loulydatingapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Packages packages;

    @Before
    public void setUp() throws Exception {

        packages = new Packages();
    }

    @Test
    public void package1_isCorrect() {

        double result = packages.getPackgeTotal(1);
        assertEquals(2000, result, 0.01);
    }

    @Test
    public void package2_isCorrect() {

        double result = packages.getPackgeTotal(2);
        assertEquals(9000, result, 0.01);
    }

    @Test
    public void package3_isCorrect() {

        double result = packages.getPackgeTotal(3);
        assertEquals(14400, result, 0.01);
    }
}

