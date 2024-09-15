package com.harsh.eventmanagement.exception;


import com.harsh.eventmanagement.constant.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class EventManagementExceptionHandler {


    @ExceptionHandler(EventManagementException.class)
    public ResponseEntity<ErrorResponse> handlePaymentProcessingException(
            EventManagementException ex){


      ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setErrorMessage(ex.getErrorMessage());



        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, ex.getHttpStatus());


        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex){


        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCodeEnum.GENERIC_ERROR.getErrorCode());
        errorResponse.setErrorMessage(ErrorCodeEnum.GENERIC_ERROR.getErrorMessage());


        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);


        return responseEntity;
    }


}
