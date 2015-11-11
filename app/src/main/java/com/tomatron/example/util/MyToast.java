package com.tomatron.example.util;

import android.content.Context;
import android.widget.Toast;

import com.google.inject.Inject;

/**
 * Example of wrapping Android-specific static methods around an injectable wrapper.
 */
public class MyToast {

    //region INJECTED CLASSES ----------------------------------------------------------------------

    private Context mContext;

    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    @Inject
    public MyToast(Context context) {
        mContext = context;
    }

    //endregion

    //region PUBLIC CLASS METHODS ------------------------------------------------------------------

    /**
     * Creates an Android system toast with the given message and length.
     *
     * @param message a message to display in the toast.
     * @param length  the length, either Toast.LENGTH_SHORT or Toast.LENGTH_LONG
     */
    public void showToast(String message, int length) {
        Toast.makeText(mContext, message, length).show();
    }

    //endregion
}
