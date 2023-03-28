package com.AE.sgmis.result;

public enum ExceptionCode {
    NotFindUserException(401),
    PasswordErrorException(402),
    AccessException(403),
    ExpiredException(405),
    ConfirmPasswordInconsistencyException(405),
    UnchangedPasswordException(406),
    DeleteFailException(407),
    SaveFailException(408),
    FieldsDuplicateException(409),
    MaliciousSqlInjection(410),
    PasswordUpdateFailException(411);

    public final int code;
    ExceptionCode(int code) {
        this.code = code;
    }
}
