package com.hexaware.maverickBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.maverickBank.entity.Loan;
@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {
}