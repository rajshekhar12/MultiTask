package raj.com.multitask.mvp.model;

public class LessonResponseLesson_data {
    private String conceptName;
    private String pronunciation;
    private String audio_url;
    private String targetScript;
    private String type;

    public String getConceptName() {
        return this.conceptName;
    }

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }

    public String getPronunciation() {
        return this.pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getAudio_url() {
        return this.audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getTargetScript() {
        return this.targetScript;
    }

    public void setTargetScript(String targetScript) {
        this.targetScript = targetScript;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
