package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class UserInformationException extends ProjectException{
    public UserInformationException(String message) {
        super(message);
        setCode(ExceptionCode.UserInformationException.code);
    }
}
