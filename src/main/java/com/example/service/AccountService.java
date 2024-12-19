package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;import java.util.Optional;

@Service
public class AccountService {
    @Autowired    
    AccountRepository accountRepository;

    public Account getAccountByUsername(String accountName){
        return accountRepository.findAccountByUsername(accountName);
    }

    public Account addAccount(Account account){
        return accountRepository.save(account);
    }
}
