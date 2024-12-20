package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository interface for the Account entity
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("select a from Account a where a.username = ?1")
    Account findAccountByUsername(String username);

    @Query("select a from Account a where a.username = ?1 and a.password = ?2")
    Account findAccountByUsernameAndPassword(String username, String password);

}
