package com.example.laptop_market.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.laptop_market.R;
import com.example.laptop_market.view.activities.LoginActivity;
import com.example.laptop_market.view.fragments.LoginFragment;
import com.example.laptop_market.view.fragments.SignUpFragment;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ModuleSignupTest {
    private LoginActivity loginActivity;
    private SignUpFragment signUpFragment;
    @Before
    public void setUp()
    {
        ActivityScenario<LoginActivity> loginActivityActivityScenario = ActivityScenario.launch(LoginActivity.class);
        loginActivityActivityScenario.onActivity(activity -> {
            loginActivity = activity;
        });

    }
    @Test
    public void allBlank()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<SignUpFragment> fragmentScenario = FragmentScenario.launchInContainer(SignUpFragment.class);
        fragmentScenario.onFragment(signUpFragment -> {
            signUpFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.btnSignUp)).check(matches(isNotEnabled()));
    }
    @Test
    public void EmailBlank()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<SignUpFragment> fragmentScenario = FragmentScenario.launchInContainer(SignUpFragment.class);
        fragmentScenario.onFragment(signUpFragment -> {
            signUpFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.editTextName)).perform(ViewActions.typeText("Phuc Binhf"));
        onView(withId(R.id.editTextPassword)).perform(ViewActions.typeText("1234567890"));
        onView(withId(R.id.btnSignUp)).check(matches(isNotEnabled()));

    }
    @Test
    public void editTextNameBlank()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<SignUpFragment> fragmentScenario = FragmentScenario.launchInContainer(SignUpFragment.class);
        fragmentScenario.onFragment(signUpFragment -> {
            signUpFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.editTextEmail)).perform(ViewActions.typeText("nguyen.phucbinh445@gmail.com"));
        onView(withId(R.id.editTextPassword)).perform(ViewActions.typeText("1234567890"));
        onView(withId(R.id.btnSignUp)).check(matches(isNotEnabled()));
    }
    @Test
    public void editPasswordBlank()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<SignUpFragment> fragmentScenario = FragmentScenario.launchInContainer(SignUpFragment.class);
        fragmentScenario.onFragment(signUpFragment -> {
            signUpFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.editTextEmail)).perform(ViewActions.typeText("nguyen.phucbinh445@gmail.com"));
        onView(withId(R.id.editTextName)).perform(ViewActions.typeText("Phuc Binhf"));
        onView(withId(R.id.btnSignUp)).check(matches(isNotEnabled()));
    }
    @Test
    public void editPasswordUnder6Word()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<SignUpFragment> fragmentScenario = FragmentScenario.launchInContainer(SignUpFragment.class);
        fragmentScenario.onFragment(signUpFragment -> {
            signUpFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.editTextEmail)).perform(ViewActions.typeText("nguyen.phucbinh445@gmail.com"));
        onView(withId(R.id.editTextName)).perform(ViewActions.typeText("Phuc Binhf"));
        onView(withId(R.id.editTextPassword)).perform(ViewActions.typeText("12345"));
        onView(withId(R.id.btnSignUp)).check(matches(isNotEnabled()));
    }
    @Test
    public void emailAlreadyExisted() throws InterruptedException {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<SignUpFragment> fragmentScenario = FragmentScenario.launchInContainer(SignUpFragment.class);
        fragmentScenario.onFragment(signUpFragment -> {
            signUpFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.editTextEmail)).perform(ViewActions.typeText("nguyen.phucbinh445@gmail.com"));
        onView(withId(R.id.editTextName)).perform(ViewActions.typeText("Phuc Binhf"));
        onView(withId(R.id.editTextPassword)).perform(ViewActions.typeText("1234568"));
        onView(withId(R.id.btnSignUp)).perform(ViewActions.click());
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.txtViewBug))
                .check(ViewAssertions.matches(Matchers.is(ViewMatchers.withText("Đã tồn tại tài khoản này"))));
    }

}
