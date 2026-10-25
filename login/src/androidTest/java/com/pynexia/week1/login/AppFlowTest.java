package com.pynexia.week1.login;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.pm.ActivityInfo;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppFlowTest {
    private void snapshot(String name) throws java.io.IOException {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        android.graphics.Bitmap bitmap = InstrumentationRegistry.getInstrumentation().getUiAutomation().takeScreenshot();
        java.io.File folder = new java.io.File(InstrumentationRegistry.getInstrumentation().getTargetContext().getExternalFilesDir(null), "evidence");
        if (!folder.exists() && !folder.mkdirs()) throw new java.io.IOException("Cannot create screenshot folder");
        try (java.io.FileOutputStream stream = new java.io.FileOutputStream(new java.io.File(folder, name + ".png"))) {
            if (!bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, stream)) throw new java.io.IOException("Cannot save screenshot");
        } finally { bitmap.recycle(); }
    }

    private Activity launch(Class<? extends Activity> type) {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), type);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return InstrumentationRegistry.getInstrumentation().startActivitySync(intent);
    }

    @Test public void validatesAuthenticatesAndSignsOut() throws Exception {
        launch(MainActivity.class);
        snapshot("login-form");
        onView(withId(R.id.login)).perform(scrollTo(), click());
        onView(withId(R.id.identity)).check(matches(hasErrorText("Enter your username or email.")));
        onView(withId(R.id.identity)).perform(scrollTo(), replaceText("learner"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(scrollTo(), click());
        onView(withId(R.id.password)).check(matches(hasErrorText("Enter your password.")));
        onView(withId(R.id.password)).perform(scrollTo(), replaceText("wrong"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(scrollTo(), click());
        onView(withId(R.id.feedback)).check(matches(withText(R.string.invalid)));
        onView(withId(R.id.password)).perform(scrollTo(), replaceText("Learn@123"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(scrollTo(), click());
        onView(withId(R.id.logout)).check(matches(isDisplayed()));
        snapshot("login-success");
        onView(withId(R.id.logout)).perform(click());
        onView(withId(R.id.identity)).check(matches(withText("")));
        onView(withId(R.id.password)).check(matches(withText("")));
        onView(withId(R.id.identity)).perform(replaceText("learner@example.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("Learn@123"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(scrollTo(), click());
        onView(withId(R.id.logout)).check(matches(isDisplayed()));
    }

}
