package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;

public class DeleteFailException extends ProjectException{
    public DeleteFailException(String message) {
        super(message);
        setCode(ExceptionCode.DeleteFailException.code);
    }
}
