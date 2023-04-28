package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class NotFoundException extends ProjectException {
    public NotFoundException(String message) {
        super(message);
        setCode(ExceptionCode.NotFoundException.code);
    }
}
