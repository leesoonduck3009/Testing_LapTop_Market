package com.example.laptop_market.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;

import com.example.laptop_market.R;
import com.example.laptop_market.view.activities.LoginActivity;
import com.example.laptop_market.view.activities.MainActivity;
import com.example.laptop_market.view.fragments.HomeBaseFragment;
import com.example.laptop_market.view.fragments.HomeFragment;
import com.example.laptop_market.view.fragments.LoginFragment;
import com.example.laptop_market.view.fragments.SearchFragment;
import com.example.laptop_market.view.fragments.SearchResultFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SearchModule {
    private HomeBaseFragment homeBaseFragment;
    @Before
    public void setUp()
    {
        ActivityScenario.launch(MainActivity.class);
        FragmentScenario.launchInContainer(HomeBaseFragment.class).onFragment(homeBaseFragment1 -> {
            this.homeBaseFragment = homeBaseFragment1;
        });
        FragmentScenario.launchInContainer(HomeFragment.class).onFragment(homeFragment -> {
            homeFragment.setHomeBaseFragment(this.homeBaseFragment);
        });
    }
    @Test
    public void TestSearchButtonClickTest()
    {

        onView(withId(R.id.edtTextHome)).perform(ViewActions.click());
        FragmentScenario.launchInContainer(SearchFragment.class).onFragment(searchFragment -> {
            searchFragment.setHomeBaseFragment(this.homeBaseFragment);
        });
        onView(withId(R.id.fragment_search)).check(matches(isDisplayed()));
    }
    @Test
    public void TestSearchTextTest()
    {
        onView(withId(R.id.edtTextHome)).perform(ViewActions.click());
        FragmentScenario.launchInContainer(SearchFragment.class).onFragment(searchFragment -> {
            searchFragment.setHomeBaseFragment(this.homeBaseFragment);
        });
        FragmentScenario.launchInContainer(SearchResultFragment.class);
        onView(withId(R.id.edtTextSearch)).perform(ViewActions.typeText("dell"));
        onView(withId(R.id.fragment_search_result)).check(matches(isDisplayed()));
        //onView(withId(R))
    }
    @Test
    public void TestNoneSearch()
    {
        onView(withId(R.id.edtTextHome)).perform(ViewActions.click());
        FragmentScenario.launchInContainer(SearchFragment.class).onFragment(searchFragment -> {
            searchFragment.setHomeBaseFragment(this.homeBaseFragment);
        });
        FragmentScenario.launchInContainer(SearchResultFragment.class);
        onView(withId(R.id.edtTextSearch)).perform(ViewActions.typeText(""));
        onView(withId(R.id.fragment_search_result)).check(matches(isDisplayed()));
    }
}
