package com.kimalmroth.restapidemo.error;

public class EmailAlreadyTakenException extends  RuntimeException {
    public EmailAlreadyTakenException(String errorMessage){
        super(errorMessage);
    }
}
