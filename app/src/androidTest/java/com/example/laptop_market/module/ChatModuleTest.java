package com.example.laptop_market.module;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.util.Log;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.laptop_market.R;
import com.example.laptop_market.view.activities.ConversationListActivity;
import com.example.laptop_market.view.activities.MainActivity;
import com.example.laptop_market.view.fragments.HomeFragment;
import com.example.laptop_market.view.fragments.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ChatModuleTest {
    @Before
    public void setUp()
    {
        ActivityScenario.launch(MainActivity.class);
    }
    @Test
    public void testClickChatButton()
    {
        FragmentScenario.launchInContainer(HomeFragment.class);
        onView(withId(R.id.chatMessageBtt)).perform(ViewActions.click());
        onView(ViewMatchers.withText("Bạn cần phải đăng nhập để thực hiện chức năng này!"))
                .check(matches(isDisplayed()));
        //onView(withId(R.id.txtViewBug)).check(ViewAssertions.matches(Matchers.is(ViewMatchers.withText("Bạn cần phải đăng nhập để thực hiện chức năng này!"))));
    }
    @Test
    public void testClickChatButtonWithLogin() {
        // Tạo một CountingIdlingResource để đếm số tác vụ không đồng bộ
        CountingIdlingResource countingIdlingResource = new CountingIdlingResource("FirebaseLogin");

        // Đăng ký CountingIdlingResource với IdlingRegistry
        IdlingRegistry.getInstance().register(countingIdlingResource);

        FirebaseAuth.getInstance().signInWithEmailAndPassword("nguyen.phucbinh445@gmail.com", "1234567890")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Giảm giá trị CountingIdlingResource khi tác vụ hoàn thành
                        Log.d("Hello","Success");
                        countingIdlingResource.decrement();
                    }
                });
        // Đăng ký một IdlingResource để đợi tất cả các tác vụ không đồng bộ
        // Đợi cho tất cả các tác vụ không đồng bộ hoàn thành
        Espresso.onView(ViewMatchers.withId(R.id.chatMessageBtt)).perform(ViewActions.click());
        onView(ViewMatchers.withText("Bạn cần phải đăng nhập để thực hiện chức năng này!"))
                .check(matches(isDisplayed()));
        // Chờ đợi Intent mở ConversationListActivity (hoặc Activity tương tự)
        //Intents.intended(IntentMatchers.hasComponent(ConversationListActivity.class.getName()));
        // Kiểm tra các điều kiện khác tại đây
        Log.d("Hello","End");
        // Hủy đăng ký IdlingResource
        IdlingRegistry.getInstance().unregister(countingIdlingResource);
    }

}

