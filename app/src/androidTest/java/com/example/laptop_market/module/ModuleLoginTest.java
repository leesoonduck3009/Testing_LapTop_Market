package com.example.laptop_market.module;

import androidx.annotation.UiThread;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.laptop_market.R;
import com.example.laptop_market.view.activities.LoginActivity;
import com.example.laptop_market.view.activities.MainActivity;
import com.example.laptop_market.view.fragments.LoginFragment;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ModuleLoginTest {
    LoginActivity loginActivity;
    LoginFragment loginFragment;

    @Before
    public void setUp() {
        ActivityScenario<LoginActivity> loginActivityActivityScenario = ActivityScenario.launch(LoginActivity.class);
        loginActivityActivityScenario.onActivity(activity -> {
            loginActivity = activity;
        });
    }
    @Test
    public void moduleLogin1()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<LoginFragment> fragmentScenario = FragmentScenario.launchInContainer(LoginFragment.class);
        fragmentScenario.onFragment(loginFragment -> {
            loginFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.txtEmailLogin)).check(matches(isDisplayed()));
        onView(withId(R.id.txtPasswordLogin)).check(matches(isDisplayed()));
        onView(withId(R.id.bttLogin)).check(matches(isDisplayed()));
        onView(withId(R.id.txtForgotPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.txtSignUp)).check(matches(isDisplayed()));
    }
    @Test
    public void moduleLogin2()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<LoginFragment> fragmentScenario = FragmentScenario.launchInContainer(LoginFragment.class);
        fragmentScenario.onFragment(loginFragment -> {
            loginFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.txtEmailLogin)).check(matches(isEnabled()));
    }
    @Test
    public void moduleLogin3()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<LoginFragment> fragmentScenario = FragmentScenario.launchInContainer(LoginFragment.class);
        fragmentScenario.onFragment(loginFragment -> {
            loginFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.txtPasswordLogin)).check(matches(isEnabled()));
    }
    @Test
    public void moduleLogin4()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<LoginFragment> fragmentScenario = FragmentScenario.launchInContainer(LoginFragment.class);
        fragmentScenario.onFragment(loginFragment -> {
            this.loginFragment = loginFragment;
            loginFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.txtPasswordLogin)).perform(ViewActions.typeText("12345678"));
        onView(withId(R.id.bttLogin)).check(matches(isNotEnabled()));
        // Click vào nút đăng nhập
    }
    @Test
    public void moduleLogin5()
    {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<LoginFragment> fragmentScenario = FragmentScenario.launchInContainer(LoginFragment.class);
        fragmentScenario.onFragment(loginFragment -> {
            this.loginFragment = loginFragment;
            loginFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.txtEmailLogin)).perform(ViewActions.typeText("12345678"));
        onView(withId(R.id.bttLogin)).check(matches(isNotEnabled()));
        // Click vào nút đăng nhập
    }
    @Test
    public void moduleLogin6() throws InterruptedException {
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<LoginFragment> fragmentScenario = FragmentScenario.launchInContainer(LoginFragment.class);
        fragmentScenario.onFragment(loginFragment -> {
            this.loginFragment = loginFragment;
            loginFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.txtEmailLogin)).perform(ViewActions.typeText("nguyen.phucbinh445"));
        onView(withId(R.id.txtPasswordLogin)).perform(ViewActions.typeText("1245"));
        // Click vào nút đăng nhập
        onView(withId(R.id.bttLogin)).perform(ViewActions.click());
        Thread.sleep(1000);
        onView(ViewMatchers.withId(R.id.txtViewBug))
                .check(ViewAssertions.matches(Matchers.is(ViewMatchers.withText("Vui lòng kiểm tra lại địa chỉ email"))));
    }
    @Test
    public void moduleLogin7() throws InterruptedException {
        // Assuming you have a button with id "buttonId" in your LoginFragment
        FragmentScenario.FragmentAction<LoginFragment> fragmentAction = fragment -> {
            // Replace the existing fragment with the new instance
        };
        FragmentScenario<LoginFragment> fragmentScenario = FragmentScenario.launchInContainer(LoginFragment.class);
        fragmentScenario.onFragment(loginFragment -> {
            this.loginFragment = loginFragment;
            loginFragment.setLoginActivity(loginActivity);
        });
        onView(withId(R.id.txtEmailLogin)).perform(ViewActions.typeText("nguyen.phucbinh445@gmail.com"));                            // Click vào nút đăng nhập
        onView(withId(R.id.txtPasswordLogin)).perform(ViewActions.typeText("123456789"));                                           onView(withId(R.id.bttLogin)).perform(ViewActions.click());
        // Kiểm tra chuyển sang HomeActivity
        onView(withId(R.id.bttLogin)).perform(ViewActions.click());
        Thread.sleep(2000);

        onView(ViewMatchers.withId(R.id.txtViewBug))
                .check(ViewAssertions.matches(Matchers.is(ViewMatchers.withText("Thông tin đăng nhập không chính xác. Vui lòng kiểm tra lại!"))));
    }
    @Test
    public  void  moduleLogin8()
    {
        // Assuming you have a button with id "buttonId" in your LoginFragment
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
        onView(withId(R.id.frameHomeBase)).check(matches(isDisplayed()));
        // Add assertions or verifications based on your test case
    }

}