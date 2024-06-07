package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.pojo.Area;
import com.example.sgmis_java.domain.pojo.Provincial;
import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.pojo.Urban;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AddressService {

    @GET("provincial")
    Observable<Result<List<Provincial>>> getProvincial();

    @GET("urban/{id}")
    Observable<Result<List<Urban>>> getUrban(@Path("id") Long id);

    @GET("area/{id}")
    Observable<Result<List<Area>>> getAreas(@Path("id") Long id);
}
