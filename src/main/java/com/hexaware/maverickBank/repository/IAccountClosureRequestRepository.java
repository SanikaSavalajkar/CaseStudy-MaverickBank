package com.hexaware.maverickBank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.maverickBank.entity.AccountClosureRequest;
@Repository
public interface IAccountClosureRequestRepository extends JpaRepository<AccountClosureRequest, Long> {
    List<AccountClosureRequest> findByCustomer_CustomerId(Long customerId);
    AccountClosureRequest findByAccount_AccountId(Long accountId);
}