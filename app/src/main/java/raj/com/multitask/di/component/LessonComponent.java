package raj.com.multitask.di.component;

import dagger.Component;
import raj.com.multitask.MainActivity;
import raj.com.multitask.di.module.LessonModule;
import raj.com.multitask.di.scope.PerActivity;

/**
 * Created by Raj on 20-02-2018.
 */
@PerActivity
@Component(modules = LessonModule.class,dependencies = ApplicationComponent.class)
public interface LessonComponent {

    void inject(MainActivity activity);
}
