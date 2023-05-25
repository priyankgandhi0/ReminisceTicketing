package com.example.reminisce_ticketing.apiservice;

import android.content.Context;

import com.example.reminisce_ticketing.utils.SharedPref;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASEURL = "https://reminisce.alitainfotech.in/backend/public/";

    public static Retrofit getClient(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor((Interceptor) chain -> {
            Request original = chain.request();
            Request request;
            String userToken = SharedPref.getUserToken(SharedPref.IsUserToken, context);
            if (userToken!=null){
                request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("App-Track-Version", "v1")
                        .header("App-Device-Type", "iOS")
                        .header("App-Store-Version", "1.1")
                        .header("App-Store-Build-Number", "1.1")
                        .header("Authorization", "Bearer " + userToken)
                        .method(original.method(), original.body())
                        .build();

            }else {
                request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("App-Track-Version", "v1")
                        .header("App-Device-Type", "iOS")
                        .header("App-Store-Version", "1.1")
                        .header("App-Store-Build-Number", "1.1")
                        .method(original.method(), original.body())
                        .build();

            }
            return chain.proceed(request);

        });
        OkHttpClient client = httpClient.build();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit
                .Builder()
                .baseUrl(BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}