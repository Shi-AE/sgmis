package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.dto.LogDTO;
import com.example.sgmis_java.domain.pojo.Oplog;
import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.vo.PagingConditionVo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OplogService {

    @POST("oplog")
    Observable<Result<LogDTO>> getConditionPage(@Body PagingConditionVo condition);

    @GET("oplog/{pid}/{limit}")
    Observable<Result<List<Oplog>>> getLogById(@Path("pid") Long pid, @Path("limit") Integer limit);
}
