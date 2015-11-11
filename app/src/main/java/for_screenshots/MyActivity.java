package for_screenshots;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.tomatron.example.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import rx.Observable;
import rx.Observer;

@ContentView(R.layout.activity_main)
public class MyActivity extends RoboActivity {

    // ...

    private Observable<Boolean> showDialog(final Activity activityForDialog) {
        return Observable.just(false);
    }

    public void showConfirmDialog() {
        showDialog(this).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Boolean isConfirmed) {
                // Handle the confirmation or cancellation based on the boolean.
            }
        });
    }

    // ...




    /**
     * Show a confirmation dialog and handle the results.
     */
    private void showConfirmDialog_Old() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Handle confirmation is successful
                    }
                })
                .setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Handle confirmation is not successful
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        // Handle cancel
                    }
                }).show();
    }

    // ...




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

