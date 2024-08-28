package com.dung.nguyenchidung.service;

import com.dung.nguyenchidung.entity.Customer;
import com.dung.nguyenchidung.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.getAllCustomer();
    }

    public void save(Customer customer) {
        customerRepository.createCustomer(customer);
    }

    public Customer findByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }
}
