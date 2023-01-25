//package com.kimalmroth.restapidemo.error;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.io.IOException;
//
//@Controller
//public class MyErrorController implements ErrorController {
//    @RequestMapping("/error")
//    public ResponseEntity<?> handleError(HttpServletRequest request, HttpServletResponse response) {
//        String errorPage = "error"; // default
//
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
//            // handle HTTP 404 Not Found error
//            errorPage = "error/404";
//
//        } else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
//            System.out.println("FORBIDDEN");
//            errorPage = "error/403";
//
//        } else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//            // handle HTTP 500 Internal Server error
//            errorPage = "error/500";
//
//        } else if (response.getStatus() == HttpStatus.UNAUTHORIZED.value()){
//            errorPage = "401 unauthorized ";
//        } else {
//            errorPage = "unknown error: " + response.getStatus();
//        }
//
//        return ResponseEntity.status(response.getStatus()).body(errorPage);
//    }
//
//}
