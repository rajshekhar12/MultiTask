package raj.com.multitask.mvp.view;

import java.util.List;

import raj.com.multitask.mvp.model.Lesson;

/**
 * Created by Raj on 20-02-2018.
 */

public interface MainView extends BaseView {
    void onLessonLoaded(List<Lesson> lessons);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String s);


}
