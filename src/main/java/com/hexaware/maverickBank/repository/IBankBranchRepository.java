package com.hexaware.maverickBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.maverickBank.entity.BankBranch;
@Repository
public interface IBankBranchRepository extends JpaRepository<BankBranch, Long> {
    BankBranch findByName(String name);
    BankBranch findByIfscPrefix(String ifscPrefix);
}