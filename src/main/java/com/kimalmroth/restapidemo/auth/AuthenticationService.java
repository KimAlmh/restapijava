package com.kimalmroth.restapidemo.auth;

import com.kimalmroth.restapidemo.account.AccountRepository;
import com.kimalmroth.restapidemo.account.Model.Account;
import com.kimalmroth.restapidemo.account.Role;
import com.kimalmroth.restapidemo.auth.model.AuthenticationResponse;
import com.kimalmroth.restapidemo.auth.model.RegisterRequest;
import com.kimalmroth.restapidemo.config.JwtService;
import com.kimalmroth.restapidemo.error.exception.EmailAlreadyTakenException;
import com.kimalmroth.restapidemo.error.exception.MyBadCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()){
            System.out.println("Email already taken");
            throw new EmailAlreadyTakenException("Email already taken");
        }
        var account = Account
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode((request.getPassword())))
                .role(Role.ROLE_USER)
                .build();
        repository.save(account);
        var jwt = jwtService.generateToken(account);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("In Authenticate");
        var account = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("No account found with email: " + request.getEmail()));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            List<String> test = new ArrayList<>();
            throw new MyBadCredentialsException("Wrong username or password", request.getEmail(), request.getPassword());
        }
        var jwt = jwtService.generateToken(account);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
