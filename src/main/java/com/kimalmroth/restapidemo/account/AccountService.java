package com.kimalmroth.restapidemo.account;

import com.kimalmroth.restapidemo.account.Model.Account;
import com.kimalmroth.restapidemo.account.data.AccountLoginRequest;
import com.kimalmroth.restapidemo.account.data.AccountSimpleResponse;
import com.kimalmroth.restapidemo.mapper.UserMapper;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
    BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.Y, 11);

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountSimpleResponse> getAccounts() {
        var accounts = accountRepository.findAll();
        System.out.println(accounts.get(0).getRoles());
        ArrayList<AccountSimpleResponse> simpleData = new ArrayList<>();
        for (Account acc : accounts) {
            simpleData.add(mapper.ACCOUNT_SIMPLE_RESPONSE(acc));
        }
        return simpleData;
    }

    public void addAccount(Account accountIn) {
        Optional<Account> accountFromDb = accountRepository.findByEmail(accountIn.getEmail());

        if (accountFromDb.isPresent())
            throw new IllegalStateException("Email already in use");

        accountIn.setPassword(generateHashedPassword(accountIn.getPassword()));
        accountRepository.save(accountIn);
    }

    public void removeAccountByUUID(UUID uuid) {
        if (!accountRepository.existsById(uuid)) {
            throw new IllegalStateException("No account with uuid: " + uuid);
        }
        accountRepository.deleteById(uuid);
    }

    public Account getAccountByUuid(String uuid) {
        var account = accountRepository.findById(UUID.fromString(uuid));
        if (account.isEmpty()) {
            throw new IllegalStateException("No account with id: " + uuid);
        }
        return account.get();
    }

    public void updateAccount(String accountId, Account accountIn) {
        Account accountFromDb = accountRepository
                .findById(UUID.fromString(accountId))
                .orElseThrow(() -> new IllegalStateException("No account with id: " + accountId));

        if (isNotNullOrEmptyOrEqual(accountIn.getEmail(), accountFromDb.getEmail())
                && accountRepository.findByEmail(accountIn.getEmail()).isEmpty())
            accountFromDb.setEmail(accountIn.getEmail());

        if (isNotNullOrEmptyOrEqual(accountIn.getPassword(), accountFromDb.getPassword()))
            accountFromDb.setPassword(generateHashedPassword(accountIn.getPassword()));

        if (isNotNullOrEmptyOrEqual(accountIn.getFirstName(), accountFromDb.getFirstName()))
            accountFromDb.setFirstName(accountIn.getFirstName());

        if (isNotNullOrEmptyOrEqual(accountIn.getLastName(), accountFromDb.getLastName()))
            accountFromDb.setLastName(accountIn.getLastName());

        accountRepository.save(accountFromDb);
    }

    public Account login(AccountLoginRequest accountIn) {
        Optional<Account> accountFromDb = accountRepository.findByEmail(accountIn.getEmail());
        if (accountFromDb.isEmpty()) {
            throw new IllegalStateException("No user with email: " + accountIn.getEmail());
        }
        var hashedPassword = accountFromDb.get().getPassword();
        if (!checkPassword(accountIn.getPassword(), hashedPassword)) {
            throw new IllegalStateException("Password does not match");
        }
        return accountFromDb.get();

    }

    private boolean isNotNullOrEmptyOrEqual(String newValue, String currentValue) {
        return newValue != null && newValue.length() != 0 && !newValue.equals(currentValue);
    }

    private String generateHashedPassword(String password) {
        return Password.hash(password).with(bcrypt).getResult();
    }

    private boolean checkPassword(String password, String hashedPassword) {
        return Password.check(password, hashedPassword).with(bcrypt);
    }
}
