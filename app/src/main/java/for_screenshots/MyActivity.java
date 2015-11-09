package for_screenshots;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.tomatron.example.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MyActivity extends RoboActivity {

    @InjectView(R.id.main_activity_hello_world_text)
    private TextView mHelloWorldText;

    @InjectView(R.id.main_activity_button)
    private Button mMyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // At this point, injection has occurred and the views are usable.
        mHelloWorldText.setText("Hello again!");
    }
}

