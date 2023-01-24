package com.kimalmroth.restapidemo.account;

import com.kimalmroth.restapidemo.account.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{

//    @Query("SELECT a FROM Account a WHERE a.email = ?1")
    Optional<Account> findByEmail(String email);
}
