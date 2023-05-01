package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class FileParseException extends ProjectException{
    public FileParseException(String message) {
        super(message);
        setCode(ExceptionCode.FileParseException.code);
    }
}
