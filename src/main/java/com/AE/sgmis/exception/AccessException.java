package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;

public class AccessException extends ProjectException{
    public AccessException(String message) {
        super(message);
        setCode(ExceptionCode.AccessException.code);
    }
}
