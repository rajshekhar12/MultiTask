package raj.com.multitask.mvp.model;

public class LessonResponse {
    private LessonResponseLesson_data[] lesson_data;

    public LessonResponseLesson_data[] getLesson_data() {
        return this.lesson_data;
    }

    public void setLesson_data(LessonResponseLesson_data[] lesson_data) {
        this.lesson_data = lesson_data;
    }
}
