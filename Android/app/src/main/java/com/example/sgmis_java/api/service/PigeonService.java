package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.pojo.Pigeon;
import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.vo.PigeonWrapperVo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PigeonService {
    @GET("pigeon")
    Observable<Result<List<Pigeon>>> listPigeon();

    @DELETE("pigeon/{id}/{sex}")
    Observable<Result<Object>> removePigeonById(@Path("id") Long id, @Path("sex") String sex);

    @GET("pigeon/{id}")
    Observable<Result<PigeonWrapperVo>> getPigeon(@Path("id") Long id);
}
