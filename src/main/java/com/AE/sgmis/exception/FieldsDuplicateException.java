package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;

public class FieldsDuplicateException extends ProjectException{
    public FieldsDuplicateException(String message) {
        super(message);
        setCode(ExceptionCode.FieldsDuplicateException.code);
    }
}
