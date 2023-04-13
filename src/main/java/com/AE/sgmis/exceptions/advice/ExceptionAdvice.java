package com.AE.sgmis.exceptions.advice;

import com.AE.sgmis.exceptions.*;
import com.AE.sgmis.result.ExceptionCode;
import com.AE.sgmis.result.Result;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.DataTruncation;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({NotFindUserException.class, PasswordErrorException.class,
            ConfirmPasswordInconsistencyException.class, UnchangedPasswordException.class,
            DeleteFailException.class, SaveFailException.class, FieldsDuplicateException.class
            , MaliciousSqlInjectionException.class, PasswordUpdateFailException.class,
            AccessException.class, FileSaveException.class})
    private Result commonException(ProjectException exception) {
        return new Result(exception.getCode(), exception.getMessage());
    }

    /**
     * 403无访问权限
     */
    @ExceptionHandler({JWTDecodeException.class, SignatureVerificationException.class})
    private Result accessException() {
        return new Result(ExceptionCode.AccessException.code, "通行证失效");
    }

    /**
     * 登录过期
     */
    @ExceptionHandler({TokenExpiredException.class})
    private Result expiredException() {
        return new Result(ExceptionCode.ExpiredException.code, "通信证过期");
    }

    /**
     * 对数据库操作的数据过大
     */
    @ExceptionHandler({DataTruncation.class})
    private Result dataTruncationException(DataTruncation e) {
        log.warn("用户上传的数据超出数据库预期 {}", e.getMessage());
        return new Result(ExceptionCode.SaveFailException.code, "上传的数据过大，请联系管理员获取支持");
    }
}
