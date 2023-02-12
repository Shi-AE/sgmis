package com.AE.sgmis.exception;

import com.AE.sgmis.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({NotFindUserException.class, PasswordErrorException.class})
    private Result loginException(ProjectException exception) {
        return new Result(exception.getCode(), exception.getMessage());
    }
}
