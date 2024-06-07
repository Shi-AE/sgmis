package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.pojo.Gsxx;
import com.example.sgmis_java.domain.pojo.Result;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DovecotInfoService {
    @GET("gsxx")
    Observable<Result<Gsxx>> getGsxx();

    @POST("gsxx")
    Observable<Result<Object>> postGsxx(@Body Gsxx gsxx);
}
