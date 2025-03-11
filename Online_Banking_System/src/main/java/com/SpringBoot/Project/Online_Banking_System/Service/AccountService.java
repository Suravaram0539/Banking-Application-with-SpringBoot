package com.SpringBoot.Project.Online_Banking_System.Service;

import com.SpringBoot.Project.Online_Banking_System.DTO.AccountDto;
import com.SpringBoot.Project.Online_Banking_System.Entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    AccountDto transferMoney(Long senderid,Long receiverid, double amount);

}
