package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;

public class FileSaveException extends ProjectException {
    public FileSaveException(String message) {
        super(message);
        setCode(ExceptionCode.FileSaveException.code);
    }
}
