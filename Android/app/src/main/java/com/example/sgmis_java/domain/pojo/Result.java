package com.example.sgmis_java.domain.pojo;

import com.google.gson.internal.LinkedTreeMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private String message;
    private Integer code;
    private T data;
    private LinkedTreeMap<String, Object> param;
}
