package com.tomatron.example.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.tomatron.example.R;

import rx.Observable;
import rx.Subscriber;

/**
 * Android alert confirmation dialog that is converted to reactive-pattern.
 */
public class RxConfirmDialog {

    /**
     * Show a confirmation dialog and receive an Observable on a boolean of whether the dialog
     * was confirmed or canceled / dismissed.
     *
     * @param activityForDialog activity the dialog will be shown in.
     * @return an Observable on a boolean indicating if the response was negative or positive.
     */
    public Observable<Boolean> showDialog(final Activity activityForDialog) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                new AlertDialog.Builder(activityForDialog)
                        .setMessage(R.string.dialog_message)
                        .setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                subscriber.onNext(true);
                                subscriber.onCompleted();
                            }
                        })
                        .setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                subscriber.onNext(false);
                                subscriber.onCompleted();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                subscriber.onNext(false);
                                subscriber.onCompleted();
                            }
                        })
                        .show();
            }
        });
    }
}

