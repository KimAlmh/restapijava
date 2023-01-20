package com.kimalmroth.restapidemo.auth;

import com.kimalmroth.restapidemo.account.AccountRepository;
import com.kimalmroth.restapidemo.account.Role;
import com.kimalmroth.restapidemo.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kimalmroth.restapidemo.account.Account;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        var account = Account
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode((request.getPassword())))
                .role(Role.User)
                .build();
        repository.save(account);
        var jwt = jwtService.generateToken(account);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var account = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("No account with found with email: " + request.getEmail()));
        var jwt = jwtService.generateToken(account);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
