package com.kimalmroth.restapidemo.account.data;

import lombok.Getter;

public class AccountLoginRequest {

    @Getter
    private String email;
    @Getter
    private String password;
}
