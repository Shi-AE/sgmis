package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class LogException extends ProjectException{
    public LogException(String message) {
        super(message);
        setCode(ExceptionCode.LogException.code);
    }
}
