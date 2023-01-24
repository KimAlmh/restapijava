package com.kimalmroth.restapidemo.auth;

import com.google.gson.Gson;
import com.kimalmroth.restapidemo.error.EmailAlreadyTakenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyAuthority('Admin', 'User')")
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request){
        try {
            System.out.println("test auth");
            return ResponseEntity.ok(service.authenticate(request));
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login");
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user with email found");
        } catch ( Exception e) {
            System.out.println("test auth");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("bad " + e.getMessage());
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@NonNull HttpServletRequest req, @NonNull HttpServletResponse response, @RequestBody RegisterRequest request) throws IOException {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (EmailAlreadyTakenException e){
//            response.sendError(409, "Email already taken");
            Gson gson = new Gson();
            return ResponseEntity.status(409).body(gson.toJson("409: Email already taken"));
        }

    }
}
