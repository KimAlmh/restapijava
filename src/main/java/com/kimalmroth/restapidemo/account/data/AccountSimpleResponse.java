package com.kimalmroth.restapidemo.account.data;

import com.kimalmroth.restapidemo.role.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSimpleResponse {

    private String email;
    private String firstName;
    private String lastName;
    private Set<Role> roles;
}
