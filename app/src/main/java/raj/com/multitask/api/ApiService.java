package raj.com.multitask.api;


import raj.com.multitask.mvp.model.LessonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Raj on 20-02-2018.
 */

public interface ApiService {

    @GET("/getLessonData.php")
    Observable<LessonResponse> getLesson();

    Call<LessonResponse> getTheLesson();
}
