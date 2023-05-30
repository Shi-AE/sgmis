package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class FreePassException extends ProjectException {
    public FreePassException(String message) {
        super(message);
        setCode(ExceptionCode.FreePassException.code);
    }
}
