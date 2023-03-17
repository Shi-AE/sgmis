package com.AE.sgmis.result;

public enum SuccessCode {

    Success(200),
    LoginSuccess(201),
    ExitSuccess(202),
    AccessSuccess(203),
    ModifyPasswordSuccess(204);

    public final int code;

    SuccessCode(int code) {
        this.code = code;
    }
}
