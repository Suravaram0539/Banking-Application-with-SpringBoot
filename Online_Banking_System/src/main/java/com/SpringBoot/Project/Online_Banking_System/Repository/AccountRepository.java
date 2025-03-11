package com.SpringBoot.Project.Online_Banking_System.Repository;

import com.SpringBoot.Project.Online_Banking_System.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {

}
