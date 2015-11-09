package for_screenshots.member_injection;

import android.content.Context;

import com.tomatron.example.service.MyDAO;
import com.tomatron.example.util.MyAPIErrorHandler;
import com.tomatron.example.util.MyScheduler;

import javax.inject.Inject;

import roboguice.RoboGuice;

/**
 * Simple example of a class that uses member injection.
 */
public class ClassWithInjectedDependencies {

    @Inject
    private MyDAO mMyDAO;

    @Inject
    private MyScheduler mMyScheduler;

    @Inject
    private MyAPIErrorHandler mErrorHandler;

    /**
     * Initialization step that requires the Android context.
     */
    public void initialize(Context context) {
        RoboGuice.injectMembers(context, this);
    }

    //...


    private void forScreenshot_removeLintWarnings() {
        mMyDAO = null;
        mMyScheduler = null;
        mErrorHandler = null;
        this.initialize(null);
    }
    private void forScreenshot_removeMoreLintWarnings() {
        mMyDAO.getData("");
        mMyScheduler.mainThread();
        mErrorHandler.handle(null);
    }
}
