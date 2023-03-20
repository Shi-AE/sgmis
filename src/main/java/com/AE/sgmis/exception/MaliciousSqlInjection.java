package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;

public class MaliciousSqlInjection extends ProjectException{
    public MaliciousSqlInjection(String message) {
        super(message);
        setCode(ExceptionCode.MaliciousSqlInjection.code);
    }
}
