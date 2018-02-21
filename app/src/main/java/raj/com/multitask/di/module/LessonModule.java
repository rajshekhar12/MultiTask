package raj.com.multitask.di.module;

import dagger.Module;
import dagger.Provides;
import raj.com.multitask.api.ApiService;
import raj.com.multitask.di.scope.PerActivity;
import raj.com.multitask.mvp.view.MainView;
import retrofit2.Retrofit;

/**
 * Created by Raj on 20-02-2018.
 */

@Module
public class LessonModule {

    private MainView mView;

    public LessonModule(MainView mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView(){
        return mView;
    }
}
