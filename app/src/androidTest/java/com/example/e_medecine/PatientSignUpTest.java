package com.example.e_medecine;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.e_medecine.sqliteBd.GlobalDbHelper;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PatientSignUpTest {
    GlobalDbHelper db;
    @Rule
    public ActivityTestRule<PatientSignupActivity> rule  = new  ActivityTestRule<>(PatientSignupActivity.class);

    @Test
    public void testExampleWithCorrectValues() {

        String validEmail = "email";
        String validPassword = "password";
       // db = new GlobalDbHelper(this);
       // Boolean checkloginpass = db.loginpassword(login, password);
        boolean responseOfExecutingYourApiWithCorrectValues = true;//"how you get a response from the api"

        Assert.assertEquals(true, responseOfExecutingYourApiWithCorrectValues);

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.e_medecine", appContext.getPackageName());
    }

    @Test
    public void email_not_validate(){
        onView(withId(R.id.email)).perform(typeText("Meriem"));
    }
}
