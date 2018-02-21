package raj.com.multitask.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.io.File;

import butterknife.ButterKnife;
import raj.com.multitask.application.LessonApplication;
import raj.com.multitask.di.component.ApplicationComponent;

/**
 * Created by Raj on 20-02-2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    File sub;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        onViewReady(savedInstanceState,getIntent());
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        //to be used by child Activity

        resolveDaggerDependency();
    }

    protected void resolveDaggerDependency() {
    }


    protected void showDialog(String message){
        if (mProgressDialog==null){
            mProgressDialog=new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }

        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog(){

        if (mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }

    }

    protected ApplicationComponent getApplicationComponent(){
        return ((LessonApplication)getApplication()).getApplicationComponent();
    }
    protected abstract int getContentView();

    protected void createDirectory(){
        sub = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Multi");
        if (!sub.exists())
            sub.mkdirs();
    }
}
