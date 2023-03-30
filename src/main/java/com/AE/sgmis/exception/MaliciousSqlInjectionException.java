package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;

public class MaliciousSqlInjectionException extends ProjectException{
    public MaliciousSqlInjectionException(String message) {
        super(message);
        setCode(ExceptionCode.MaliciousSqlInjectionException.code);
    }
}
