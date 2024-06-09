package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.dto.LogDTO;
import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.vo.PagingConditionVo;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OplogService {

    @POST("oplog")
    Observable<Result<LogDTO>> getConditionPage(@Body PagingConditionVo condition);
}
