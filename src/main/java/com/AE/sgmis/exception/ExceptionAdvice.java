package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;
import com.AE.sgmis.result.Result;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({NotFindUserException.class, PasswordErrorException.class})
    private Result loginException(ProjectException exception) {
        return new Result(exception.getCode(), exception.getMessage());
    }

    /**
     * 403无访问权限
     */
    @ExceptionHandler({JWTDecodeException.class, SignatureVerificationException.class})
    private Result accessException(Exception exception) {
        return new Result(ExceptionCode.AccessException.code, exception.getMessage());
    }

    /**
     * 登录过期
     */
    @ExceptionHandler({TokenExpiredException.class})
    private Result expiredException(Exception exception) {
        return new Result(ExceptionCode.ExpiredException.code, exception.getMessage());
    }
}
