package com.harsh.eventmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EventManagementException extends RuntimeException {

    private static final long serialVersionUID = 5460057024243093828L;

    private final String errorCode;
    private final String errorMessage;

    private final HttpStatus httpStatus;



}