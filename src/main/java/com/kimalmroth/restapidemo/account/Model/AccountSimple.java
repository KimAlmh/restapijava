package com.kimalmroth.restapidemo.account.Model;

import com.kimalmroth.restapidemo.account.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSimple {

    private String email;
    private String firstname;
    private String lastName;
    private Role role;
}
