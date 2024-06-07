package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.pojo.Xxpz;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OptionService {
    @GET("xxpz/{type}")
    Observable<Result<List<Xxpz>>> getAllByType(@Path("type") String type);
}
