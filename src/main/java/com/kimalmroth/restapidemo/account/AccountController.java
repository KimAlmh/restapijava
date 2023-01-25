package com.kimalmroth.restapidemo.account;

import com.kimalmroth.restapidemo.account.Model.Account;
import com.kimalmroth.restapidemo.account.Model.AccountLogin;
import com.kimalmroth.restapidemo.account.Model.AccountSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin/user")
//@EnableMethodSecurity
//@PreAuthorize("hasRole('USER')")
public class AccountController {
    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountSimple> getAccounts() {
        return accountService.getAccounts();
    }
    @GetMapping(path = {"{accountId}"})
    public Account getAccountById(@PathVariable("accountId") String uuid){
        try {
            return accountService.getAccountByUuid(uuid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Account();
        }
    }
    @PostMapping
    public void postAccount(@RequestBody Account account){
        accountService.addAccount(account);
    }

    @DeleteMapping(path = {"{accountId}"})
    public void deleteAccount(@PathVariable("accountId") String uuid){
        accountService.removeAccountByUUID(UUID.fromString(uuid));
    }
    @PutMapping(path = "{accountId}")
    public void putAccount(@PathVariable String accountId, @RequestBody Account values){
        accountService.updateAccount(accountId, values);
    }
    @PostMapping("/login")
    public Account login(@RequestBody AccountLogin account){
        return accountService.login(account);
    }
}
