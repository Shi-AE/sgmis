package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.pojo.Xtspz;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HerdbookService {

    @GET("xtspz")
    Observable<Result<Xtspz>> getInfo();

    @POST("xtspz")
    Observable<Result<Object>> postInfo(@Body Xtspz xtspz);
}
