package raj.com.multitask;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import raj.com.multitask.base.BaseActivity;
import raj.com.multitask.di.component.DaggerLessonComponent;
import raj.com.multitask.di.module.LessonModule;
import raj.com.multitask.mvp.model.Lesson;
import raj.com.multitask.mvp.presenter.LessonPresenter;
import raj.com.multitask.mvp.view.MainView;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.tv_lesson_type)protected TextView mTvLessonType;
    @BindView(R.id.tv_conceptName)protected TextView mTvConceptName;
    @BindView(R.id.tv_targetScript)protected TextView mTargetScript;
    @BindView(R.id.tv_pronunciation)protected TextView mTvPronunciation;
    @BindView(R.id.btn_record)protected ImageButton mIbRecord;
    @BindView(R.id.btn_next)protected ImageButton mIbNext;
    @BindView(R.id.play)protected Button mBtnPlay;
    @BindView(R.id.ll)protected LinearLayout mLLRecord;
    int i=0;
    List<Lesson> lessons;
    MediaPlayer player;
    private String mFileName = null;
    File sub;
    MediaRecorder mRecorder;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Inject
    protected LessonPresenter mPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mPresenter.getLesson();
        sub = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Samarthanam");
        if (!sub.exists())
            sub.mkdirs();

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerLessonComponent.builder()
                .applicationComponent(getApplicationComponent())
                .lessonModule(new LessonModule(this))
                .build().inject(this);
    }

    @Override
    public void onLessonLoaded(List<Lesson> lessons) {
        this.lessons=new ArrayList<>(lessons);


        mTvLessonType.setText(lessons.get(0).getType());
        mTvConceptName.setText(lessons.get(0).getConceptName());
        mTargetScript.setText(lessons.get(0).getTargetScript());
        mTvPronunciation.setText(lessons.get(0).getPronunciation());
        i++;

    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.play)
    public void play(View view){
       PlayAudio playAudio=new PlayAudio();
       playAudio.execute(lessons.get(i).getAudio_url());
    }
    @OnTouch(R.id.btn_record)
    public boolean record(View view, MotionEvent event){
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            //Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();

            startRecording();

        }
        return false;
    }

    @OnClick(R.id.btn_next)
    public void next(View view){
        if (i<lessons.size()){
            if (lessons.get(i).getType().equals("learn")){
                mLLRecord.setVisibility(View.GONE);
                mBtnPlay.setVisibility(View.VISIBLE);
            }else {
                mBtnPlay.setVisibility(View.GONE);
                mLLRecord.setVisibility(View.VISIBLE);
            }
            mTvLessonType.setText(lessons.get(i).getType());
            mTvConceptName.setText(lessons.get(i).getConceptName());
            mTargetScript.setText(lessons.get(i).getTargetScript());
            mTvPronunciation.setText(lessons.get(i).getPronunciation());
            i++;
        }
    }

    public class PlayAudio extends AsyncTask<String,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }

        @Override
        protected Void doInBackground(String... strings) {


            try {
                player.setDataSource(strings[0]);
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            player.start();
        }
    }

    public void startRecording(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say!");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Speech is not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    txtSpeechInput.setText(result.get(0));

                    Log.d("string",result.get(0));

                    if (result.get(0).startsWith(lessons.get(i).getPronunciation())){
                        Toast.makeText(this,"Matched",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"Not Matched",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }

        }
    }


}
