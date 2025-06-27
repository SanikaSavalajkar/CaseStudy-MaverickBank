package com.hexaware.maverickBank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.maverickBank.entity.Account;
@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomer_CustomerId(Long customerId);
    Account findByAccountNumber(String accountNumber);
}