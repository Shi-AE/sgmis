package com.AE.sgmis.result;

public enum SuccessCode {

    LoginSuccess(2000);

    public final int code;

    SuccessCode(int code) {
        this.code = code;
    }
}
