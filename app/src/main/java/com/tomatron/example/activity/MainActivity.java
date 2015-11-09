package com.tomatron.example.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tomatron.example.R;
import com.tomatron.example.controller.MyViewController;
import com.tomatron.example.controller.ViewImplementer;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity implements ViewImplementer {

    //region INJECTED CLASSES ----------------------------------------------------------------------

    @Inject
    private MyViewController mViewController;

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
    //endregion

    //region OBSERVERS -----------------------------------------------------------------------------
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion
}
