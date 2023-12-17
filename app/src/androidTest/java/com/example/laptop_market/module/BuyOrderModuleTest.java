package com.example.laptop_market.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.laptop_market.R;
import com.example.laptop_market.view.activities.MainActivity;
import com.example.laptop_market.view.fragments.BuyFragment;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BuyOrderModuleTest {
    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {

    }
    @Before
    public void setUp() throws InterruptedException {
        ActivityScenario<MainActivity> loginActivityActivityScenario = ActivityScenario.launch(MainActivity.class);
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890");
        Thread.sleep(5000);
    }
    @Test
    public void navigateToCancelBuyOrderPage() {
        // Swipe left to navigate to the Cancel Buy Order page
        onView( withId(R.id.buy)).perform(ViewActions.click());
        //FragmentScenario.launchInContainer(BuyFragment.class);
        onView(withId(R.id.swipeRefreshLayoutBuyProcessing)).check(matches(isDisplayed()));
        // Check if the correct fragment is displayed

    }

    @Test
    public void navigateToDeliveringBuyOrderPage() {
        // Swipe right to navigate to the Delivering Buy Order page
        Espresso.onView(ViewMatchers.withId(R.id.viewPagerBuy))
                .perform(ViewActions.swipeLeft());


    }

    @Test
    public void navigateToFinishBuyOrderPage() {
        // Swipe right to navigate to the Finish Buy Order page
        Espresso.onView(ViewMatchers.withId(R.id.viewPagerBuy))
                .perform(ViewActions.swipeLeft());

        // Swipe right again to navigate to the Finish Buy Order page
        Espresso.onView(ViewMatchers.withId(R.id.viewPagerBuy))
                .perform(ViewActions.swipeLeft());


    }

    @Test
    public void navigateToProcessingBuyOrderPage() {
        // Swipe right to navigate to the Processing Buy Order page
        onView(withId(R.id.swipeRefreshLayoutBuyDelivering)).check(matches(isDisplayed()));
    }
}
