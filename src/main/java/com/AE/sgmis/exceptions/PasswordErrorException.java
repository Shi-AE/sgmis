package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class PasswordErrorException extends ProjectException {
    public PasswordErrorException(String message) {
        super(message);
        setCode(ExceptionCode.PasswordErrorException.code);
    }
}