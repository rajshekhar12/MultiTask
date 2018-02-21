package raj.com.multitask.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import raj.com.multitask.mvp.model.Lesson;
import raj.com.multitask.mvp.model.LessonResponse;
import raj.com.multitask.mvp.model.LessonResponseLesson_data;

/**
 * Created by Raj on 20-02-2018.
 */

public class LessonMapper {

    @Inject
    public LessonMapper() {
    }

    public List<Lesson> mapLesson(LessonResponse response){
        List<Lesson> lessonList =new ArrayList<>();

        if (response !=null){
            LessonResponseLesson_data[] responseLesson = response.getLesson_data();
            if (responseLesson!=null){
                for (LessonResponseLesson_data lesson:responseLesson) {
                    Lesson myLesson=new Lesson();
                    myLesson.setType(lesson.getType());
                    myLesson.setConceptName(lesson.getConceptName());
                    myLesson.setPronunciation(lesson.getPronunciation());
                    myLesson.setTargetScript(lesson.getTargetScript());
                    myLesson.setAudio_url(lesson.getAudio_url());
                    lessonList.add(myLesson);
                    
                }
            }

        }


        return lessonList;
    }
}
