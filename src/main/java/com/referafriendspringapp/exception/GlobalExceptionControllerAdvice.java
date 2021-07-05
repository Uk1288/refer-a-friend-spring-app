package com.referafriendspringapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse httpRes, CustomException exception) throws IOException {
        httpRes.sendError(exception.getHttpStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handlerException(HttpServletResponse httpRes, Exception exception) throws IOException {
        httpRes.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
