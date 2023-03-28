package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;

public class PasswordUpdateFailException extends ProjectException{
    public PasswordUpdateFailException(String message) {
        super(message);
        setCode(ExceptionCode.PasswordUpdateFailException.code);
    }
}
