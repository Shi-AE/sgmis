package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.vo.YanCount;
import com.example.sgmis_java.domain.vo.YsCount;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface StatisticService {

    @GET("statistic/yan")
    Observable<Result<List<YanCount>>> getYanData();

    @GET("statistic/ys")
    Observable<Result<List<YsCount>>> getYsData();
}
