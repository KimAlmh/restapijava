package com.kimalmroth.restapidemo.error;

import com.kimalmroth.restapidemo.error.exception.EmailAlreadyTakenException;
import com.kimalmroth.restapidemo.error.exception.MyBadCredentialsException;
import com.kimalmroth.restapidemo.error.model.BadCredentialsResponse;
import com.kimalmroth.restapidemo.error.model.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyTaken(Exception ex, WebRequest req) {
        ErrorResponse error = new ErrorResponse();
        error.setDate(LocalDateTime.now());
        error.setStatus(HttpServletResponse.SC_CONFLICT);
        error.setMessage(ex.getMessage());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MyBadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentials(MyBadCredentialsException ex, WebRequest req) {
        BadCredentialsResponse error = new BadCredentialsResponse();
        error.setDate(LocalDateTime.now());
        error.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        error.setMessage(ex.getMessage() + ": bad username or password");
        error.setUsername(ex.getUsername());
        error.setPassword(ex.getPassword());

        return ResponseEntity.status(error.getStatus()).body(error);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(Exception ex, WebRequest req) {
        ErrorResponse error = new ErrorResponse();
        error.setDate(LocalDateTime.now());
        error.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        error.setMessage(ex.getMessage());

        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
