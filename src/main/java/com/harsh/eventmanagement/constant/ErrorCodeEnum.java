package com.harsh.eventmanagement.constant;


import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    GENERIC_ERROR("2000","Unable To Process, try later");



    private String errorCode;
    private String errorMessage;


    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ErrorCodeEnum fromErrorCode(String errorCode) {
        for (ErrorCodeEnum error : ErrorCodeEnum.values()) {
            if (error.getErrorCode().equals(errorCode)) {
                return error;
            }
        }
        throw new IllegalArgumentException("No matching enum constant for errorCode: " + errorCode);
    }

    public static ErrorCodeEnum fromErrorMessage(String errorMessage) {
        for (ErrorCodeEnum error : ErrorCodeEnum.values()) {
            if (error.getErrorMessage().equalsIgnoreCase(errorMessage)) {
                return error;
            }
        }
        throw new IllegalArgumentException("No matching enum constant for errorMessage: " + errorMessage);
    }

}
