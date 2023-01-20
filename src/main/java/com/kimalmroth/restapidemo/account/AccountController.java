package com.kimalmroth.restapidemo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class AccountController {
    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public List<AccountSimpleData> getAccounts() {
        return accountService.getAccounts();
    }
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(path = {"{accountId}"})
    public Account getAccountById(@PathVariable("accountId") String uuid){
        return accountService.getAccountByUuid(uuid);
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public void postAccount(@RequestBody Account account){
        accountService.addAccount(account);
    }
    @PreAuthorize("hasAuthority('Admin')")

    @DeleteMapping(path = {"{accountId}"})
    public void deleteAccount(@PathVariable("accountId") String uuid){
        accountService.removeAccountByUUID(UUID.fromString(uuid));
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(path = "{accountId}")
    public void putAccount(@PathVariable String accountId, @RequestBody Account values){
        accountService.updateAccount(accountId, values);
    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/login")
    public Account login(@RequestBody AccountLoginData account){
        return accountService.login(account);
    }
}
