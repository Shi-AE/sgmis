package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;

public class NotFindUserException extends ProjectException{
    public NotFindUserException(String message) {
        super(message);
        setCode(ExceptionCode.NotFindUserException.code);
    }
}
