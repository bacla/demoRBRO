package com.example.demoRBRO.repo;

import com.example.demoRBRO.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a where a.iban = ?1")
    Account findAccountByIban(String iban);
}
