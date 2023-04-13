package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class SaveFailException extends ProjectException{
    public SaveFailException(String message) {
        super(message);
        setCode(ExceptionCode.SaveFailException.code);
    }
}
