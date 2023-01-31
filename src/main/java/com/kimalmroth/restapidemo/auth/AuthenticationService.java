package com.kimalmroth.restapidemo.auth;

import com.kimalmroth.restapidemo.account.AccountRepository;
import com.kimalmroth.restapidemo.account.Model.Account;
import com.kimalmroth.restapidemo.auth.data.AuthenticationRequest;
import com.kimalmroth.restapidemo.auth.data.AuthenticationResponse;
import com.kimalmroth.restapidemo.auth.data.RegisterRequest;
import com.kimalmroth.restapidemo.auth.data.RegisterResponse;
import com.kimalmroth.restapidemo.config.JwtService;
import com.kimalmroth.restapidemo.error.exception.DataBadCredentialsException;
import com.kimalmroth.restapidemo.error.exception.EmailAlreadyTakenException;
import com.kimalmroth.restapidemo.role.RoleService;
import com.kimalmroth.restapidemo.role.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) {
        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("Email already taken");
            throw new EmailAlreadyTakenException("Email already taken");
        }
        var account = Account
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode((request.getPassword())))
                .build();
//        if (jwtService.extractRoles(request.getToken()).contains("ADMIN")){
//            account.setRoles(Set.of(roleService.getRoleByName(RoleType.USER), roleService.getRoleByName(RoleType.ADMIN)));
//        } else {
//        }
        account.setRoles(Set.of(roleService.getRoleByName(RoleType.USER)));
        var jwt = jwtService.generateToken(account);
        accountRepository.save(account);
        return RegisterResponse.builder()
                .message("Account registration successful")
                .token(jwt)
                .username(account.getUsername())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("In Authenticate");
        var account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No account found with email: " + request.getEmail()));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            throw new DataBadCredentialsException("Wrong username or password", request.getEmail(), request.getPassword());
        }
        var jwt = jwtService.generateToken(account);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();

    }
}
