package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class UnchangedPasswordException extends ProjectException {
    public UnchangedPasswordException(String message) {
        super(message);
        setCode(ExceptionCode.UnchangedPasswordException.code);
    }
}
