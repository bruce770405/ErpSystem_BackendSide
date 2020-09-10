package com.bruce.erpapp.common.advice;

import com.bruce.erpapp.common.errorhandle.exception.SystemException;
import com.bruce.erpapp.common.models.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemExceptionHandler {

    @ExceptionHandler({SystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData handleException(SystemException e) {
        var response = new ResponseData();
        response.setStatus(e.getStatus());
        return response;
    }
}
