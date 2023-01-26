package com.kimalmroth.restapidemo.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private String message;
    private String username;
    private String token;
}
