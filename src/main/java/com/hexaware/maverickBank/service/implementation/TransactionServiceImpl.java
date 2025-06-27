package com.hexaware.maverickBank.service.implementation;

/**
 * -----------------------------------------------------------------------------
 * Author      : Sanika
 * Date        : June 25, 2025
 * Description : This class implements the TransactionService interface and handles 
 *               the business logic for transaction operations including creation, 
 *               retrieval by ID, and deletion of transactions.
 * 
 *               It also includes utility methods for converting between DTO and 
 *               entity objects for transactions, and handles interactions with 
 *               the database through appropriate repositories.
 * -----------------------------------------------------------------------------
 */

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.maverickBank.dto.TransactionDTO;
import com.hexaware.maverickBank.entity.Account;
import com.hexaware.maverickBank.entity.Transaction;
import com.hexaware.maverickBank.repository.IAccountRepository;
import com.hexaware.maverickBank.repository.ITransactionRepository;
import com.hexaware.maverickBank.service.interfaces.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IAccountRepository accountRepository;

 

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = convertDTOtoEntity(transactionDTO);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertEntityToDTO(savedTransaction);
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction not found with ID: " + transactionId));
        return convertEntityToDTO(transaction);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new NoSuchElementException("Transaction not found with ID: " + transactionId);
        }
        transactionRepository.deleteById(transactionId);
    }

    private TransactionDTO convertEntityToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(transaction.getTransactionId());
        if (transaction.getAccount() != null) {
            dto.setAccountId(transaction.getAccount().getAccountId());
        }
        dto.setTransactionType(transaction.getTransactionType());
        dto.setAmount(transaction.getAmount());
        dto.setTransactionDate(transaction.getTransactionDate());
        //dto.setDescription(transaction.getDescription());
        
        return dto;
    }
    
    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                           .map(this::convertEntityToDTO)
                           .toList();
    }


    private Transaction convertDTOtoEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(dto.getTransactionId());
        if (dto.getAccountId() != null) {
            Account account = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new NoSuchElementException("Account not found with ID: " + dto.getAccountId()));
            transaction.setAccount(account);
        }
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionDate(dto.getTransactionDate());
        //transaction.setDescription(dto.getDescription());
        
                    
           
        
        return transaction;
    }
}