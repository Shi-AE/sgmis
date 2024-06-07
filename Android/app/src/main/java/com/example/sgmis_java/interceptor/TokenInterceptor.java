package com.example.sgmis_java.interceptor;

import androidx.annotation.NonNull;

import com.example.sgmis_java.utils.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * token拦截器
 */
public class TokenInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        String token = SharedPreferencesUtils.getString("token", "");

        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", token)
                .build();
        Response response = chain.proceed(request);

        String authorization = response.header("Authorization");
        if (authorization != null && !authorization.trim().isEmpty()) {
            SharedPreferencesUtils.putString("token", authorization);
        }

        return response;
    }
}
