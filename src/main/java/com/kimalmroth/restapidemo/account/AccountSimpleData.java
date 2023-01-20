package com.kimalmroth.restapidemo.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSimpleData {

    private String email;
    private String firstname;
    private String lastName;
    private Role role;
}
