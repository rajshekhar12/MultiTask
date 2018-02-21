package raj.com.multitask.mvp.presenter;



import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;
import raj.com.multitask.api.ApiService;
import raj.com.multitask.base.BasePresenter;
import raj.com.multitask.mapper.LessonMapper;
import raj.com.multitask.mvp.model.Lesson;
import raj.com.multitask.mvp.model.LessonResponse;
import raj.com.multitask.mvp.view.MainView;
import rx.Observable;
import rx.Observer;

/**
 * Created by Raj on 20-02-2018.
 */

public class LessonPresenter extends BasePresenter<MainView> implements Observer<LessonResponse> {

    @Inject protected ApiService mApiService;
    @Inject protected LessonMapper mLessonMapper;

    @Inject
    public LessonPresenter() {
    }

    public void getLesson(){

        getView().onShowDialog("loading...");

        Observable<LessonResponse> lessonResponseObservable=mApiService.getLesson();
        subscribe(lessonResponseObservable,this);

    }



    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Lesson Downloaded!");

    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error: "+e.getMessage());

    }

    @Override
    public void onNext(LessonResponse lessonResponse) {
        List<Lesson> lessons = mLessonMapper.mapLesson(lessonResponse);
        getView().onLessonLoaded(lessons);

    }


}
