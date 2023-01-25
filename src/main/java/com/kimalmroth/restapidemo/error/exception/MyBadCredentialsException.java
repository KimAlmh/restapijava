package com.kimalmroth.restapidemo.error.exception;

import lombok.Getter;

@Getter
public class MyBadCredentialsException extends RuntimeException{
    private final String username;
    private final String password;
    public MyBadCredentialsException(String message, String username, String password) {
        super(message);
        this.username = username;
        this.password = password;
    }
}
