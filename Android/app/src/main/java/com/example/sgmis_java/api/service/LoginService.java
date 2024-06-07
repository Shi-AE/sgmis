package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.dto.UserDTO;
import com.example.sgmis_java.domain.pojo.LoginMsg;
import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.vo.UpdateUserVo;
import com.example.sgmis_java.domain.vo.UserVo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface LoginService {

    @POST("login/authority")
    Observable<Result<UserDTO>> loginVerify(@Body UserVo user);

    @PUT("login")
    Observable<Result<String>> updatePassword(@Body UpdateUserVo userVo);

    @GET("login/message")
    Observable<Result<List<LoginMsg>>> getLoginMessage();
}
