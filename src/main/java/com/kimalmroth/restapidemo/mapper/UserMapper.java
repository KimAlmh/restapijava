package com.kimalmroth.restapidemo.mapper;

import com.kimalmroth.restapidemo.account.Model.Account;
import com.kimalmroth.restapidemo.account.data.AccountSimpleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Account ACCOUNT(AccountSimpleResponse accountSimpleResponse);
    AccountSimpleResponse ACCOUNT_SIMPLE_RESPONSE(Account account);
}
