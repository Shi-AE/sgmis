package com.AE.sgmis.exceptions;

public class ProjectException extends RuntimeException{
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ProjectException(String message) {
        super(message);
    }
}
