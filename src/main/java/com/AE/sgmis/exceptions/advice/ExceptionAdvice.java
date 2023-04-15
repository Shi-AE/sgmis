package com.AE.sgmis.exceptions.advice;

import com.AE.sgmis.exceptions.*;
import com.AE.sgmis.result.ExceptionCode;
import com.AE.sgmis.result.Result;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.DataTruncation;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({NotFindUserException.class, PasswordErrorException.class,
            ConfirmPasswordInconsistencyException.class, UnchangedPasswordException.class,
            DeleteFailException.class, SaveFailException.class, FieldsDuplicateException.class,
            PasswordUpdateFailException.class, AccessException.class, FileSaveException.class})
    private Result doCommonException(ProjectException exception) {
        return new Result(exception.getCode(), exception.getMessage());
    }

    /**
     * 403无访问权限
     */
    @ExceptionHandler({JWTDecodeException.class, SignatureVerificationException.class})
    private Result doAccessException() {
        return new Result(ExceptionCode.AccessException.code, "通行证失效");
    }

    /**
     * 登录过期
     */
    @ExceptionHandler({TokenExpiredException.class})
    private Result doExpiredException() {
        return new Result(ExceptionCode.ExpiredException.code, "通信证过期");
    }

    /**
     * 对数据库操作的数据过大
     */
    @ExceptionHandler(DataTruncation.class)
    private Result doDataTruncationException(DataTruncation exception) {
        log.warn("用户上传的数据超出数据库预期 {}", exception.getMessage());
        return new Result(ExceptionCode.SaveFailException.code, "上传的数据过大，请联系管理员获取支持");
    }

    /**
     * 数据库唯一字段发生重复
     */
    @ExceptionHandler(DuplicateKeyException.class)
    private Result doDuplicateKeyException() {
        return new Result(ExceptionCode.SaveFailException.code, "内容重复");
    }

    /**
     * 非法sql注入
     */
    @ExceptionHandler(MaliciousSqlInjectionException.class)
    private Result doMaliciousSqlInjectionException(MaliciousSqlInjectionException exception) {
        // TODO 分级别禁用IP
        return new Result(exception.getCode(), exception.getMessage());
    }
}
