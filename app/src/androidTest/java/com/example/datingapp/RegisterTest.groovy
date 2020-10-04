package com.example.datingapp

import android.view.View
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterTest {
    @Rule
    public ActivityTestRule<Register>mRegisterTestRule = new ActivityTestRule<Register>(Register.class);
    private  Register mRegister = null;

    @Before
    void setUp() {
        super.setUp()
        mRegister = mRegisterTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mRegister.findViewById(R.id.regphone);

        assertNotNull(view);
    }


    @After
    void tearDown() {

        mRegister = null;

    }
}
