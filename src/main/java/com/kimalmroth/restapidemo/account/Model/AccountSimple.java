package com.kimalmroth.restapidemo.account.Model;

import com.kimalmroth.restapidemo.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSimple {

    private String email;
    private String firstname;
    private String lastName;
    private Set<Role> role;
}
