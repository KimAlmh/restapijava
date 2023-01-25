package com.kimalmroth.restapidemo.error.exception;

public class EmailAlreadyTakenException extends  RuntimeException {
    public EmailAlreadyTakenException(String errorMessage){
        super(errorMessage);
    }
}
