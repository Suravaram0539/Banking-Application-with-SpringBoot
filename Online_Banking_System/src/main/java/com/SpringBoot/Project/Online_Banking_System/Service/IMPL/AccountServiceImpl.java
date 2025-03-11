package com.SpringBoot.Project.Online_Banking_System.Service.IMPL;

import com.SpringBoot.Project.Online_Banking_System.DTO.AccountDto;
import com.SpringBoot.Project.Online_Banking_System.Entity.Account;
import com.SpringBoot.Project.Online_Banking_System.Exception.AccountException;
import com.SpringBoot.Project.Online_Banking_System.Mapper.AccountMapper;
import com.SpringBoot.Project.Online_Banking_System.Repository.AccountRepository;
import com.SpringBoot.Project.Online_Banking_System.Service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.maptoAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    public AccountDto getAccountById(Long id){
        Account account=accountRepository.
                findById(id).
                orElseThrow(()->new AccountException("Account does exists"));

        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account=accountRepository.
                findById(id).
                orElseThrow(()->new AccountException("Account does exists"));

        double total=account.getBalance()+amount;

        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);

        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account=accountRepository.
                findById(id).
                orElseThrow(()->new AccountException("Account does exists"));

        if(account.getBalance()<amount){
            throw new AccountException("Insufficient amount");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts(){
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map(AccountMapper::maptoAccountDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.
                findById(id).
                orElseThrow(()->new AccountException("Account does exists"));

        accountRepository.deleteById(id);

    }

    @Override
    public AccountDto transferMoney(Long senderid, Long receiverid, double amount) {

        Account account=accountRepository.
                findById(senderid).
                orElseThrow(()->new AccountException("Sender Account does exists"));

        Account account1=accountRepository.
                findById(receiverid).
                orElseThrow(()->new AccountException("Receiver Account does exists"));

        if(account.getBalance()<amount){
            throw new AccountException("Insufficient amount");
        }

        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);

        double total1=account1.getBalance()+amount;

        account1.setBalance(total1);
        Account savedAccount1=accountRepository.save(account1);

        return AccountMapper.maptoAccountDto(savedAccount1);

    }


}
