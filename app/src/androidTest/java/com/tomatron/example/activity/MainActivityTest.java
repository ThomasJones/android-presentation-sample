package com.tomatron.example.activity;

import android.app.Activity;
import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Toast;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.tomatron.example.R;
import com.tomatron.example.controller.MyViewController;
import com.tomatron.example.util.MyScheduler;
import com.tomatron.example.util.MyToast;
import com.tomatron.example.util.RxConfirmDialog;


import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import roboguice.RoboGuice;
import rx.Observable;
import rx.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Integration / UI test on the main activity.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @Mock
    private MyViewController mMockViewController;

    @Mock
    private RxConfirmDialog mMockConfirmDialog;

    @Mock
    private MyScheduler mMockScheduler;

    @Mock
    private MyToast mMockToast;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        //MockitoAnnotations.initMocks(this);       // Getting a dexing issue when using annotations...
        mMockViewController = mock(MyViewController.class);
        mMockConfirmDialog = mock(RxConfirmDialog.class);
        mMockScheduler = mock(MyScheduler.class);
        mMockToast = mock(MyToast.class);

        // Override the RoboGuice injector with the mocks
        Application application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
        RoboGuice.overrideApplicationInjector(application, new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(MyViewController.class).toInstance(mMockViewController);
                binder.bind(RxConfirmDialog.class).toInstance(mMockConfirmDialog);
                binder.bind(MyScheduler.class).toInstance(mMockScheduler);
                binder.bind(MyToast.class).toInstance(mMockToast);
            }
        });

        when(mMockScheduler.mainThread()).thenReturn(Schedulers.immediate());
    }

    public void testHelloWorldViews() {
        // WHEN the activity is started
        getActivity();

        // THEN the text has the expected text
        onView(withText(R.string.hello_world)).check(matches(isDisplayed()));
        onView(withId(R.id.main_activity_hello_world_text)).check(matches(withText(R.string.hello_world)));
    }

    public void testClickDoesSomething() {
        // GIVEN the activity is started
        getActivity();

        // GIVEN the dialog responds with a positive answer
        when(mMockConfirmDialog.showDialog(any(Activity.class))).thenReturn(Observable.just(true));

        // WHEN the button is clicked to open the dialog (and the dialog response is positive)
        onView(withId(R.id.main_activity_button)).perform(click());

        // THEN the toast said 'Dialog confirmed!'
        verify(mMockToast).showToast("Dialog confirmed!", Toast.LENGTH_SHORT);

        // THEN the hello world text was changed
        //onView(withId(R.id.main_activity_hello_world_text)).check(matches(withText("Clicked!")));
    }
}