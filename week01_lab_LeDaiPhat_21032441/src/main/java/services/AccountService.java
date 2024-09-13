package services;

import entities.Account;
import repositories.AccountRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService() {
        accountRepository = new AccountRepository();
    }
    public Account checkLogin(String phone, String password) {
        return accountRepository.login(phone, password);
    }

    public Boolean checkAdmin(Account a){
        return accountRepository.isAdmin(a);
    }

    public ArrayList<Account> getAllAccount(){
        return accountRepository.findAll();
    }

    public ArrayList<Account> getAccountByRole(String role){
        return accountRepository.findByRole(role);
    }
}
