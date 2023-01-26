package com.kimalmroth.restapidemo.error.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadCredentialsResponse extends ErrorResponse {
    private String username;
    private String password;
}
