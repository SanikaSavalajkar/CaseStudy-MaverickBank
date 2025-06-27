package com.hexaware.maverickBank.service.interfaces;

import java.util.List;

import com.hexaware.maverickBank.dto.CustomerCreateRequestDTO;
import com.hexaware.maverickBank.dto.CustomerDTO;
import com.hexaware.maverickBank.dto.CustomerUpdateRequestDTO;

public interface CustomerServcie {
    CustomerDTO createCustomer(CustomerCreateRequestDTO customerCreateRequestDTO);
    CustomerDTO getCustomerById(Long customerId);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(Long customerId, CustomerUpdateRequestDTO customerUpdateRequestDTO);
    void deleteCustomer(Long customerId);
    CustomerDTO getCustomerByUserId(Long userId);
}
