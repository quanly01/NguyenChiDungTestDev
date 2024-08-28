package com.dung.nguyenchidung.repository;

import com.dung.nguyenchidung.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {
    public List<Customer> getAllCustomer(){
        throw new RuntimeException("Function is not implemented");
    }

    public void createCustomer(Customer customer){
        throw new RuntimeException("Function is not implemented");
    }

    public Customer getCustomerByEmail(String email){
        throw new RuntimeException("Function is not implemented");
    }
}
