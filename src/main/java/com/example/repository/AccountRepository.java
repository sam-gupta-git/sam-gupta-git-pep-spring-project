package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("select a from Account a where a.username = ?1")
    Account findAccountByUsername(String username);
}
