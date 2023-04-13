package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class DeleteFailException extends ProjectException{
    public DeleteFailException(String message) {
        super(message);
        setCode(ExceptionCode.DeleteFailException.code);
    }
}
