package com.AE.sgmis.exceptions.advice;

import com.AE.sgmis.exceptions.*;
import com.AE.sgmis.result.ExceptionCode;
import com.AE.sgmis.result.Result;
import com.AE.sgmis.util.BlacklistUtil;
import com.AE.sgmis.util.IpUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.DataTruncation;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @Autowired
    private BlacklistUtil blacklistUtil;
    @Autowired
    private IpUtil ipUtil;


    @ExceptionHandler({NotFindUserException.class, PasswordErrorException.class,
            ConfirmPasswordInconsistencyException.class, UnchangedPasswordException.class,
            DeleteFailException.class, SaveFailException.class, FieldsDuplicateException.class,
            PasswordUpdateFailException.class, AccessException.class, FileSaveException.class,
            UserInformationException.class, NotFoundException.class, FileParseException.class,
            LogException.class, FreePassException.class})
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
        return new Result(ExceptionCode.DuplicateKeyException.code, "信息已存在");
    }

    /**
     * 非法sql注入
     */
    @ExceptionHandler(MaliciousSqlInjectionException.class)
    private Result doMaliciousSqlInjectionException(MaliciousSqlInjectionException exception, HttpServletRequest request) {
        //获取实际ip，避免使用token中的ip
        String ip = ipUtil.getIp(request);

        //添加信息
        Map<String, Object> cause = new HashMap<>();
        cause.put("ip", ip);
        cause.put("message", exception.getMessage());
        cause.put("route", request.getServletPath());

        //按严重程度加入黑名单
        blacklistUtil.addForbiddenIp(ip, cause, exception.getSeverityLevel());

        return new Result(exception.getCode(), exception.getMessage());
    }
}
