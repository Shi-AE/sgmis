package com.AE.sgmis.result;

public enum ExceptionCode {
    NotFindUserException(401),
    PasswordErrorException(402),
    AccessException(403),
    NotFoundException(404),
    ExpiredException(405),
    ConfirmPasswordInconsistencyException(406),
    UnchangedPasswordException(407),
    DeleteFailException(408),
    SaveFailException(409),
    FieldsDuplicateException(410),
    MaliciousSqlInjectionException(411),
    PasswordUpdateFailException(412),
    FileSaveException(413),
    DuplicateKeyException(414),
    UserInformationException(415);

    public final int code;
    ExceptionCode(int code) {
        this.code = code;
    }
}
