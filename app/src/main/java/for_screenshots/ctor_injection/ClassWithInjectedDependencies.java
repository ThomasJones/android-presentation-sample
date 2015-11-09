package for_screenshots.ctor_injection;

import com.tomatron.example.service.MyDAO;
import com.tomatron.example.util.MyAPIErrorHandler;
import com.tomatron.example.util.MyScheduler;

import javax.inject.Inject;

/**
 * Simple example of a class that uses constructor injection.
 */
public class ClassWithInjectedDependencies {

    private MyDAO mMyDAO;
    private MyScheduler mMyScheduler;
    private MyAPIErrorHandler mErrorHandler;

    @Inject
    public ClassWithInjectedDependencies(MyDAO dao,
                                         MyScheduler scheduler,
                                         MyAPIErrorHandler errorHandler) {
        mMyDAO = dao;
        mMyScheduler = scheduler;
        mErrorHandler = errorHandler;
    }

    //...

    private void blah() {
        mMyDAO.getData("");
        mMyScheduler.mainThread();
        mErrorHandler.handle(null);
    }
}


