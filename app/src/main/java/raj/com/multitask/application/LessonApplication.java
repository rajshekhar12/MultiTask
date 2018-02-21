package raj.com.multitask.application;

import android.app.Application;

import raj.com.multitask.di.component.ApplicationComponent;
import raj.com.multitask.di.component.DaggerApplicationComponent;
import raj.com.multitask.di.module.ApplicationModule;

/**
 * Created by Raj on 20-02-2018.
 */

public class LessonApplication extends Application {
    private  ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this,"http://www.akshaycrt2k.com"))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
