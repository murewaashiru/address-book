package com.example.accountbalance.service;

import  com.example.accountbalance.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountData {
    //list of accounts
    private List<Account> accounts;
    private static AccountData instance = null;

    public static AccountData getInstance(){
        if(instance == null){
            instance = new AccountData();
        }
        return instance;
    }

    public AccountData(){
        accounts = new ArrayList<Account>();
        accounts.add(new Account(1, "2490456792", 0.00));
        accounts.add(new Account(2, "9596075480", 100.00));
        accounts.add(new Account(3, "24904525792", 34554.00));
        accounts.add(new Account(4, "9600456792", 379234.00));
    }

    public List<Account> fetchAccounts() {
        return accounts;
    }

    public Account getByAccountNumber(String accountNumber) {
        for(Account m: accounts) {
            if(m.getAccountNumber().equals(accountNumber)) {
                return m;
            }
        }
        return null;
    }

    public double getBalanceByAccountNumber(String accountNumber) {
        for(Account m: accounts) {
            if(m.getAccountNumber().equals(accountNumber)) {
                return m.getBalance();
            }
        }
        return 0;
    }

}
