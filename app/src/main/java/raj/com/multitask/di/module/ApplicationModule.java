package raj.com.multitask.di.module;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raj on 20-02-2018.
 */

@Module
public class ApplicationModule {

    private String mBaseUrl;
    private Context mContext;

    public ApplicationModule(Context mContext,String mBaseUrl ) {
        this.mBaseUrl = mBaseUrl;
        this.mContext = mContext;
    }

    @Singleton
    @Provides
    GsonConverterFactory providerGsonConverterFactory(){
        GsonConverterFactory factory=GsonConverterFactory.create();
        return factory;
    }

    @Singleton
    @Provides
    @Named("ok-1")
    OkHttpClient providerOkHttpClient1(){
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("ok-2")
    OkHttpClient providerOkHttpClient2(){
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("ok-1") OkHttpClient client, GsonConverterFactory converterFactory, RxJavaCallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();

    }

    @Singleton
    @Provides
    Context provideContext(){
        return mContext;
    }
}
