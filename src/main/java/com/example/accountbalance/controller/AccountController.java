package com.example.accountbalance.controller;

import com.example.accountbalance.models.Account;
import com.example.accountbalance.service.AccountData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    AccountData accountData = AccountData.getInstance();

    @GetMapping("")
    public List<Account> getAccounts() {
        return accountData.fetchAccounts();
    }

    @GetMapping("/balance/{id}")
    public Object getAccountBalance(@PathVariable String id){
        return accountData.getBalanceByAccountNumber(id);
    }

}
