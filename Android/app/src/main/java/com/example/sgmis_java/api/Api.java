package com.example.sgmis_java.api;

import android.content.Context;
import android.util.Log;

import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.interceptor.TokenInterceptor;
import com.example.sgmis_java.utils.MessageUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final String TAG = "Api";

    private static final OkHttpClient httpClient;

    private static final Retrofit retrofit;

    public static final String baseUrl = "http://shiyixiang.cn/";

    public static final String api = "api/";

    public static final String logo = "logo/";

    public static final String pigeon = "pigeon/";

    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMdd = "yyyy-MM-dd";


    static {

        httpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(3000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();


        retrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl + api)
                .client(httpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                                        context.serialize(src.format(DateTimeFormatter.ofPattern(yyyyMMddHHmmss))))
                                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                                        LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern(yyyyMMddHHmmss)))
                                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                                        context.serialize(src.format(DateTimeFormatter.ofPattern(yyyyMMdd))))
                                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                                        LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern(yyyyMMdd)))
                                .create()
                ))
                .build();
    }

    public static <T> T creat(Class<T> service) {
        return retrofit.create(service);
    }

    public static <T> void execute(Context context, Observable<Result<T>> call, ResCallback<T> callback) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<T>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull Result<T> result) {
                        Integer code = result.getCode();
                        if (code > 204) {
                            String message = result.getMessage();
                            Log.e(TAG, "code: " + code + " msg: " + message);
                            MessageUtils.showToast(context, "接口调用失败 code: " + code + " msg: " + message);
                            return;
                        }
                        callback.success(result.getData(), result.getMessage());
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e(TAG, "接口调用失败", e);
                        MessageUtils.showToast(context, "接口调用失败");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
