package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class NotFindUserException extends ProjectException{
    public NotFindUserException(String message) {
        super(message);
        setCode(ExceptionCode.NotFindUserException.code);
    }
}
