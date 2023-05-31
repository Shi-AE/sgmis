package com.AE.sgmis.exceptions;

import com.AE.sgmis.result.ExceptionCode;
import com.AE.sgmis.result.SeverityLevel;

public class MaliciousSqlInjectionException extends ProjectException{

    private SeverityLevel severityLevel;

    public MaliciousSqlInjectionException(String message, SeverityLevel severityLevel) {
        super(message);
        this.severityLevel = severityLevel;
        setCode(ExceptionCode.MaliciousSqlInjectionException.code);
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }
}
