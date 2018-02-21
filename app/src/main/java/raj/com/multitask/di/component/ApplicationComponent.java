package raj.com.multitask.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import raj.com.multitask.application.LessonApplication;
import raj.com.multitask.di.module.ApplicationModule;
import retrofit2.Retrofit;

/**
 * Created by Raj on 20-02-2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    //void applyInjection(LessonApplication application);

    Retrofit exposeRetrofit();
    Context exposeContext();
}
