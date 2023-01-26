package com.kimalmroth.restapidemo.auth;

import com.kimalmroth.restapidemo.auth.model.AuthenticationRequest;
import com.kimalmroth.restapidemo.auth.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(service.authenticate(request));
//        try {
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login");
//        } catch (UsernameNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user with email found");
//        } catch ( BadCredentialsException e) {
//            System.out.println("bad auth");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad password or username");
//        } catch ( Exception e) {
//            System.out.println("test auth");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("bad " + e.getMessage());
//        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws IOException {
        return ResponseEntity.ok(service.register(request));
    }
}
