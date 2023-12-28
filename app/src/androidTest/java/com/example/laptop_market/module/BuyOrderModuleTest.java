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
import com.example.laptop_market.view.activities.LoginActivity;
import com.example.laptop_market.view.activities.MainActivity;
import com.example.laptop_market.view.fragments.BuyFragment;
import com.example.laptop_market.view.fragments.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BuyOrderModuleTest {
    private LoginActivity loginActivity;
    @BeforeClass
    public static void setUpBeforeClass() throws InterruptedException {

    }
    @Before
    public void setUp() throws InterruptedException {
        ActivityScenario<LoginActivity> loginActivityActivityScenario = ActivityScenario.launch(LoginActivity.class);
        loginActivityActivityScenario.onActivity(activity -> {
            loginActivity = activity;
        });
        ActivityScenario.launch(MainActivity.class);

    }
    @Test
    public void navigateToCancelBuyOrderPage() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890").addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
                    // Replace the existing fragment with the new instance
                };
                FragmentScenario<LoginFragment> fragmentScenario = FragmentScenario.launchInContainer(LoginFragment.class);
                fragmentScenario.onFragment(loginFragment -> {
                    loginFragment.setLoginActivity(loginActivity);
                });
                onView(withId(R.id.txtEmailLogin)).perform(ViewActions.typeText("nguyen.phucbinh445@gmail.com"));                            // Click vào nút đăng nhập
                onView(withId(R.id.txtPasswordLogin)).perform(ViewActions.typeText("1234567890"));                                           onView(withId(R.id.bttLogin)).perform(ViewActions.click());
                // Kiểm tra chuyển sang HomeActivity
                onView(withId(R.id.bttLogin)).perform(ViewActions.click());
                ActivityScenario<MainActivity> mainActivityActivityScenario = ActivityScenario.launch(MainActivity.class);
                // Swipe left to navigate to the Cancel Buy Order page
                onView( withId(R.id.buy)).perform(ViewActions.click());
                FragmentScenario.launchInContainer(BuyFragment.class);
                onView(withId(R.id.swipeRefreshLayoutBuyProcessing)).check(matches(isDisplayed()));
                // Check if the correct fragment is displayed
            }
        });


    }

    @Test
    public void navigateToDeliveringBuyOrderPage() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890").addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                // Swipe right to navigate to the Delivering Buy Order page
                Espresso.onView(ViewMatchers.withId(R.id.viewPagerBuy))
                        .perform(ViewActions.swipeLeft());
                onView(withId(R.id.swipeRefreshLayoutBuyProcessing)).check(matches(isDisplayed()));
            }});
    }

    @Test
    public void navigateToFinishBuyOrderPage() {
        // Swipe right to navigate to the Finish Buy Order page
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890").addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                Espresso.onView(ViewMatchers.withId(R.id.viewPagerBuy))
                        .perform(ViewActions.swipeLeft());

                // Swipe right again to navigate to the Finish Buy Order page
                Espresso.onView(ViewMatchers.withId(R.id.viewPagerBuy))
                        .perform(ViewActions.swipeLeft());
            }});


    }

    @Test
    public void navigateToProcessingBuyOrderPage() {
        // Swipe right to navigate to the Processing Buy Order page
        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com","1234567890").addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                onView(withId(R.id.swipeRefreshLayoutBuyDelivering)).check(matches(isDisplayed()));
            }});

    }
}
