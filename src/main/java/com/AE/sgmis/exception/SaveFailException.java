package com.AE.sgmis.exception;

import com.AE.sgmis.result.ExceptionCode;

public class SaveFailException extends ProjectException{
    public SaveFailException(String message) {
        super(message);
        setCode(ExceptionCode.SaveFailException.code);
    }
}
