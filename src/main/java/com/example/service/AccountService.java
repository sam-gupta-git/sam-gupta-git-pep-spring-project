package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired    
    AccountRepository accountRepository;

    /**
     * Method to retrieve an account given the accountId
     * @param accountId ID of the account to retrieve
     */ 
    public Account getAccountById(int accountId){
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()){
            Account account = accountOptional.get();
            return account;
        }
        return null;
    }
    
    /**
     * Method to retrieve an account given a username
     * @param accountName String containing the username of an account
     */ 
    public Account getAccountByUsername(String accountName){
        return accountRepository.findAccountByUsername(accountName);
    }

    /**
     * Method to add a new account, returns the new account object if successful
     * @param account Account object that contains account information
     */ 
    public Account addAccount(Account account){
        return accountRepository.save(account);
    }

    /**
     * Method to verify a login given accountName and accountPassword
     * @param accountName String containing a username
     * @param accountPassword String containing a password
     */ 
    public Account login(String accountName, String accountPassword){
        return accountRepository.findAccountByUsernameAndPassword(accountName, accountPassword);
    }
}
