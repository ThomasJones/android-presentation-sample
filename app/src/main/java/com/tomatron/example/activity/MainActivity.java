package com.tomatron.example.activity;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tomatron.example.R;
import com.tomatron.example.controller.MyViewController;
import com.tomatron.example.controller.ViewImplementer;
import com.tomatron.example.util.MyScheduler;
import com.tomatron.example.util.MyToast;
import com.tomatron.example.util.RxConfirmDialog;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import rx.Observer;

/**
 * Simple activity that injects dependencies which can be mocked in tests and interactions verified.
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity implements ViewImplementer {

    //region INJECTED CLASSES ----------------------------------------------------------------------

    @Inject
    private MyViewController mViewController;

    @Inject
    private RxConfirmDialog mConfirmDialog;

    @Inject
    private MyScheduler mMyScheduler;

    @Inject
    private MyToast mMyToast;

    //endregion

    //region INJECTED VIEWS ------------------------------------------------------------------------

    @InjectView(R.id.main_activity_hello_world_text)
    private TextView mHelloWorldText;

    @InjectView(R.id.main_activity_button)
    private Button mMyButton;

    //endregion

    //region STATIC LOCAL CONSTANTS ----------------------------------------------------------------
    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion

    //region LIFECYCLE METHODS ---------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewController.onCreated(this);

        mMyButton.setOnClickListener(new ButtonClickListener());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mViewController.onDestroyed();
    }

    //endregion

    //region LISTENERS -----------------------------------------------------------------------------

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            invokeClick();
        }
    }

    //endregion

    //region EVENTS --------------------------------------------------------------------------------
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    //endregion

    //region PUBLIC METHODS ------------------------------------------------------------------------

    @Override
    public void setText(String message) {
        mHelloWorldText.setText(message);
    }

    //endregion

    //region PRIVATE METHODS -----------------------------------------------------------------------

    /**
     * Implementation for the click, visible for testing to avoid interacting with the UI.
     */
    @VisibleForTesting
    void invokeClick() {
        mConfirmDialog.showDialog(MainActivity.this)
                .observeOn(mMyScheduler.mainThread())
                .subscribe(new ConfirmDialogSubscriber());
    }

    //endregion

    //region OBSERVERS -----------------------------------------------------------------------------

    /**
     * Observer on the confirmation dialog that shows a toast if confirmation is successful.
     */
    private class ConfirmDialogSubscriber implements Observer<Boolean> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Boolean isConfirmed) {
            if (isConfirmed) {
                mMyToast.showToast("Dialog confirmed!", Toast.LENGTH_SHORT);
            }
        }
    }

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion
}
