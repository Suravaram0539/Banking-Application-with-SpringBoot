package com.SpringBoot.Project.Online_Banking_System.Mapper;

import com.SpringBoot.Project.Online_Banking_System.DTO.AccountDto;
import com.SpringBoot.Project.Online_Banking_System.Entity.Account;

public class AccountMapper {

    public static Account maptoAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.id(),
                accountDto.accountHolderName(),
                accountDto.balance()
        );
        return account;
    }

    public static AccountDto maptoAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
