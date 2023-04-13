package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class ConfirmPasswordInconsistencyException extends ProjectException{
    public ConfirmPasswordInconsistencyException(String message) {
        super(message);
        setCode(ExceptionCode.ConfirmPasswordInconsistencyException.code);
    }
}
