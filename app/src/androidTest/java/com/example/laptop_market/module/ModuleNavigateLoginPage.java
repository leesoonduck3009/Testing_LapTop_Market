package com.example.laptop_market.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;

import com.example.laptop_market.R;
import com.example.laptop_market.view.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ModuleNavigateLoginPage {
    @BeforeClass
    public static void setBeforeClass() throws InterruptedException {
//        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890");
//        Thread.sleep(5000);
    }
    @Before
    public void setUp()
    {
        ActivityScenario<MainActivity> loginActivityActivityScenario = ActivityScenario.launch(MainActivity.class);
    }
    @Test
    public void navigateToAccountPageLogin()
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890")
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        onView(withId(R.id.account)).perform(ViewActions.click());
                        onView(withId(R.id.fragmentAccount)).check(matches(isDisplayed()));
                    }});
    }
    @Test
    public void navigateToPostPageLogin()
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890")
                .addOnCompleteListener(task -> {
                    onView(withId(R.id.post)).perform(ViewActions.click());
                    onView(withId(R.id.gridRequireLoginForBuy)).check(matches(Matchers.not(isDisplayed())));
                    onView(withId(R.id.framePost)).check(matches(isDisplayed()));
                });
    }
    @Test
    public void navigateToSellOrderPageLogin()
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890")
                .addOnCompleteListener(task -> {
                    onView(withId(R.id.sell)).perform(ViewActions.click());
                    onView(withId(R.id.gridRequireLoginForBuy)).check(matches(Matchers.not(isDisplayed())));
                    onView(withId(R.id.fragmentSell)).check(matches(isDisplayed()));
                });
    }
    @Test
    public void navigateToBuyOrderPageLogin()
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890")
                .addOnCompleteListener(task -> {
        onView(withId(R.id.buy)).perform(ViewActions.click());
                    onView(withId(R.id.gridRequireLoginForBuy)).check(matches(Matchers.not(isDisplayed())));
        onView(withId(R.id.fragmentBuy)).check(matches(isDisplayed()));});
    }
}
