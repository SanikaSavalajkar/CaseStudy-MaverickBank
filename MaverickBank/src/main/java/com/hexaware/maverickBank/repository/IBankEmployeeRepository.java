package com.hexaware.maverickBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.maverickBank.entity.BankEmployee;

@Repository
public interface IBankEmployeeRepository extends JpaRepository<BankEmployee, Long> {
    BankEmployee findByUser_UserId(Long userId);
    //BankEmployee findByUsername(String username);
}