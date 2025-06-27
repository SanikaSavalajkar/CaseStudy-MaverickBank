package com.hexaware.maverickBank.service.interfaces;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.hexaware.maverickBank.dto.AccountCreateRequestDTO;
import com.hexaware.maverickBank.dto.AccountDTO;
import com.hexaware.maverickBank.dto.AccountUpdateRequestDTO;
import com.hexaware.maverickBank.entity.Account;
import com.hexaware.maverickBank.entity.Transaction;

public interface AccountService {
    AccountDTO createAccount(AccountCreateRequestDTO accountCreateRequestDTO);
    AccountDTO getAccountById(Long accountId);
    List<AccountDTO> getAllAccounts();
    AccountDTO updateAccount(Long accountId, AccountUpdateRequestDTO accountUpdateRequestDTO);
    void deleteAccount(Long accountId);
    List<AccountDTO> getAccountsByCustomerId(Long customerId);
    AccountDTO getAccountByAccountNumber(String accountNumber);
    List<Transaction> getTransactionsForAccount(Long accountId);
    List<Transaction> getTransactionsForAccountByDateRange(Long accountId, LocalDateTime startDate, LocalDateTime endDate);
    void deposit(Account account, BigDecimal amount);
    void withdraw(Account account, BigDecimal amount);
    void transfer(Account fromAccount, Account toAccount, BigDecimal amount);
}