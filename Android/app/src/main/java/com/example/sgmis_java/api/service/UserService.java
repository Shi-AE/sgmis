package com.example.sgmis_java.api.service;

import com.example.sgmis_java.domain.dto.AdminUserDTO;
import com.example.sgmis_java.domain.pojo.Result;
import com.example.sgmis_java.domain.pojo.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("user")
    Observable<Result<List<AdminUserDTO>>> getUserList();

    @PUT("user")
    Observable<Result<Object>> resetPassword(@Body User user);

    @DELETE("user/{id}")
    Observable<Result<Object>> deleteUser(@Path("id") Long id);
}
