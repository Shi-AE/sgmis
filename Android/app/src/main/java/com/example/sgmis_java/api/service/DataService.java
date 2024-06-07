package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.dto.OptionPieDTO;
import com.example.sgmis_java.domain.dto.OplogLineDTO;
import com.example.sgmis_java.domain.dto.TotalDTO;
import com.example.sgmis_java.domain.pojo.Oplog;
import com.example.sgmis_java.domain.pojo.Pigeon;
import com.example.sgmis_java.domain.pojo.Result;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface DataService {

    @GET("data/pigeon/picture")
    Observable<Result<List<Pigeon>>> getPigeonPicture();

    @GET("data/online")
    Observable<Result<Integer>> getOnline();

    @GET("data/online/group")
    Observable<Result<Integer>> getOnlineGroup();

    @GET("data/pigeon/total")
    Observable<Result<TotalDTO>> getPigeonCount();

    @GET("data/oplog/total")
    Observable<Result<TotalDTO>> getOplogCount();

    @GET("data/create")
    Observable<Result<Map<LocalDate, Integer>>> getCreate();

    @GET("data/delete")
    Observable<Result<List<Map<String, Object>>>> getDelete();

    @GET("data/oplog/line")
    Observable<Result<List<OplogLineDTO>>> getOplogLine();

    @GET("data/oplog/data/{limit}")
    Observable<Result<List<Oplog>>> getOplogData(@Path("limit") int limit);

    @GET("data/oplog/content/total")
    Observable<Result<List<OptionPieDTO>>> getOptionPie();
}
