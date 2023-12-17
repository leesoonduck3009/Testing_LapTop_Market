package com.example.laptop_market.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.laptop_market.R;
import com.example.laptop_market.view.activities.LoginActivity;
import com.example.laptop_market.view.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ModuleNavigatePageTest {
    private MainActivity mainActivity;

    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890");
        Thread.sleep(3000);
    }
    @Before
    public void setUp()
    {
        ActivityScenario<MainActivity> loginActivityActivityScenario = ActivityScenario.launch(MainActivity.class);
    }
    @Test
    public void navigateToHomePage()
    {
        onView( withId(R.id.home)).perform(ViewActions.click());
        onView(withId(R.id.linearLayoutFragmentHome)).check(matches(isDisplayed()));
    }
    @Test
    public void navigateToSellOrderPageNotLogin()
    {
        FirebaseAuth.getInstance().signOut();
        onView( withId(R.id.sell)).perform(ViewActions.click());
        onView(withId(R.id.fragmentSell)).check(matches(isDisplayed()));

    }
    @Test
    public void navigateToBuyPageNotLogin()
    {
        FirebaseAuth.getInstance().signOut();
        onView( withId(R.id.buy)).perform(ViewActions.click());
        onView(withId(R.id.gridRequireLoginForBuy)).check(matches(Matchers.is(isDisplayed())));


    }
    @Test
    public void navigateToPostPageNotLogin()
    {
        FirebaseAuth.getInstance().signOut();
        onView( withId(R.id.post)).perform(ViewActions.click());
        onView(withId(R.id.framePost)).check(matches(isDisplayed()));
        onView(withId(R.id.gridRequireLoginForBuy)).check(matches(isDisplayed()));

    }
    @Test
    public void navigateToAccountPageNotLogin()
    {
        FirebaseAuth.getInstance().signOut();
        onView(withId(R.id.account)).perform(ViewActions.click());
        onView(withId(R.id.fragmentAccount)).check(matches(isDisplayed()));
    }

}
