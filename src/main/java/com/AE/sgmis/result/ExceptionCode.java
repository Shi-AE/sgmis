package com.AE.sgmis.result;

public enum ExceptionCode {
    NotFindUserException(5001),
    PasswordErrorException(5002);

    public final int code;
    ExceptionCode(int code) {
        this.code = code;
    }
}
