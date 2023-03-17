package com.AE.sgmis.result;

public enum ExceptionCode {
    NotFindUserException(401),
    PasswordErrorException(402),
    AccessException(403),
    ExpiredException(405);

    public final int code;
    ExceptionCode(int code) {
        this.code = code;
    }
}
